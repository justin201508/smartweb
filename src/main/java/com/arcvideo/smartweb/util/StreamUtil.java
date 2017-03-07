package com.arcvideo.smartweb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by st@arcvideo.com on 2017/3/7.
 */
public final class StreamUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(StreamUtil.class);

    public static String getString(InputStream ins){
        StringBuilder builder = new StringBuilder();
        try{
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(ins));
            String line;
            while((line = bufReader.readLine()) != null){
                builder.append(line);
            }
        } catch (IOException e) {
            LOGGER.error("get string from input stream failed!",e);
            throw new RuntimeException(e);
        }
        return builder.toString();
    }

}
