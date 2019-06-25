package com.zfwhub.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

// 动态代理类
public class ImageHandler implements InvocationHandler {
    
    //代理类中的真实对象  
    private Object obj;
    
    public ImageHandler(Object obj) {
        this.obj = obj;
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 校验权限，打印日志...
        System.out.println("打印日志");
        Object invoke = method.invoke(obj, args);
        return invoke;
       // 释放资源...
    }
    
    

}
