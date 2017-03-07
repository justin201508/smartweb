package com.arcvideo.smartweb.framework.bean;

import java.lang.reflect.Method;

/**
 * Created by st@arcvideo.com on 2017/3/7.
 */
public class Handler {

    /**
     * controller class
     */
    private Class<?> controllerClass;
    /**
     * action method in controllerClass
     */
    private Method action;

    public Handler(Class<?> controllerClass,Method action){
        this.action = action;
        this.controllerClass = controllerClass;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getAction() {
        return action;
    }
}
