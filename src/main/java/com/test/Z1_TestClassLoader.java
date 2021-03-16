package com.test;

import java.net.URL;
import java.net.URLClassLoader;

public class Z1_TestClassLoader {
    public static void main(String[] args) throws Exception {
        URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL("https://gitee.com/xutao_ustc/ClassLoaderJarsHub/raw/master/basic/JustProvideClass-1.0.jar")});

        Class<?> clazz = classLoader.loadClass("com.test.TestClass");
        Object object = clazz.newInstance();
        System.out.println( clazz.getMethod("hello", String.class).invoke(object, "fun") );
    }
}
