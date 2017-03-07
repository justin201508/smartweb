package com.arcvideo.smartweb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射工具类，用于基于反射实例化托管类
 *
 * Created by st@arcvideo.com on 2017/3/6.
 */
public class ReflectionUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    public static Object newInstance(Class<?> cls){
        Object instance;
        try{
            instance = cls.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException("class : " + cls.getName() + " create instance failed!",e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("class : " + cls.getName() + " illegal access !",e);
        }
        return instance;
    }

    public static Object invoke(Object obj, Method method, Object... args){
        Object result = null;
        try{
            method.setAccessible(true);
            result = method.invoke(obj, args);
        } catch (Exception e) {
            LOGGER.error("invoke method failed,class : " + obj.getClass().getName(),e);
            e.printStackTrace();
        }
        return result;
    }

    public static void setFieldValue(Object obj, Field field, Object value){
        try{
            field.setAccessible(true);
            field.set(obj,value);
        } catch (IllegalAccessException e) {
            LOGGER.error("set value failed",e);
            e.printStackTrace();
        }
    }

}
