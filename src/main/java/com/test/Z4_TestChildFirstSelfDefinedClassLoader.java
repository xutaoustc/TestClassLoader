package com.test;

public class Z4_TestChildFirstSelfDefinedClassLoader {
    public static void main(String[] args) throws Exception{
//        SelfDefinedClassLoader classLoader = new SelfDefinedClassLoader();      // 用这个类加载器加载不到jar包中的类
        ChildFirstSelfDefinedClassLoader classLoader = new ChildFirstSelfDefinedClassLoader();

        Class<?> clazz = classLoader.loadClass("com.test.TestClass");
        Object object = clazz.newInstance();
        System.out.println( clazz.getMethod("hello", String.class).invoke(object, "fun") );
    }
}
