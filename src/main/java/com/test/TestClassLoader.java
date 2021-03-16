package com.test;

import java.net.URL;
import java.net.URLClassLoader;

public class TestClassLoader {
    public static void main(String[] args) throws Exception {
        URL jarPath = new URL("https://gitee.com/xutao_ustc/ClassLoaderJarsHub/raw/master/basic/JustProvideClass-1.0.jar");
        URLClassLoader classLoader = new URLClassLoader(new URL[]{jarPath});

        Class<?> clazz = classLoader.loadClass("com.test.TestClass");
        Object object = clazz.newInstance();
        System.out.println( clazz.getMethod("hello", String.class).invoke(object, "fun") );
    }
}
