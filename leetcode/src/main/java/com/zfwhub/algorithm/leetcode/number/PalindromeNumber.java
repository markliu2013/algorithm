package com.zfwhub.algorithm.leetcode.number;

import java.util.ArrayList;

/**
 * https://leetcode.com/problems/palindrome-number/description/
 */
public class PalindromeNumber {
    
    public static boolean solution(int x) {
        if (x < 0) {
            return false;
        }
        ArrayList<Integer> digitList = new ArrayList<Integer>();
        int currentDigit = x;
        while (currentDigit > 0) {
            digitList.add(currentDigit % 10);
            currentDigit = currentDigit / 10;
        }
        for (int i = 0; i < (digitList.size() / 2); i++) {
            if (digitList.get(i) != digitList.get(digitList.size()-i-1)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * https://leetcode.com/problems/palindrome-number/solution/
     */
    public static boolean solution2(int x) {
        // Special cases:
        // As discussed above, when x < 0, x is not a palindrome.
        // Also if the last digit of the number is 0, in order to be a palindrome,
        // the first digit of the number also needs to be 0.
        // Only 0 satisfy this property.
        if(x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        
        int revertedNumber = 0;
        while(x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        
        // When the length is an odd number, we can get rid of the middle digit by revertedNumber/10
        // For example when the input is 12321, at the end of the while loop we get x = 12, revertedNumber = 123,
        // since the middle digit doesn't matter in palidrome(it will always equal to itself), we can simply get rid of it.
        return x == revertedNumber || x == revertedNumber/10;
    }
    
    public static void main(String[] args) {
        System.out.println(PalindromeNumber.solution(1131));
    }

}
