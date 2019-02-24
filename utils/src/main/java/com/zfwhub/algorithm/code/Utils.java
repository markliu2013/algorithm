package com.zfwhub.algorithm.code;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {
    
    public static String searchFile(String dir, String fileName, String packageName) {
        StringBuilder sb = new StringBuilder();
        File file = new File(dir);
        if (file.isDirectory()) {
            searchDir(file, fileName, packageName, sb);
        }
        return sb.toString();
    }
    
    public static String readAllToString(String fileName) {
        try {
            return new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private static boolean searchDir(File file, String fileName, String packageName, StringBuilder result) {
        if (file.isDirectory()) {
            if (file.canRead()) {
                for (File temp : file.listFiles()) {
                    if (temp.isDirectory()) {
                        if (searchDir(temp, fileName, packageName, result)) {
                            return true;
                        }
                    } else {
                        if (fileName.equals(temp.getName())) {
                            if (temp.getAbsolutePath().indexOf(packageName.replaceAll("\\.", File.separator)) >= 0) {
                                result.append(temp.getAbsolutePath().toString());
                            }
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
