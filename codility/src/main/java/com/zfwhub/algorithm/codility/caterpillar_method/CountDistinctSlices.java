package com.zfwhub.algorithm.codility.caterpillar_method;

import java.util.HashSet;
// https://app.codility.com/programmers/lessons/15-caterpillar_method/count_distinct_slices/
public class CountDistinctSlices {
    
    public static int MAX = 1000000000;
    
    // Performance 0
    public static int solution1(int M, int[] A) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i; j < A.length; j++) {
                HashSet<Integer> set = new HashSet<Integer>();
                for (int k = i; k <= j; k++) {
                    if (set.contains(A[k])) {
                        break;
                    } else {
                        set.add(A[k]);
                    }
                }
                if (set.size() == (j - i) + 1) {
                    count++;
                    if (count > MAX) {
                        return MAX;
                    }
                }
            }
        }
        return count;
    }
    
    // Performance 40
    public static int solution2(int M, int[] A) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            HashSet<Integer> set = new HashSet<Integer>();
            set.add(A[i]);
            count++;
            if (count > MAX) {
                return MAX;
            }
            for (int j = i+1; j < A.length; j++) {
                if (set.contains(A[j])) { // 检测到重复的话，后面不用检测了。
                    break;
                } else {
                    set.add(A[j]);
                    count++;
                    if (count > MAX) {
                        return MAX;
                    }
                }
            }
        }
        return count;
    }
    
    
    public static int solution3(int M, int[] A) {
        // This solution is more clever, and much faster O(n)
        
        // main idea: 
        // use "boolean[]" to record if an integer is already seen 
        // also use "leftEnd" and "rightEnd"
        
        boolean[] seen = new boolean[M+1]; // from 0 to M 
        // Arrays.fill(seen, false); // note: "false" by default
        
        int leftEnd=0;
        int rightEnd=0;
        int numSlice =0;
        
        // key point: move the "leftEnd" and "rightEnd" of a slice
        while(leftEnd < A.length && rightEnd < A.length){
            
            // case 1: distinct (rightEnd)
            if( seen[A[rightEnd]] == false){ 
                // note: not just +1 
                // there could be (rightEnd - leftEnd + 1) combinations (be careful)
                numSlice = numSlice + (rightEnd - leftEnd + 1);
                if(numSlice >= 1_000_000_000)
                    return 1_000_000_000;
                
                // increase the slice to right by "1" (important)
                seen[A[rightEnd]] = true;
                rightEnd++; 
            }
            // case 2: not distinct
            else { 
                // decrease the slice from left by "1" (important)
                // remove A[leftEnd] from "seen" (be careful)
                seen[A[leftEnd]] = false;
                leftEnd++;
            } 
        }
        return numSlice;
    }
    
    public static void main(String[] args) {
        int[] A = new int[] {2,1,3,4,5,1,7};
        int M = 36;
        System.out.println(solution1(M, A));
        System.out.println(solution2(M, A));
        System.out.println(solution3(M, A));
    }

}
