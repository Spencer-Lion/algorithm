package com.example.testapplication;

import org.junit.Test;
import java.util.*;

public class MediumDifficulty {

    /**
     * 给定一个长度为n的数组arr，返回arr的最长无重复元素子数组的长度，无重复指的是所有数字都不相同。
     * 子数组是连续的，比如[1,3,5,7,9]的子数组有[1,3]，[3,5,7]等等，但是[1,3,7]不是子数组
     * 双指针解法
     */
    public int maxLength(int[] arr) {
        // write code here
        if (arr.length == 0)
            return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        // 双指针，最初i,j都指向第1位，i不断往前走，当遇到重复项时，记录下当前长度为max=i - j + 1
        // 同时j指向重复项
        for (int i = 0, j = 0; i < arr.length; ++i) {
            if (map.containsKey(arr[i])) {
                j = Math.max(j, map.get(arr[i]) + 1);
            }
            map.put(arr[i], i);
            max = Math.max(max, i - j + 1);
        }
        return max;
    }

    /**
     * 用一个队列，把元素不停的加入到队列中，如果有相同的元素，就把队首的元素移除，
     * 这样我们就可以保证队列中永远都没有重复的元素
     */
    public int maxLength2(int[] arr) {
        //用链表实现队列，队列是先进先出的
        Queue<Integer> queue = new LinkedList<>();
        int res = 0;
        for (int c : arr) {
            while (queue.contains(c)) {
                //如果有重复的，队头出队
                queue.poll();
            }
            //添加到队尾
            queue.add(c);
            res = Math.max(res, queue.size());
        }
        return res;
    }

    /**
     * 给定一个 n * m 的矩阵 a，从左上角开始每次只能向右或者向下走，最后到达右下角的位置，
     * 路径上所有的数字累加起来就是路径和，输出所有的路径中最小的路径和。
     */
    public int minPathSum(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = matrix[0][0];
        //第一列只能从上面走下来
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        }
        //第一行只能从左边走过来
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + matrix[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                //递推公式，取从上面走下来和从左边走过来的最小值+当前坐标的值
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        ArrayList<Interval> res = new ArrayList<>();
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                // 从小到大排列
                return a.start - b.start;
            }
        });
        int len = intervals.size();
        int i = 0;
        //下面举例子
        while (i < len) {
            int left = intervals.get(i).start; //获得起点  a
            int right = intervals.get(i).end;  //获得终点  b
            //例如：c <= b，则可以合并
            while ((i < len - 1) && (intervals.get(i + 1).start <= right)) {
                // 看b大还是d大，区间则为哪个
                right = Math.max(right, intervals.get(i + 1).end);
                i++; // i继续遍历下一个区间，看能不能继续合并
            }
            //合并完的区间放进去返回集合中
            res.add(new Interval(left, right));
            //以下一个为合并的区间为开始区间继续合并
            i++;
        }
        return res;
    }

    @Test
    public void testSort() {
        Interval interval1 = new Interval();
        interval1.start = 10;
        interval1.end = 20;

        Interval interval2 = new Interval();
        interval2.start = 30;
        interval2.end = 40;
        Interval interval3 = new Interval();
        interval3.start = 50;
        interval3.end = 60;

        ArrayList<Interval> intervals = new ArrayList<>();
        intervals.add(interval1);
        intervals.add(interval2);
        intervals.add(interval3);

        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                // 从小到大排列
                System.out.println("a.start - b.start=" + (a.start - b.start));
                return a.start - b.start;
            }
        });
        for (int i = 0; i < intervals.size(); i++) {
            System.out.println("intervals.start=" + intervals.get(i).start);
        }

        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                // 从大到小排列
                System.out.println("b.start - a.start=" + (b.start - a.start));
                return b.start - a.start;
            }
        });
        for (int i = 0; i < intervals.size(); i++) {
            System.out.println("intervals.start=" + intervals.get(i).start);
        }
    }

    /**
     * 有一个NxN整数矩阵，请编写一个算法，将矩阵顺时针旋转90度。
     * 给定一个NxN的矩阵，和矩阵的阶数N,请返回旋转后的NxN矩阵。
     */
    public int[][] rotateMatrix(int[][] mat, int n) {
        // write code here
        int[][] temp = new int[n][n];//新建temp对象，作为最终返回的对象
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[j][n - 1 - i] = mat[i][j];//直接交换
            }
        }
        return temp;
    }


    /**
     * 加起来和为目标值的组合(二)
     * 有点难，要再看看
     */
    ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    HashSet<Integer> set = new HashSet<>();
    public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
        //先要排序数组
        Arrays.sort(num);
        // 进行遍历
        dfs(num, target, 0, new ArrayList<Integer>());
        return res;
    }

    void dfs(int[] num, int target, int start, ArrayList<Integer> list){
        // 目标值小于0直接返回
        if(target < 0) return;
        // 当目标值为0，即list中的数字和与初始的target值相等
        if(target == 0 && list.size()>0){
            res.add(new ArrayList<Integer>(list));
            return;
        }
        // 不断往后的数字进行选择
        for(int i=start;i<num.length;i++){
            // 按照排列的思路使用set去重
            if(i>0 && num[i]==num[i-1] && !set.contains(i-1)) continue;
            // 后面的数比target大，直接结束
            if(num[i]>target) break;
            // 选择该数
            list.add(num[i]);
            set.add(i);
            dfs(num, target-num[i], i+1, list);
            // 撤销选择
            list.remove(list.size()-1);
            set.remove(i);
        }
    }


}
