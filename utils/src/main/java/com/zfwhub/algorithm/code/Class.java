package com.zfwhub.algorithm.code;

import java.util.Set;

public class Class {
    
    public String modifier; // 修饰语
    public boolean isStatic;
    public String name;
    public Set<Filed> fileds;
    public Set<Method> methods;
    public Set<Class> innerClasses;
    
    public String generate() {
        StringBuilder sb = new StringBuilder();
        sb.append(modifier);
        if (isStatic) sb.append("static");
        sb.append("class");
        sb.append(name);
        sb.append("{");
        
        for (Filed filed : fileds) {
            sb.append(filed.generate());
        }
        
        for (Method method : methods) {
            sb.append(method.generate());
        }
        
        for (Class clazz : innerClasses) {
            sb.append(clazz.generate());
        }
        
        sb.append("}");
        return sb.toString();
    }

}
