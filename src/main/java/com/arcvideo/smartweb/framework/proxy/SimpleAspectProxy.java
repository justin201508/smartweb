package com.arcvideo.smartweb.framework.proxy;

import com.arcvideo.smartweb.framework.annotation.Aspect;
import com.arcvideo.smartweb.framework.annotation.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Created by st@arcvideo.com on 2017/3/8.
 */
@Aspect(value=Controller.class)
public class SimpleAspectProxy extends AspectProxy{

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleAspectProxy.class);

    @Override
    void after(Class<?> targetClass, Method targetMethod, Object[] methodParams, Object result) throws Throwable {
        LOGGER.debug("SimpleAspectProxy.after");
    }

    @Override
    void before(Class<?> targetClass, Method targetMethod, Object[] methodParams) throws Throwable {
        LOGGER.debug("SimpleAspectProxy.before");
    }
}
