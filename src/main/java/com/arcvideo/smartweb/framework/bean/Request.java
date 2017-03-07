package com.arcvideo.smartweb.framework.bean;

import jdk.nashorn.internal.ir.RuntimeNode;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by st@arcvideo.com on 2017/3/7.
 */
public class Request {

    private String method;
    private String path;

    public Request(String method, String path){
        this.method = method;
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    @Override
    public int hashCode(){
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj){
        return EqualsBuilder.reflectionEquals(this,obj);
    }

}
