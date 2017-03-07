package com.arcvideo.smartweb.framework.bean;

import com.arcvideo.smartweb.util.CastUtil;

import java.util.Map;

/**
 * Created by st@arcvideo.com on 2017/3/7.
 */
public class Param {

    private Map<String,Object> paramMap;

    public Param(Map<String,Object> paramMap){
        this.paramMap = paramMap;
    }

    public Long getLong(String name){
        return CastUtil.castLong(paramMap.get(name));
    }

    public Map<String,Object> getMap(){
        return this.paramMap;
    }



}
