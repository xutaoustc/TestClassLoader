package com.test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.SecureClassLoader;

public class Z2_TestSelfDefinedClassLoader {
    public static void main(String[] args) throws Exception {
        // 默认父加载器是AppClassLoader
        SelfDefinedClassLoader classLoader = new SelfDefinedClassLoader();

        Class<?> clazz = classLoader.loadClass("com.test.TestClass");
        Object object = clazz.newInstance();
        System.out.println( clazz.getMethod("hello", String.class).invoke(object, "fun") );
    }
}