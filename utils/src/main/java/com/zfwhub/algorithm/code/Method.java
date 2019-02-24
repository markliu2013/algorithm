package com.zfwhub.algorithm.code;

public class Method {
    
    public String modifier; // 修饰语
    public boolean isStatic;
    public String returnType;
    public String name;
    public String body;
    
    public String generate() {
        StringBuilder sb = new StringBuilder();
        sb.append(modifier);
        if (isStatic) sb.append(" static");
        sb.append(" " + returnType);
        sb.append(" " + name);
        sb.append("{");
        sb.append(body);
        sb.append("}");
        return sb.toString();
    }
    
}
