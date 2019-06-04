package com.zfwhub.algorithm.leetcode.dp;

import java.util.*;

import com.zfwhub.algorithm.utils.ArrayUtil;
import com.zfwhub.algorithm.utils.CollectionUtil;
// https://leetcode.com/problems/minimum-score-triangulation-of-polygon/
public class Triangulation {
    
    // 超时 https://leetcode.com/submissions/detail/226873208/
    public static int solution1(int[] A) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            list.add(i);
        }
        List<List<Integer>> combinations = CollectionUtil.combine(list, 3);
        List<List<List<Integer>>> solutionList = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();
        dfs(solutionList, solution, combinations, A.length-2);
        int minProduct = Integer.MAX_VALUE;
        for (int i = 0; i < solutionList.size(); i++) {
            List<List<Integer>> currentSolution = solutionList.get(i);
            int currentProduct = 0;
            for (int j = 0; j < currentSolution.size(); j++) {
                List<Integer> triangle = currentSolution.get(j);
                currentProduct += A[triangle.get(0)] * A[triangle.get(1)] * A[triangle.get(2)];
            }
            minProduct = Math.min(minProduct, currentProduct);
        }
        /*
        for (int i = 0; i < solutionList.size(); i++) {
            List<List<Integer>> currentSolution = solutionList.get(i);
            int currentProduct = 0;
            for (int j = 0; j < currentSolution.size(); j++) {
                List<Integer> triangle = currentSolution.get(j);
                currentProduct += A[triangle.get(0)] * A[triangle.get(1)] * A[triangle.get(2)];
            }
            if (currentProduct == minProduct) {
                System.out.println(currentSolution);
            }
        }
        */
        return minProduct;
    }
    
    private static void dfs(List<List<List<Integer>>> solutionList, List<Integer> solution, List<List<Integer>> combinations, int n) {
        if (isASolution(solution, n)) {
            processSolution(solutionList, solution, combinations);
        } else {
            for (int i = 0; i < combinations.size(); i++) {
                if (isValid(solution, i, combinations)) {
                    makeMove(solution, i);
                    dfs(solutionList, solution, combinations, n);
                    unMakeMove(solution);
                }
            }
        }
    }
    
    private static boolean isASolution(List<Integer> solution, int n) {
        return solution.size() == n;
    }
    
    private static void processSolution(List<List<List<Integer>>> solutionList, List<Integer> solution, List<List<Integer>> combinations) {
        List<List<Integer>> solution2 = new ArrayList<>();
        for (int i = 0; i < solution.size(); i++) {
            solution2.add(new ArrayList<>(combinations.get(solution.get(i))));
        }
        solutionList.add(solution2);
    }
    
    // 必须间隔一个。
    private static boolean isValid(List<Integer> solution, int i, List<List<Integer>> combinations) {
        if (solution.size() == 0) {
            return true;
        }
        if (solution.get(solution.size()-1) < i) {
            for (int j = 0; j < solution.size(); j++) {
                if (check(combinations.get(solution.get(j)), combinations.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    private static void makeMove(List<Integer> solution, int i) {
        solution.add(i);
    }
    
    private static void unMakeMove(List<Integer> solution) {
        solution.remove(solution.size()-1);
    }
    
    // 有重叠返回true
    private static boolean check(List<Integer> triangle1, List<Integer> triangle2) {
        for (int i = 0; i < 3; i++) {
            int[] a = new int[2];
            if (i == 2) {
                a[0] = triangle1.get(0);
                a[1] = triangle1.get(i);
            } else {
                a[0] = triangle1.get(i);
                a[1] = triangle1.get(i+1);
            }
            for (int j = 0; j < 3; j++) {
                int[] b = new int[2];
                if (j == 2) {
                    b[0] = triangle2.get(0);
                    b[1] = triangle2.get(j);
                } else {
                    b[0] = triangle2.get(j);
                    b[1] = triangle2.get(j+1);
                }
                // 判断a和b是否相交
                if ((b[0] > a[0] && b[0] < a[1]) && b[1] > a[1]) {
                    return true;
                } else if ((a[0] > b[0] && a[0] < b[1]) && a[1] > b[1]) {
                    return true;
                }
            }
        }
        return false;
    }
    
    // 动态规划，递归。超时 https://leetcode.com/submissions/detail/228636456/
    public static int solution2(int[] A) {
        if (A.length == 3) {
            return A[0] * A[1] * A[2];
        }
        int minResult = Integer.MAX_VALUE;
        // 以下代码块处理连续的三个
        for (int i = 0; i < A.length-2; i++) {
            int[] newA = new int[A.length-1];
            System.arraycopy(A, i+2, newA, 0, newA.length-1-i);
            System.arraycopy(A, 0, newA, newA.length-1-i, i+1);
            minResult = Math.min(minResult, solution2(newA) + A[i] * A[i+1] * A[i+2]);
        }
        int[] newB = new int[A.length-1];
        System.arraycopy(A, 0, newB, 0, newB.length);
        minResult = Math.min(minResult, solution2(newB) + A[A.length-2] * A[A.length-1] * A[0]);
        int[] newC = new int[A.length-1];
        System.arraycopy(A, 1, newC, 0, newC.length);
        minResult = Math.min(minResult, solution2(newC) + A[A.length-1] * A[0] * A[1]);
        return minResult;
    }
    
    // 动态规划，递归+map。超时 https://leetcode.com/submissions/detail/227503937/
    public static int solution3(int[] A) {
        return solution3DP(A, new HashMap<>());
    }
    
    private static int solution3DP(int[] A, Map<DPMapKey, Integer> map) {
        if (A.length == 3) {
            return A[0] * A[1] * A[2];
        }
        int minResult = Integer.MAX_VALUE;
        // 以下代码块处理连续的三个
        for (int i = 0; i < A.length-2; i++) {
            int[] newA = new int[A.length-1];
            System.arraycopy(A, i+2, newA, 0, newA.length-1-i);
            System.arraycopy(A, 0, newA, newA.length-1-i, i+1);
            DPMapKey dpMapKey = new DPMapKey(newA);
            int newAResult = 0;
            if (map.containsKey(dpMapKey)) {
                newAResult = map.get(dpMapKey);
            } else {
                newAResult = solution3DP(newA, map);
                map.put(dpMapKey, newAResult);
            }
            minResult = Math.min(minResult, newAResult + A[i] * A[i+1] * A[i+2]);
        }
        int[] newB = new int[A.length-1];
        System.arraycopy(A, 0, newB, 0, newB.length);
        DPMapKey dpMapKey = new DPMapKey(newB);
        int newBResult = 0;
        if (map.containsKey(dpMapKey)) {
            newBResult = map.get(dpMapKey);
        } else {
            newBResult = solution3DP(newB, map);
            map.put(dpMapKey, newBResult);
        }
        minResult = Math.min(minResult, newBResult + A[A.length-2] * A[A.length-1] * A[0]);
        int[] newC = new int[A.length-1];
        System.arraycopy(A, 1, newC, 0, newC.length);
        DPMapKey dpMapKey2 = new DPMapKey(newC);
        int newCResult = 0;
        if (map.containsKey(dpMapKey2)) {
            newCResult = map.get(dpMapKey2);
        } else {
            newCResult = solution3DP(newC, map);
            map.put(dpMapKey2, newCResult);
        }
        minResult = Math.min(minResult, newCResult + A[A.length-1] * A[0] * A[1]);
        return minResult;
    }
    
    private static class DPMapKey {
        
        public int[] A;

        public DPMapKey(int[] a) {
            A = a;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + Arrays.hashCode(A);
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            DPMapKey other = (DPMapKey) obj;
            if (!Arrays.equals(A, other.A))
                return false;
            return true;
        }
        
    }
    
    private static class DPStatus {
        
        public List<Integer> list;
        
        public DPStatus() {
            list = new ArrayList<>();
        }
        
    }
    // https://leetcode.com/submissions/detail/227582800/
    // 动态规划，递推+map。https://leetcode.com/submissions/detail/227898364/
    public static int solution4(int[] A) {
        if (A.length == 3) {
            return A[0] * A[1] * A[2];
        }
        int minResult = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            int a = i !=0 ? i-1 : A.length-1;
            int b = i;
            int c = i != A.length-1 ? i+1 : 0;
            int[] newA = ArrayUtil.removeByIndex(A, i);
            minResult = Math.min(minResult, solution4(newA) + A[a] * A[b] * A[c]);
        }
        return minResult;
    }
    
    // 失败
    public static int solution5(int[] A) {
        // 第一维：A的长度，第二维：以A的每个点为中间顶点。
        int[][] dp = new int[A.length][A.length];
        
        for (int i = 2; i < A.length; i++) {
            int[] preDp = dp[i-1];
            // 现在要求i这个长度，j为中间顶点的。
            for (int j = 0; j < A.length; j++) {
                int a = j !=0 ? j-1 : A.length-1;
                int b = j;
                int c = j != A.length-1 ? j+1 : 0;
                // 除掉j这个索引，找preDp的最小值
                int minPreDp = Integer.MAX_VALUE;
                for (int k = 0; k < j; k++) {
                    minPreDp = Math.min(minPreDp, preDp[k]);
                }
                for (int k = j+1; k < preDp.length; k++) {
                    minPreDp = Math.min(minPreDp, preDp[k]);
                }
                dp[i][j] = minPreDp + A[a] * A[b] * A[c];
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return ArrayUtil.min(dp[dp.length-1]);
    }
    
    // 失败
    public static int solution6(int[] A) {
        if (A.length == 3) {
            return A[0] * A[1] * A[2];
        }
        DPStatus[] dp = new DPStatus[A.length];
        dp[3] = new DPStatus();
        for (int i = 0; i < 4; i++) {
            int a = i !=0 ? i-1 : 3;
            int b = i;
            int c = i != 3 ? i+1 : 0;
            int d = c+1 == 4 ? 0 : c+1;
            dp[3].list.add(A[a]*A[b]*A[c] + A[a]*A[c]*A[d]);
        }
        for (int i = 4; i < A.length; i++) {
            DPStatus preDp = dp[i-1];
            dp[i] = new DPStatus();
            for (int j = 0; j < i+1; j++) {
                int a = j !=0 ? j-1 : i;
                int b = j;
                int c = j != i ? j+1 : 0;
                dp[i].list.add(A[a]*A[b]*A[c] + Collections.min(preDp.list));
            }
        }
        return Collections.min(dp[dp.length-1].list);
    }
    
    public static int solution7(int[] A) {
        return solution7DP(A, 0, 0);
    }
    
    public static int solution7DP(int[] A, int i, int j) {
        if (j == 0) j = A.length - 1;
        int minResult = 0;
        for (int k = i+1; k < j; ++k) {
            minResult = Math.min(minResult==0?Integer.MAX_VALUE:minResult, solution7DP(A, i, k) + A[i] * A[k] * A[j] + solution7DP(A, k, j));
        }
        return minResult;
    }
    
    public static void main(String[] args) {
//        List<Integer> triangle1 = new ArrayList<>();
//        triangle1.add(1);
//        triangle1.add(2);
//        triangle1.add(4);
//        List<Integer> triangle2 = new ArrayList<>();
//        triangle2.add(1);
//        triangle2.add(3);
//        triangle2.add(4);
//        System.out.println(check(triangle1, triangle2));
//        
//        int[] A = new int[] {35,73,90,27,71,80,21,33,33,13,48,12,68,70,80,36,66,3,70,58};
        int[] A = new int[] {3,1,4,5,4};
//        int[] A = ArrayUtil.newIntArray(1, 51);
        System.out.println(solution1(A));
        System.out.println(solution7(A));
    }
    
}
