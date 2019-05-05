package com.zfwhub.algorithm.leetcode.number;
// https://leetcode.com/problems/valid-number/
// https://leetcode.windliang.cc/leetCode-65-Valid-Number.html
public class ValidNumber {

    public static boolean solution1(String s) {
        s = s.trim();
        // 按e分解
        String[] splitAsE = s.split("e");
        // 存在两个以上的e直接false
        if (splitAsE.length > 2 || splitAsE.length == 0) {
            return false;
        }
        if (s.endsWith("e")) {
            return false;
        }
        
        if (splitAsE[0].matches("^(-|\\+)*0+(\\d+$)")) {
            splitAsE[0] = splitAsE[0].replaceFirst("0+(?!$)", "");
        }
        if (splitAsE.length == 1) {
            return isCreatable(splitAsE[0]);
        }
        if (splitAsE[1].matches("^(-|\\+)*0+(\\d+$)")) {
            splitAsE[1] = splitAsE[1].replaceFirst("0+(?!$)", "");
        }
        
        // 判断e的左边 splitAsE[0]
        if (isCreatable(splitAsE[0]) && isCreatable(splitAsE[1])) {
            if (splitAsE[1].indexOf(".") >= 0) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean isCreatable(final String str) {
        if (str.equals("")) {
            return false;
        }
        final char[] chars = str.toCharArray();
        int sz = chars.length;
        boolean hasExp = false;
        boolean hasDecPoint = false;
        boolean allowSigns = false;
        boolean foundDigit = false;
        // deal with any possible sign up front
        final int start = chars[0] == '-' || chars[0] == '+' ? 1 : 0;
        if (sz > start + 1 && chars[start] == '0' && str.indexOf(".") < 0) { // leading 0, skip if is a decimal number
            if (chars[start + 1] == 'x' || chars[start + 1] == 'X') { // leading 0x/0X
                int i = start + 2;
                if (i == sz) {
                    return false; // str == "0x"
                }
                // checking hex (it can't be anything else)
                for (; i < chars.length; i++) {
                    if ((chars[i] < '0' || chars[i] > '9') && (chars[i] < 'a' || chars[i] > 'f') && (chars[i] < 'A' || chars[i] > 'F')) {
                        return false;
                    }
                }
                return true;
            } else if (Character.isDigit(chars[start + 1])) {
                // leading 0, but not hex, must be octal
                int i = start + 1;
                for (; i < chars.length; i++) {
                    if (chars[i] < '0' || chars[i] > '7') {
                        return false;
                    }
                }
                return true;
            }
        }
        sz--; // don't want to loop to the last char, check it afterwords
              // for type qualifiers
        int i = start;
        // loop to the next to last char or to the last char if we need another digit to
        // make a valid number (e.g. chars[0..5] = "1234E")
        while (i < sz || i < sz + 1 && allowSigns && !foundDigit) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                foundDigit = true;
                allowSigns = false;

            } else if (chars[i] == '.') {
                if (hasDecPoint || hasExp) {
                    // two decimal points or dec in exponent
                    return false;
                }
                hasDecPoint = true;
            } else if (chars[i] == 'e' || chars[i] == 'E') {
                // we've already taken care of hex.
                if (hasExp) {
                    // two E's
                    return false;
                }
                if (!foundDigit) {
                    return false;
                }
                hasExp = true;
                allowSigns = true;
            } else if (chars[i] == '+' || chars[i] == '-') {
                if (!allowSigns) {
                    return false;
                }
                allowSigns = false;
                foundDigit = false; // we need a digit after the E
            } else {
                return false;
            }
            i++;
        }
        if (i < chars.length) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                // no type qualifier, OK
                return true;
            }
            if (chars[i] == '.') {
                if (hasDecPoint || hasExp) {
                    // two decimal points or dec in exponent
                    return false;
                }
                // single trailing decimal point after non-exponent is ok
                return foundDigit;
            }
       
            // last character is illegal
            return false;
        }
        // allowSigns is true iff the val ends in 'E'
        // found digit it to make sure weird stuff like '.' and '1E-' doesn't pass
        return !allowSigns && foundDigit;
    }

    public static void main(String[] args) {
        String s1 = "+0619";
        if (s1.matches("^(-|\\+)*0+(\\d+$)")) {
            s1 = s1.replaceFirst("0+(?!$)", "");
        }
        System.out.println(s1);
        System.out.println(solution1(s1));
    }

}

