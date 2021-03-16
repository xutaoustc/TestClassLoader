package com.test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.SecureClassLoader;

class ChildFirstSelfDefinedClassLoader extends SecureClassLoader {

    // loadClass -> findClass
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if(name.startsWith("com.test")){
            return this.findClass(name);
        }

        return super.loadClass(name, resolve);
    }

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
