package com.arcvideo.smartweb.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by st@arcvideo.com on 2017/3/6.
 */
public class StringUtil {

    public static boolean isEmpty(String str){
        if(str != null){
            str.trim();
        }
        return StringUtils.isEmpty(str);
    }
}
