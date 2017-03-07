package com.arcvideo.smartweb.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by st@arcvideo.com on 2017/3/6.
 */
public class ArrayUtil {

    public static boolean isNotEmpty(Object[] arr){
        return ArrayUtils.isNotEmpty(arr);
    }

    public static boolean isEmpty(Object[] arr){
        return ArrayUtils.isEmpty(arr);
    }
}
