package com.zfwhub.algorithm.ml.hello;

import java.util.StringTokenizer;

public class Test {

    public static void main(String[] args) {
        String str = "http||123";
        str.split("");
        StringTokenizer sk = new StringTokenizer(str, "||");
        System.out.println(sk.nextToken());
    }
    
}
