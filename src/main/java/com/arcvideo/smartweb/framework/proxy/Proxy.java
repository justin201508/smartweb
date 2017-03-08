package com.arcvideo.smartweb.framework.proxy;

/**
 * Created by st@arcvideo.com on 2017/3/7.
 */
public interface Proxy {

    Object doProxy(ProxyChain proxyChain) throws Throwable;

}
