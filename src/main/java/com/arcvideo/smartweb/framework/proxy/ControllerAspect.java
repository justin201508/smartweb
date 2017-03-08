package com.arcvideo.smartweb.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Created by st@arcvideo.com on 2017/3/8.
 */
public class ControllerAspect extends AspectProxy{

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);

    private Long begin;

    @Override
    public void before(Class<?> cls , Method m,Object[] params) throws Throwable{
        LOGGER.debug("----------------begin-----------------");
        LOGGER.debug(String.format("class : %s",cls.getName()));
        LOGGER.debug(String.format("method : %s",m.getName()));

        begin = System.currentTimeMillis();
    }

    @Override
    public void after(Class<?> cls , Method m,Object[] params,Object result) throws Throwable{
        LOGGER.debug(String.format("time : %dms",System.currentTimeMillis()-begin));

        LOGGER.debug("----------------end-----------------");
    }
}
