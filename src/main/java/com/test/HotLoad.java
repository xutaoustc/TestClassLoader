package com.test;

public class HotLoad {
    public static void main(String[] args) throws Exception {
        while(true){
            SelfDefinedClassLoader classLoader = new SelfDefinedClassLoader();

            Class<?> clazz = classLoader.loadClass("com.test.TestClass");
            Object object = clazz.newInstance();
            System.out.println( clazz.getMethod("hello", String.class).invoke(object, "fun") );

            Thread.sleep(1000);
        }
    }
}
