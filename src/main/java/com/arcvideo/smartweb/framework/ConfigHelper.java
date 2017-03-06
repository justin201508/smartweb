package com.arcvideo.smartweb.framework;

import com.arcvideo.smartweb.util.PropsUtil;

import java.util.Properties;

/**
 * Created by st@arcvideo.com on 2017/3/6.
 */
public class ConfigHelper {

    private static final Properties CONFIG_PROPS = PropsUtil.loadProps(FrameworkConstant.CONFIG_FILE);

    public static String getJdbcDriver(){
        return CONFIG_PROPS.getProperty(FrameworkConstant.JDBC_DRIVER);
    }
    public static String getJdbcUrl(){
        return CONFIG_PROPS.getProperty(FrameworkConstant.JDBC_URL);
    }
    public static String getJdbcUserName(){
        return CONFIG_PROPS.getProperty(FrameworkConstant.JDBC_USERNAME);
    }
    public static String getJdbcPassword(){
        return CONFIG_PROPS.getProperty(FrameworkConstant.JDBC_PASSWORD);
    }
    public static String getAppBasePack(){
        return CONFIG_PROPS.getProperty(FrameworkConstant.APP_BASE_PACK);
    }
    public static String getAppJspPath(){
        return CONFIG_PROPS.getProperty(FrameworkConstant.APP_JSP_PATH,"/WEB-INF/view");
    }
    public static String getAppAssetPath(){
        return CONFIG_PROPS.getProperty(FrameworkConstant.APP_ASSET_PATH,"/asset/");
    }
}
