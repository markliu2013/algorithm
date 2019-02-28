package com.zfwhub.algorithm.code;

import java.util.regex.*;

public class Generator {
    
    private final static String workingDir = "/Users/liumingwei/algorithm/";
    
    public static void main(String[] args) {
        String re=generate("CountTriangles.java", "com.zfwhub.algorithm.codility.caterpillar_method");
        System.out.println(re);
    }
    
    public static String generate(String name, String packageName) {
        JavaFile javaFile = new JavaFile();
        String fileName = Utils.searchFile(workingDir, name, packageName);
        if (fileName.isEmpty()) {
            return "";
        }
        dfs(fileName, packageName, javaFile);
        return javaFile.generate();
    }
    
    public static void dfs(String fileName, String packageName, JavaFile javaFile) {
        String fileContent = Utils.readAllToString(fileName);
        // 处理import语句
        Pattern patternJavaImport = Pattern.compile("import (java..*?);");
        Matcher matcherJavaImport = patternJavaImport.matcher(fileContent);
        while (matcherJavaImport.find()) {
            javaFile.imports.add(matcherJavaImport.group(1));
        }
        Pattern patternDependImport = Pattern.compile("import ([^java]..*?);");
        Matcher matcherDependImport = patternDependImport.matcher(fileContent);
        while (matcherDependImport.find()) {
            System.out.println(matcherDependImport.group(1));
        }
    }

}
