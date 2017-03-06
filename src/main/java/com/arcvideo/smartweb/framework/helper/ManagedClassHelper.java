package com.arcvideo.smartweb.framework.helper;

import com.arcvideo.smartweb.framework.ConfigHelper;
import com.arcvideo.smartweb.framework.annotation.Controller;
import com.arcvideo.smartweb.framework.annotation.Service;
import com.arcvideo.smartweb.util.ClassUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * 托管类助手
 * Created by st@arcvideo.com on 2017/3/6.
 */
public class ManagedClassHelper {

    /**
     * 托管类集合,只读，初始化后不能修改
     */
    private static final Set<Class<?>> CLASS_SET;

    static{
        String basePackage = ConfigHelper.getAppBasePack();
        CLASS_SET = ClassUtil.getClasses(basePackage);
    }

    public static Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }

    /**
     * 返回所有的Service类
     * @return  Service类集合
     */
    public static Set<Class<?>> getServiceClassSet(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for(Class<?> cls : CLASS_SET){
            if(cls.isAnnotationPresent(Service.class)){
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * 返回所有的Controller类
     * @return Controller类集合
     */
    public static Set<Class<?>> getControllerClassSet(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for(Class<?> cls : CLASS_SET){
            if(cls.isAnnotationPresent(Controller.class)){
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * 返回所有的托管的类集合
     * @return  托管的类集合
     */
    public static Set<Class<?>> getMBeanClassSet(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        classSet.addAll(getControllerClassSet());
        classSet.addAll(getServiceClassSet());
        return classSet;
    }
}
