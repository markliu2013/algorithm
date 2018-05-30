package com.zfwhub.algorithm.math.template;

/**
 * http://blog.sina.com.cn/s/blog_8960ba110100txng.html
 * https://www.zhihu.com/question/32128504
 * https://blog.csdn.net/opencpu/article/details/48391417
 * https://blog.csdn.net/mikayong/article/details/51706508
 * For循环拍平
 */
public class DynamicFor {
    
    /**
     * https://coderanch.com/t/629359/java/java-recursion
     */
    public static void x(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("   " + n);
            x(n - 1);
        }
    }
    
    public static void x2(int n) {
        System.out.println("Enter x(" + n + ")");
        for (int i = 0; i < n; i++) {
            System.out.println("Enter for loop. i = " + i + ", n = " + n);
            System.out.println("Before recursive call");
            x2(n - 1);
            System.out.println("After recursive call");
        }
        System.out.println("Exit x(" + n + ")");
    }
    
    static int count = 0;
    public static void cycle(int i, int n) {
        if (n == 0) {
            count++;
            return;
        }
        n--;
        for (int j = 0; j < i; j++) {
            cycle(i, n);
        }
    }
    
    public static void main(String[] args) {
        cycle(3, 4);//3*3*3*3
        System.out.println(count);
    }

}
