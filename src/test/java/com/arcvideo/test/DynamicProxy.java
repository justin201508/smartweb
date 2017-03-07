package com.arcvideo.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by st@arcvideo.com on 2017/3/7.
 */
public class DynamicProxy implements InvocationHandler {

    private Object target;

    public DynamicProxy(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target,args);
        after();
        return result;
    }

    public <T> T getProxy(){
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }

    private void before(){
        System.out.println("before()");
    }

    private void after(){
        System.out.println("after()");
    }


    public static void main(String[] args) {
        Hello target = new HelloImpl();

        //把拦截的代码放在动态代理里
        DynamicProxy dynamicProxy = new DynamicProxy(target);

        //通过被代理类的类加载器和接口，重建一个接口的实现类
        //ASPECT逻辑代码就定义在这里
        Hello helloProxy = dynamicProxy.getProxy();

        //在真正的代理类里，没有什么代码，就只调用了动态代理里的回调方法
        //并且通过反射机制调用被代理类的代码

        //动态代理最大的优点就是，它是在运行时，通过反射机制调用被代理对象的接口方法的
        //因此，当接口变化了，增减了，都无需修改代理代码，这也正是基于接口的Proxy的优势
        helloProxy.sayHello("suntao");
        helloProxy.sayGoodBye("suntao");
    }

}
