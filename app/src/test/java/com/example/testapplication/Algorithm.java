package com.example.testapplication;

import org.junit.Test;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * 各种算法
 */
public class Algorithm {

    /**
     * 某商店规定：三个空汽水瓶可以换一瓶汽水，允许向老板借空汽水瓶（但是必须要归还）。
     * 小张手上有n个空汽水瓶，她想知道自己最多可以喝到多少瓶汽水。
     * 数据范围：输入的正整数满足
     * 注意：本题存在多组输入。输入的 0 表示输入结束，并不用输出结果。
     */
    @Test
    public void bottleChange() {

        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        // 瓶数
        int number = 10;
        // 最终喝到的瓶数
        int count = 0;
        while (number / 3 > 0) {
            // 每3瓶换一瓶
            count += number / 3;
            number = number / 3 + number % 3;
            // 当只有2个空瓶时，借1瓶，换1瓶再还回去
            if (number == 2) {
                number = number + 1;
            }
        }
        System.out.println("空瓶数为：" + number + "时可以喝到的瓶数为：" + count);

    }

    /**
     * 明明生成了N个1到500之间的随机整数。请你删去其中重复的数字，即相同的数字只保留一个，把其余相同的数去掉，
     * 然后再把这些数从小到大排序，按照排好的顺序输出。
     */
    @Test
    public void removeDuplicationAndSort() {
        Scanner sc = new Scanner(System.in);
        //获取个数
        int num = sc.nextInt();
        //创建TreeSet进行去重排序
        //1、元素不能重复。
        //2、具有排序功能。
        TreeSet set = new TreeSet();
        //输入
        for (int i = 0; i < num; i++) {
            set.add(sc.nextInt());
        }

        //输出
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }


    /**
     * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。
     * <p>
     * 数据范围：保证结果在
     */
    @Test
    public void toHex() {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String str = sc.next().substring(2);
            System.out.println(Integer.parseInt(str, 16));
        }

    }


}