package com.arcvideo.smartweb.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A Request Action definition annotation
 * Created by st@arcvideo.com on 2017/3/6.
 */
@Target(ElementType.METHOD) //TARGET : METHOD,A REQUEST ACTION METHOD
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {
    String value(); // TO DEFINE A REQUEST PATH
}
