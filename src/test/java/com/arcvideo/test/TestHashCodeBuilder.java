package com.arcvideo.test;

import com.arcvideo.smartweb.framework.bean.Request;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by st@arcvideo.com on 2017/3/7.
 */
public class TestHashCodeBuilder {

    public static void main(String[] args) {

        //关于reflectionHashCode,if attribute is all same,will return same hashcode
        Request req = new Request("get","/query/product");
        int hashcode = HashCodeBuilder.reflectionHashCode(req);
        System.out.println(hashcode);

        Request req2 = new Request("get","/query/product");
        hashcode = HashCodeBuilder.reflectionHashCode(req);
        System.out.println(hashcode);
    }
}
