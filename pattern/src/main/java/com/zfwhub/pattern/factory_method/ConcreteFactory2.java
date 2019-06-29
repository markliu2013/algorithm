package com.zfwhub.pattern.factory_method;

public class ConcreteFactory2 implements AbstractFactory {
    
    @Override
    public Product newProduct() {
        System.out.println("具体工厂2生成-->具体产品2...");
        return new ConcreteProduct2();
    }

}
