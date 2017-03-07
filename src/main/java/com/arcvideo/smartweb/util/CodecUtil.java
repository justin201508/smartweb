package com.arcvideo.smartweb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by st@arcvideo.com on 2017/3/7.
 */
public class CodecUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodecUtil.class);

    public static String encodeURL(String source){
        String target = null;
        try{
            target = URLEncoder.encode(source,"UTF-8");

        } catch (UnsupportedEncodingException e) {
            LOGGER.error("string encode failed,string : " + source);
            throw new RuntimeException(e);
        }
        return target;
    }

    public static String decodeURL(String source){
        String target = null;
        try{
            target = URLDecoder.decode(source,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("string decode failed,string : " + source);
            throw new RuntimeException(e);
        }
        return target;
    }
}
