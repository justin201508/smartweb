package com.arcvideo.smartweb.framework.helper;

import com.arcvideo.smartweb.framework.annotation.Action;
import com.arcvideo.smartweb.framework.bean.Handler;
import com.arcvideo.smartweb.framework.bean.Request;
import com.arcvideo.smartweb.util.ArrayUtil;
import com.arcvideo.smartweb.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by st@arcvideo.com on 2017/3/7.
 */
public class ControllerHelper {

    public static final Map<Request,Handler> ACTION_MAP = new HashMap<Request,Handler>();

    //在类加载时初始化根据annotation配置的REQUEST -> ACTION MAPPING
    static{
        //从annotation中加载Controller class set
        Set<Class<?>> controllerClasses = ManagedClassHelper.getControllerClassSet();
        if(CollectionUtil.isNotEmpty(controllerClasses)){
            for(Class<?> controllerClass : controllerClasses){
                //获取controller方法
                Method[] methods = controllerClass.getDeclaredMethods();
                if(ArrayUtil.isNotEmpty(methods)){
                    for(Method m : methods){
                        //如果有方法包含action注解，表示这是一个action handler method
                        if(m.isAnnotationPresent(Action.class)){
                            Action action = m.getAnnotation(Action.class);
                            // mapping 定义在value属性中
                            String mapping = action.value();
                            // \w -> word -> [a-zA-Z0-9]
                            if(mapping.matches("\\w+:/\\w*")){
                                String[] reqArr = mapping.split(":");
                                if(ArrayUtil.isNotEmpty(reqArr) && reqArr.length == 2){
                                    String method = reqArr[0];
                                    String path = reqArr[1];
                                    Request req = new Request(method,path);
                                    Handler handler = new Handler(controllerClass,m);
                                    //MAP REQ -> HANDLER
                                    ACTION_MAP.put(req,handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static Handler getHandler(String method,String path){
        Request req = new Request(method,path);
        return ACTION_MAP.get(req);
    }

}
