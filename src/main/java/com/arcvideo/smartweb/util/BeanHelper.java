package com.arcvideo.smartweb.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static javafx.scene.input.KeyCode.T;

/**
 * 托管Bean助手
 * Created by st@arcvideo.com on 2017/3/6.
 */
public class BeanHelper {

    /**
     * 托管Bean集合，key是Class,Value是预先创建的托管对象
     */
    private static final Map<Class<?>,Object> BEAN_MAP = new HashMap<Class<?>,Object>();


    static{
        //在类加载的时候完成BeanMap的初始化，之后无法修改
        Set<Class<?>> beanClassSet = new HashSet<Class<?>>();
        for(Class<?> cls : beanClassSet){
            Object bean = ReflectionUtil.newInstance(cls);
            BEAN_MAP.put(cls,bean);
        }
    }

    /**
     * 获取BeanMap
     * @return
     */
    public static Map<Class<?>,Object> getBeanMap(){
        return BEAN_MAP;
    }

    public static <T> T getBean(Class<T> cls){
        if(BEAN_MAP.containsKey(cls)){
            throw new IllegalArgumentException("can not get bean by class : " + cls.getName());
        }
        return (T) BEAN_MAP.get(cls);
    }

}
