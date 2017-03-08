package com.arcvideo.smartweb.framework.annotation;

import java.lang.annotation.*;

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
    Class<? extends Annotation> value();
}
