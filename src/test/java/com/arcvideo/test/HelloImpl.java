package com.arcvideo.test;

/**
 * Created by st@arcvideo.com on 2017/3/7.
 */
public class HelloImpl implements Hello{
    @Override
    public void sayHello(String name) {
        System.out.println("hello," + name);
    }

    @Override
    public void sayGoodBye(String name) {
        System.out.println("Goodbye," + name);
    }

    @Override
    public void sayYes(String name) {
        System.out.println("Yes," + name);
    }
}
