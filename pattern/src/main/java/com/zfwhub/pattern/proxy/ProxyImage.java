package com.zfwhub.pattern.proxy;

// 静态代理类
public class ProxyImage implements Image {
    
    //代理类中的真实对象  
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        // 校验权限，打印日志...
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
        // 释放资源...
    }

}
