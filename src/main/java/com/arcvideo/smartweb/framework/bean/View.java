package com.arcvideo.smartweb.framework.bean;

import java.util.Map;

/**
 * Created by st@arcvideo.com on 2017/3/7.
 */
public class View {

    private String path;
    private Map<String,Object> model;

    public View(String path,Map<String,Object> model){
        this.path = path;
        this.model = model;
    }

    public View addModel(String key,Object value){
        model.put(key,value);
        return this;
    }

    public Map<String,Object> getModel(){
        return this.model;
    }



}
