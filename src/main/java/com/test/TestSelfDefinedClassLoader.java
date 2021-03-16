package com.test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.SecureClassLoader;

public class TestSelfDefinedClassLoader {
    public static void main(String[] args) throws Exception {
        SelfDefinedClassLoader classLoader = new SelfDefinedClassLoader();

        Class<?> clazz = classLoader.loadClass("com.test.TestClass");
        Object object = clazz.newInstance();
        System.out.println( clazz.getMethod("hello", String.class).invoke(object, "fun") );
    }
}


class SelfDefinedClassLoader extends SecureClassLoader{

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            String classpath = name.replace(".", "/").concat(".class");
            URL url = Thread.currentThread().getContextClassLoader().getResource("JustProvideClass-1.0.jar");
            URL classURL = new URL("jar:file:" + url.getPath() + "!/" + classpath);

            InputStream inputStream = classURL.openStream();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int code;
            while((code = inputStream.read()) != -1){
                buffer.write(code);
            }
            buffer.flush();
            byte[] targetArray = buffer.toByteArray();

            return this.defineClass(name, targetArray, 0, targetArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}