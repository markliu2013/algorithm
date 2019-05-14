package com.zfwhub.algorithm.leetcode.contest136;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/contest/weekly-contest-136/problems/flower-planting-with-no-adjacent/
public class FlowerPlantingWithNoAdjacent {
    
    // 回溯算法 https://leetcode.com/submissions/detail/228349623/ 超时
    public static int[] solution1(int N, int[][] paths) {
        int[] result = new int[N];
        List<Integer> solution = new ArrayList<>();
        dfs(result, solution, N, paths);
        return result;
    }
    
    // 一开始的时候每个点都能放1，2，3，4 
    // 随着动态增加path，每个点的情况去适应变化。
    public static int[] solution2(int N, int[][] paths) {
        Map<Integer, List<Integer>> solution = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);list.add(2);list.add(3);list.add(4);
        for (int i = 1; i <= N; i++) {
           solution.put(i, new ArrayList<>(list));
        }
        List<Path> pathList = processPaths(paths);
        for (int i = 0; i < pathList.size(); i++) {
            Path path = pathList.get(i);
            solution.get(path.y).remove(solution.get(path.x).get(0));
        }
        int[] result = new int[N];
        for (Integer key : solution.keySet()) {
            result[key-1] = solution.get(key).get(0);
        }
        return result;
    }
    
    // 这个算法失败了
    public static int[] solution3(int N, int[][] paths) {
        int[] result = new int[N];
        for (int i = 0; i < result.length; i++) {
            result[i] = 1;
        }
        List<Path> pathList = processPaths(paths);
        int currentGarden = 1;
        result[currentGarden-1] = 1;
        System.out.println(pathList);
        for (int i = 0; i < pathList.size(); i++) {
            Path path = pathList.get(i);
            if (path.x == currentGarden) {
                result[path.y-1] = result[currentGarden-1]+1;
            } else {
                currentGarden = path.x;
                if (result[path.y-1] == result[currentGarden-1]) {
                    result[path.y-1] = result[path.y-1]+1;
                }
            }
        }
        return result;
    }
    
    public static List<Path> processPaths(int[][] paths) {
        List<Path> list = new ArrayList<>();
        for (int i = 0; i < paths.length; i++) {
            int x = Math.min(paths[i][0], paths[i][1]);
            int y = Math.max(paths[i][0], paths[i][1]);
            Path path = new Path(x, y);
            list.add(path);
        }
        Collections.sort(list);
        return list;
    }
    
    private static class Path implements Comparable<Path> {
        
        public int x;
        public int y;
        
        public Path(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
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
            Path other = (Path) obj;
            if (x != other.x)
                return false;
            if (y != other.y)
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "Path [x=" + x + ", y=" + y + "]";
        }

        @Override
        public int compareTo(Path o) {
            if (this.x > o.x) {
                return 1;
            } else if (this.x < o.x) {
                return -1;
            } else {
                if (this.y > o.y) {
                    return 1;
                } else if (this.y < o.y) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
        
    }
    
    private static boolean dfs(int[] result, List<Integer> solution, int N, int[][] paths) {
        if (isASolution(solution, N)) {
            processSolution(result, solution);
            return true;
        } else {
            // 在每个位置都尝试1，2，3，4
            for (int i = 1; i <= 4; i++) {
                if (isValid(solution, i, paths)) {
                    makeMove(solution, i);
                    if (dfs(result, solution, N, paths)) {
                        return true;
                    }
                    unMakeMove(solution);
                }
            }
            return false;
        }
    }
    
    private static boolean isASolution(List<Integer> solution, int n) {
        return solution.size() == n;
    }

    private static void processSolution(int[] result, List<Integer> solution) {
        for (int i = 0; i < solution.size(); i++) {
            result[i] = solution.get(i);
        }
    }
    
    private static boolean isValid(List<Integer> solution, int n, int[][] paths) {
        // 找当前的连接点
        int currentIndex = solution.size() + 1; // 当前尝试的点
        if (currentIndex == 1) {
            return true;
        }
        for (int i = 1; i < currentIndex; i++) {
            for (int j = 0; j < paths.length; j++) {
                int x = Math.min(paths[j][0], paths[j][1]);
                int y = Math.max(paths[j][0], paths[j][1]);
                if (x == i && y == currentIndex) {
                    if (solution.get(x-1) == n) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    private static void makeMove(List<Integer> solution, int n) {
        solution.add(n);
    }
    
    private static void unMakeMove(List<Integer> solution) {
        solution.remove(solution.size()-1);
    }
    
    public static void main(String[] args) {
        int N = 6;
        int[][] paths = new int[][] {{6,4},{6,1},{3,1},{4,5},{2,1},{5,6},{5,2}};
        System.out.println(Arrays.toString(solution2(N, paths)));
    }
    
}
