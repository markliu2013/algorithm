package com.zfwhub.algorithm.code;

import java.util.*;

public class JavaFile {
    
    public Set<String> imports;
    public Set<Class> classes;
    
    public JavaFile() {
        imports = new LinkedHashSet<>();
        classes = new LinkedHashSet<>();
    }
    
    public String generate() {
        StringBuilder sb = new StringBuilder();
        for (String importStr : imports) {
            sb.append("import ");
            sb.append(importStr);
            sb.append(";");
            sb.append(System.lineSeparator());
        }
        for (Class clazz : classes) {
            sb.append(clazz.generate());
        }
        return sb.toString();
    }

}
