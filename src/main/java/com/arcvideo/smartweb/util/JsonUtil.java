package com.arcvideo.smartweb.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by st@arcvideo.com on 2017/3/7.
 */
public final class JsonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> String toJson(T obj){
        String json = null;
        try{
            json = OBJECT_MAPPER.writeValueAsString(obj);
        }
        catch(Exception e){
            LOGGER.error("object to json string failed,class : " + obj.getClass());
            throw new RuntimeException(e);
        }
        return json;
    }

}
