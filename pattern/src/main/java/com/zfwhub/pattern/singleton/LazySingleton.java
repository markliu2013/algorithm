package com.zfwhub.pattern.singleton;

// 懒汉式单例
public class LazySingleton {
    
    private static volatile LazySingleton instance = null; //保证 instance 在所有线程中同步

    private LazySingleton() { }

    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}