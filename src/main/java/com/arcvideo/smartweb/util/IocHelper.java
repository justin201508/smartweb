package com.arcvideo.smartweb.util;


import com.arcvideo.smartweb.framework.annotation.Inject;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 依赖注入助手
 * Created by st@arcvideo.com on 2017/3/6.
 */
public class IocHelper {

    static{
        //实际上就是利用反射工具，实现对象的装配
        Map<Class<?>,Object> beanMap = BeanHelper.getBeanMap();
        if(CollectionUtil.isNotEmpty(beanMap)){
            for(Map.Entry<Class<?>,Object> entry : beanMap.entrySet()){
                Class<?> beanClass = entry.getKey();
                Object bean = entry.getValue();
                Field[] fields = beanClass.getDeclaredFields();
                if(ArrayUtil.isNotEmpty(fields)){
                    for(Field beanField : fields){
                        if(beanField.isAnnotationPresent(Inject.class)){
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if(beanFieldInstance != null){
                                //根据反射机制设置依赖注入的对象
                                ReflectionUtil.setFieldValue(bean,beanField,beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }

    }
}
