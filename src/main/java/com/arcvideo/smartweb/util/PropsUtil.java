package com.arcvideo.smartweb.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by st@arcvideo.com on 2017/3/6.
 */
public class PropsUtil {

    public static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    public static Properties loadProps(String fileName){
        Properties props = null;
        InputStream ins = null;
        try{
            ins = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if (ins == null){
                throw new FileNotFoundException("file : " + fileName + "is not exists!");
            }
            props = new Properties();
            props.load(ins);
        }catch (IOException ex){
            LOGGER.error("load properties file : " + fileName + " failed!",ex);
        }
        finally {
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    LOGGER.error("close inputstream failure!",e);
                }
            }
        }
        return props;
    }

    public static String getProperty(Properties props,String key,String defaultValue){
        String value = defaultValue;
        if(props.containsKey(key)){
            return props.getProperty(key);
        }
        return value;
    }

    public static String getProperty(Properties props,String key){
        if(props.containsKey(key)){
            return props.getProperty(key);
        }
        return null;
    }

}
