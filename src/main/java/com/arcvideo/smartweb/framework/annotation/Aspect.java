package com.arcvideo.smartweb.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A ASPECT ANNOTATION
 * annotation target : TYPE
 * retention policy : RUNTIME
 *
 * Created by st@arcvideo.com on 2017/3/7.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
}
