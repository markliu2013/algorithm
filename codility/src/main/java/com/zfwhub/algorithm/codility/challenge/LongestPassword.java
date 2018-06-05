package com.zfwhub.algorithm.codility.challenge;

/**
 * https://app.codility.com/programmers/lessons/90-tasks_from_indeed_prime_2015_challenge/longest_password/
 */
public class LongestPassword {
    
    public static int solution(String S) {
        String[] words = S.split(" ");
        int maxLength = -1;
        for (int i = 0; i < words.length; i++) {
            char[] chars = words[i].toCharArray();
            int letterCount = 0;
            int digitCount = 0;
            boolean isValid = true;
            for (int j = 0; j < chars.length; j++) {
                if (Character.isDigit(chars[j])) {
                    digitCount++;
                } else if (Character.isLetter(chars[j])) {
                    letterCount++;
                } else {
                    isValid = false;
                    break;
                }
            }
            if (isValid && letterCount % 2 == 0 && digitCount % 2 != 0) {
                maxLength = Math.max(maxLength, chars.length);
            }
        }
        return maxLength;
    }
    
    public static void main(String[] args) {
        String S = "test 5 a0A pass007 ?xy1";
        System.out.println(LongestPassword.solution(S));
    }

}
