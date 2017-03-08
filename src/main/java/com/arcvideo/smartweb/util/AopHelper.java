package com.arcvideo.smartweb.util;

import com.arcvideo.smartweb.framework.annotation.Aspect;
import com.arcvideo.smartweb.framework.helper.ManagedClassHelper;
import com.arcvideo.smartweb.framework.proxy.AspectProxy;
import com.arcvideo.smartweb.framework.proxy.Proxy;
import com.arcvideo.smartweb.framework.proxy.ProxyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * Created by st@arcvideo.com on 2017/3/8.
 */
public class AopHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(AopHelper.class);

    static{
        try{
            // 在托管class集合中搜集代理映射[proxy -> target class set]
            Map<Class<?>,Set<Class<?>>> proxyMap = createProxyMap();
            // 基于代理映射，创建托管类迎合[target class -> proxy list]
            Map<Class<?>,List<Proxy>> targetMap = createTargetMap(proxyMap);
            // 遍历目标类和代理列表，创建目标类的代理对象，写入BEAN MAP
            for(Map.Entry<Class<?>,List<Proxy>> entry : targetMap.entrySet()){
                Class<?> targetClass = entry.getKey();
                List<Proxy> proxyList = entry.getValue();
                Object proxy = ProxyManager.createProxy(targetClass,proxyList);
                BeanHelper.setBean(targetClass,proxy);
            }
        }
        catch(Exception e){
            LOGGER.error("aop context init failed!",e);
        }
    }

    private static Map<Class<?>,List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap) {
        Map<Class<?>,List<Proxy>> targetMap = new HashMap<Class<?>,List<Proxy>>();

        return targetMap;
    }


    /**
     * 创建代理集合
     * @return
     * @throws Exception
     */
    public static Map<Class<?>,Set<Class<?>>> createProxyMap() throws Exception{
        Map<Class<?>,Set<Class<?>>> proxyMap = new HashMap<Class<?>,Set<Class<?>>>();
        //从托管类集合中获取切面类集合
        Set<Class<?>> proxyClassSet = ManagedClassHelper.getClassSetBySuper(AspectProxy.class);

        for(Class<?> proxyClass : proxyClassSet){
            if(proxyClass.isAnnotationPresent(Aspect.class)){
                Aspect aspect = proxyClass.getAnnotation(Aspect.class);
                Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
                proxyMap.put(proxyClass,targetClassSet);
            }
        }
        return proxyMap;
    }

    private static Set<Class<?>> createTargetClassSet(Aspect aspect) {
        Set<Class<?>> targetClassSet = new HashSet<Class<?>>();
        Class<? extends Annotation> annotation = aspect.value();
        if(annotation != null && !annotation.equals(Aspect.class)){
            //如果你的AspectProxy的@Aspect的value是Controller.class
            //这个拦截器就会拦截所有的Controller类，并在运行时生成这些类的代理，从而实现链式的拦截器框架
            targetClassSet.addAll(ManagedClassHelper.getClassSetByAnntation(annotation));
        }
        return targetClassSet;
    }

}
