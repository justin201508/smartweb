package com.arcvideo.smartweb.framework.proxy;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 关于ProxyChain的一些解释：
 * 代理链维护了一个目标类和拦截器列表，
 * 在动态代理调用代理方法的时候，讲执行的权限交给了ProxyChain的doProxyChain方法
 * 在这个方法里，实现了代理类的钩子方法的层层迭代，类似下面的调用
 *
 * begin1
 * begin2
 * before1
 * before2
 * target.run()
 * after1
 * after2
 * end1
 * end2
 *
 * Created by st@arcvideo.com on 2017/3/7.
 */
public class ProxyChain {

    // 目标类[被代理类]
    private final Class<?> targetClass;
    // 目标对象[被代理对象]
    private final Object targetObject;
    // 被代理的方法
    private final Method targetMethod;
    // CGLIB 代理方法
    private final MethodProxy methodProxy;
    // 方法参数列表
    private final Object[] methodParams;
    // 执行代理列表
    private final List<Proxy> proxyList;

    private int proxyIndex = 0;

    public ProxyChain(Class<?> targetClass,Object targetObject,
                      Method targetMethod,MethodProxy methodProxy,Object[] methodParams,List<Proxy> proxyList){
        this.targetClass = targetClass;
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
        this.methodProxy = methodProxy;
        this.methodParams = methodParams;
        this.proxyList = proxyList;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Object getTargetObject() {
        return targetObject;
    }

    public Object[] getMethodParams() {
        return methodParams;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    /**
     * 可以执行链式代理方法的代理植入
     * @return
     * @throws Throwable
     */
    public Object doProxyChain() throws Throwable{
        Object result = null;
        if(proxyIndex < proxyList.size()){
            result = proxyList.get(proxyIndex++).doProxy(this);
        }
        else{
            result = methodProxy.invokeSuper(targetObject,methodParams);
        }
        return result;
    }
}
