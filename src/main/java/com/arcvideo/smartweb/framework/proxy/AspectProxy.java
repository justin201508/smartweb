package com.arcvideo.smartweb.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 切面代理抽象类
 * Created by st@arcvideo.com on 2017/3/8.
 */
public abstract class AspectProxy implements  Proxy{

    private static final Logger LOGGER = LoggerFactory.getLogger(AspectProxy.class);


    /**
     * 这个方法里预埋了一些钩子方法，begin,end,before,after
     * 已被实现类选择性实现
     * @param chain
     * @return
     * @throws Throwable
     */
    @Override
    public final Object doProxy(ProxyChain chain) throws Throwable{
        Object result = null;
        Class<?> targetClass = chain.getTargetClass();
        Method targetMethod = chain.getTargetMethod();
        Object[] methodParams = chain.getMethodParams();

        begin();
        try{
            if(intercept(targetClass,targetMethod,methodParams)){
                // before intercept method
                before(targetClass,targetMethod,methodParams);
                // invoke proxy chain
                result = chain.doProxyChain();
                // after intercept method
                after(targetClass,targetMethod,methodParams,result);
            }
        }
        finally{
            end();
        }
        return result;
    }

    abstract void after(Class<?> targetClass, Method targetMethod, Object[] methodParams, Object result) throws Throwable;
    abstract void before(Class<?> targetClass, Method targetMethod, Object[] methodParams) throws Throwable;

    boolean intercept(Class<?> targetClass, Method targetMethod, Object[] methodParams) {
        return true;
    }

    private void begin() {
    }
    private void end() {
    }
}
