package com.test;

public class Z5_TestMultiVersion {
    public static void main(String[] args) throws Exception{
        ChildFirstSelfDefinedClassLoader2 classLoader = new ChildFirstSelfDefinedClassLoader2("JustProvideClass-1.0.jar");
        Class<?> clazz = classLoader.loadClass("com.test.TestClass");
        Object object = clazz.newInstance();
        System.out.println( clazz.getMethod("hello", String.class).invoke(object, "fun") );

        ChildFirstSelfDefinedClassLoader2 classLoader2 = new ChildFirstSelfDefinedClassLoader2("JustProvideClass-1.0-v2.jar");
        Class<?> clazz2 = classLoader2.loadClass("com.test.TestClass");
        Object object2 = clazz2.newInstance();
        System.out.println( clazz2.getMethod("hello", String.class).invoke(object2, "fun") );
    }
}
