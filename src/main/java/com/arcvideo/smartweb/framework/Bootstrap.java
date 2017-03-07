package com.arcvideo.smartweb.framework;

import com.arcvideo.smartweb.framework.helper.ControllerHelper;
import com.arcvideo.smartweb.framework.helper.ManagedClassHelper;
import com.arcvideo.smartweb.util.BeanHelper;
import com.arcvideo.smartweb.util.ClassUtil;
import com.arcvideo.smartweb.util.IocHelper;

/**
 * smartweb bootstrap class
 * Created by st@arcvideo.com on 2017/3/7.
 */
public class Bootstrap {

    public static void init(){
        Class<?>[] helperClasses = {
                ManagedClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };

        // load classes to finish framework init
        // 在框架启动时集中加载
        for(Class<?> cls : helperClasses){
            ClassUtil.loadClass(cls.getName(),true);
        }
    }

}
