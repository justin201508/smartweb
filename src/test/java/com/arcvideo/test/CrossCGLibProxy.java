package com.arcvideo.test;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 *
 * 在spring aop中，默认是代理接口，可以通过proxyTargetClass的方式，
 * 强制spring framework通过CGLIB实现代理目标类，这样接口里没有的方法，也可以实现代理了
 *
 * Created by st@arcvideo.com on 2017/3/7.
 */
public class CrossCGLibProxy implements MethodInterceptor {

    private CrossCGLibProxy(){}

    public static CrossCGLibProxy INSTANCE = new CrossCGLibProxy();
    public static CrossCGLibProxy instance(){
        return INSTANCE;
    }

    public <T> T getProxy(Class<T> cls){
        return (T) Enhancer.create(cls,this);
    }

    @Override
    public Object intercept(
            Object obj,
            Method method,
            Object[] args,
            MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(obj,args);
        after();
        return result;
    }

    private void before(){
        System.out.println("before()");
    }

    private void after(){
        System.out.println("after()");
    }

    public static void main(String[] args) {

        //任何的类，都可以在运行时，通过生成类的子类，来完成动态代理类的创建
        //一行代码就可以创建制定类的代理类，爽吧
        Hello helloProxy = CrossCGLibProxy.instance().getProxy(HelloImpl.class);
        helloProxy.sayHello("suntao");
        helloProxy.sayGoodBye("suntao");
        helloProxy.sayYes("suntao");

    }
}
