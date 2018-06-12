package com.zfwhub.algorithm.leetcode.string;

/**
 * https://leetcode.com/problems/short-encoding-of-words/description/
 */
public class ShortEncodingOfWords {
    
    // TODO ShortEncodingOfWords
    public int minimumLengthEncoding(String[] words) {
        String maxString = words[0];
        for (int i = 1; i < words.length; i++) {
            if (maxString.length() < words[i].length()) {
                maxString = words[i];
            }
        }
        return 0;
    }
    
    public int encodeTwo(String str1, String str2) {
        if (str1.indexOf(str2) >= 0) {
            return 0;
        } else {
            
        }
        return 0;
    }
    
    public static void main(String[] args) {
        String[] words = new String[]{"time", "me", "bell"};
        ShortEncodingOfWords shortEncodingOfWords = new ShortEncodingOfWords();
        int result = shortEncodingOfWords.minimumLengthEncoding(words);
//      System.out.println(result);
        int result2 = shortEncodingOfWords.encodeTwo("time", "tim");
        System.out.println(result2);
    }
}
