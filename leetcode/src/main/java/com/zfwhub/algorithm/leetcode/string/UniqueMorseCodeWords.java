package com.zfwhub.algorithm.leetcode.string;

import java.util.HashMap;
import java.util.HashSet;

/**
 * https://leetcode.com/problems/unique-morse-code-words/description/
 */
public class UniqueMorseCodeWords {
    
    public static int solution(String[] words) {
        String[] codes = new String[] {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        HashMap<Character, String> map = new HashMap<Character, String>();
        for (int i = 0; i < alphabet.length; i++) {
            map.put(alphabet[i], codes[i]);
        }
        HashSet<String> set = new HashSet<String>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            StringBuilder wordCoded = new StringBuilder();
            char[] chars = word.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                String code = map.get(chars[j]);
                wordCoded.append(code);
            }
            set.add(wordCoded.toString());
        }
        return set.size();
    }
    public static void main(String[] args) {
        String[] words = new String[] {"gin", "zen", "gig", "msg"};
        System.out.println(UniqueMorseCodeWords.solution(words));;
    }
}
