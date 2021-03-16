package com.test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.SecureClassLoader;

class ChildFirstSelfDefinedClassLoader2 extends SecureClassLoader {
    private String jar;

    public ChildFirstSelfDefinedClassLoader2(String jar){
        this.jar = jar;
    }


    // loadClass -> findClass
    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            return loadClassWithoutExceptionHandling(name, resolve);
        }
    }

    protected Class<?> loadClassWithoutExceptionHandling(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> c = findLoadedClass(name);
        if (c == null) {
            c = findClass(name);

            if (c == null) {
                c = getParent().loadClass(name);
            }
        }
        if(resolve){
            resolveClass(c);
        }

        return c;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            String classpath = name.replace(".", "/").concat(".class");
            URL url = Thread.currentThread().getContextClassLoader().getResource(jar);
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
            return null;
        }
    }
}
