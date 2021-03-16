package com.test;

public class Z3_TestHotLoad {
    public static void main(String[] args) throws Exception {
        while(true){
            // 关键在于每次要新建一个ClassLoader，破除其中的缓存，否则热加载不会生效
            SelfDefinedClassLoader classLoader = new SelfDefinedClassLoader();

            Class<?> clazz = classLoader.loadClass("com.test.TestClass");
            Object object = clazz.newInstance();
            System.out.println( clazz.getMethod("hello", String.class).invoke(object, "fun") );

            Thread.sleep(1000);
        }
    }
}
