package com.example.testapplication;

import org.junit.Test;

public class Elementary {
    /**
     * 反转字符串
     *
     * @param str string字符串
     * @return string字符串
     */
    public String solve(String str) {
        // write code here
        StringBuilder s = new StringBuilder();
        int length = str.length();
        for (int i = length; i > 0; i--) {
            s.append(str.charAt(i - 1));
        }
        return s.toString();
    }

    @Test
    public void solveTest() {
//        String str = "abcdefg";
        String str = "";
        String after = solve(str);
        System.out.println("反转前：" + str);
        System.out.println("反转后：" + after);
    }

    /**
     * 斐波那契数列
     * 递归
     */
    public int Fibonacci(int n) {
        // 递归
//        int result;
//        if (n < 3) {
//            return  1;
//        }
//        result = Fibonacci(n - 1) + Fibonacci(n - 2);
//        return result;

        // 滚动窗口
        if (n < 2) {
            return n;
        }
        int p = 0, q = 0, r = 1;
        for (int i = 2; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    @Test
    public void fibTest() {
//        String str = "abcdefg";
        int number = 10;
        int result = Fibonacci(number);
        System.out.println("斐波那契数列的第" + number + "项为：" + result);
    }


    /**
     * 判断是否回文
     *
     * @param str string字符串 待判断的字符串
     * @return bool布尔型
     */
    public boolean judge(String str) {
        // write code here
        char[] ans = str.toCharArray();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            ans[i] = str.charAt(len - 1 - i);
        }
        String afterStr = new String(ans);
        System.out.println("afterStr" + afterStr);
        return str.equals(afterStr);
    }

    @Test
    public void judgeTest() {
//        String str = "abcdefg";
        String str = "absba";
        boolean judge = judge(str);
        System.out.println(str + "    以上字符串是否为文串：" + judge);
    }


}
