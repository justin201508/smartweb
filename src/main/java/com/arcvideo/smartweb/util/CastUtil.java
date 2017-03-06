package com.arcvideo.smartweb.util;

import org.apache.commons.collections.functors.FalsePredicate;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by st@arcvideo.com on 2017/3/6.
 */
public class CastUtil {

    public static String castString(Object obj){
        return CastUtil.castString(obj,"");
    }
    public static String castString(Object obj,String defaultValue){
        return obj != null ? String.valueOf(obj) : defaultValue;
    }

    public static double castDouble(Object obj){
        return CastUtil.castDouble(obj,0d);
    }
    public static double castDouble(Object obj,double defaultValue){
        double doubleVal = defaultValue;
        if (obj != null){
            String strVal = castString(obj);
            if(StringUtils.isNotEmpty(strVal)){
                try{
                    doubleVal = Double.parseDouble(strVal);
                }catch (NumberFormatException ex){}
            }
        }
        return doubleVal;
    }

    public static long castLong(Object obj){
        return CastUtil.castLong(obj,0l);
    }
    public static long castLong(Object obj,long defaultValue){
        long longVal = defaultValue;
        if (obj != null){
            String strVal = castString(obj);
            if(StringUtils.isNotEmpty(strVal)){
                try{
                    longVal = Long.parseLong(strVal);
                }catch (NumberFormatException ex){}
            }
        }
        return longVal;
    }

    public static long castInt(Object obj){
        return CastUtil.castInt(obj,0);
    }
    public static int castInt(Object obj,int defaultValue){
        int intVal = defaultValue;
        if (obj != null){
            String strVal = castString(obj);
            if(StringUtils.isNotEmpty(strVal)){
                try{
                    intVal = Integer.parseInt(strVal);
                }catch (NumberFormatException ex){}
            }
        }
        return intVal;
    }
    public static boolean castBoolean(Object obj){
        return CastUtil.castBoolean(obj, Boolean.FALSE);
    }

    public static boolean castBoolean(Object obj,boolean defaultValue){
        boolean boolVal = defaultValue;
        if (obj != null){
            String strVal = castString(obj);
            if(StringUtils.isNotEmpty(strVal)){
                try{
                    boolVal = Boolean.parseBoolean(strVal);
                }catch (NumberFormatException ex){}
            }
        }
        return boolVal;
    }

}
