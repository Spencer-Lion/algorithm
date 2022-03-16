package com.example.testapplication;

import org.junit.Test;

import java.util.Arrays;


/**
 * 进制转换相关算法
 * binary
 * octal
 * decimal
 * hexadecimal
 */
public class BinaryConversion {

    /**
     * 二进制转八进制
     */
    @Test
    public void binaryToOctal() {

    }

    /**
     * 二进制转十进制
     */
    @Test
    public void binaryToDecimal() {

    }

    /**
     * 二进制转十六进制
     */
    @Test
    public void binaryToHexadecimal() {

    }

    /**
     * 八进制转二进制
     */
    @Test
    public void octalToBinary() {

    }

    /**
     * 八进制转十进制
     */
    @Test
    public void octalToDecimal() {

    }

    /**
     * 八进制转十六进制
     */
    @Test
    public void octalToHexadecimal() {

    }

    /**
     * 十进制转二进制
     */
    @Test
    public void decimalToBinary() {

    }

    /**
     * 十进制转八进制
     */
    @Test
    public void decimalToOctal() {

    }

    /**
     * 十进制转十六进制
     * 十六进制中所有字母(a-f)都必须是小写。
     * 十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。 
     * 给定的数确保在32位有符号整数范围内。
     * 不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法
     * <p>
     * 要解决本题，首先我们需要了解计算机存储 32 位有符号整数的相关知识。int 型整数存储范围在 [-2^{31}, 2^{31} - 1][−2^31,231−1] 内，
     * 且在计算机内部以补码形式表示，共 3232 位，最高位为符号位，负数符号位为 1，正数为 0。
     * 所以，在计算机中，[0, 2^{31} - 1]范围内的数对应的十六进制存储方式为 00000000-7fffffff，[-2^31,-1]范围内的数对应的十六进制存储方式为 80000000-ffffffff。
     * 所以，我们可以先将负数统一加上 2^32，使其映射到 [2^31, 2^{32} - 1]范围内，就可以直接进行十六进制转换了。
     *
     * 26转为16进制为：1a
     * -1转为16进制为：ffffffff
     */
    @Test
    public void decimalToHexadecimal() {
        String s = toHex(26);
        String s1 = toHex(-1);
        System.out.println("26转为16进制为：" + s);
        System.out.println("-1转为16进制为：" + s1);
    }

    public String toHex(int _num) {
        if (_num == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        char[] chs = {'a', 'b', 'c', 'd', 'e', 'f'};
        long num = _num;
        if (_num < 0) {
            num = (long) Math.pow(2, 32) + _num;
        }
        while (num != 0) {
            int remainder = (int) (num % 16);
            if (remainder > 9) {
                sb.append(chs[remainder % 10]);
            } else {
                sb.append(remainder);
            }
            num /= 16;
        }
        return sb.reverse().toString();
    }


    /**
     * 十六进制转二进制
     */
    @Test
    public void hexadecimalToBinary() {

    }

    /**
     * 十六进制转八进制
     */
    @Test
    public void hexadecimalToOctal() {

    }

    /**
     * 十六进制转十进制
     */
    @Test
    public void hexadecimalToDecimal() {

    }

}