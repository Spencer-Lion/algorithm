package com.example.testapplication;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Easy {

    /**
     * 反转链表
     * 栈
     */
    public ListNode ReverseList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        //把链表节点全部摘掉放到栈中
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        if (stack.isEmpty()) {
            return null;
        }
        // 栈中的最上面结点出栈，并赋予dummy,作为链表的第一个结点
        ListNode node = stack.pop();
        ListNode dummy = node;
        //栈中的结点全部出栈，然后重新连成一个新的链表
        while (!stack.isEmpty()) {
            ListNode tempNode = stack.pop();
            node.next = tempNode;
            node = node.next;
        }
        //最后一个结点就是反转前的头结点，一定要让他的next
        //等于空，否则会构成环
        node.next = null;
        return dummy;
    }

    /**
     * 反转链表
     * 迭代
     */
    public ListNode ReverseList2(ListNode head) {
        //pre指针：用来指向反转后的节点，初始化为null
        ListNode pre = null;
        //当前节点指针
        ListNode cur = head;
        //循环迭代
        while (cur != null) {
            //Cur_next 节点，永远指向当前节点cur的下一个节点
            ListNode Cur_next = cur.next;
            //反转的关键：当前的节点指向其前一个节点(注意这不是双向链表，没有前驱指针)
            cur.next = pre;
            //更新pre
            pre = cur;
            //更新当前节点指针
            cur = Cur_next;
        }
        //为什么返回pre？因为pre是反转之后的头节点
        return pre;
    }

    /**
     * 反转链表
     * 双链表
     */
    public ListNode ReverseList3(ListNode head) {
        //新链表
        ListNode newHead = null;
        while (head != null) {
            //先保存访问的节点的下一个节点，保存起来
            //留着下一步访问的
            ListNode temp = head.next;
            //每次访问的原链表节点都会成为新链表的头结点，其实就是把新链表挂到访问的原链表节点的后面就行了
            head.next = newHead;
            //更新新链表
            newHead = head;
            //重新赋值，继续访问
            head = temp;
        }
        //返回新链表
        return newHead;
    }


    /**
     * 给出一个整型数组 numbers 和一个目标值 target，请在数组中找出两个加起来等于目标值的数的下标，返回的下标按升序排列。
     * （注：返回的数组下标从1开始算起，保证target一定可以由数组里面2个数字相加得到）
     */
    public int[] twoSum(int[] numbers, int target) {
        // 定义哈希表hashmap，其存放的键值对为<取值，下标>。
        HashMap<Integer, Integer> map = new HashMap<>();
        //遍历数组,从开始处遍历数组，对于第i个位置，在哈希表中寻找target-nums[i]是否存在，若存在，将两个下标放入数组中返回；若不存在，将其添加至表中，继续遍历。
        for (int i = 0; i < numbers.length; i++) {
            //将不包含target - numbers[i]，装入map中，包含的话直接返回下标
            if (map.containsKey(target - numbers[i])) {
                // 注：在将结果保存至数组中时，无需判断二者下标大小：当所遍历到的位置符合题目要求时，说明哈希表中已经存在待查找的另一元素，因此「已存在于表中的元素的下标」一定小于「当前位置元素的下标」
                return new int[]{map.get(target - numbers[i]) + 1, i + 1};
            } else {
                // 将未找到的值插入哈希表中，继续遍历
                map.put(numbers[i], i);
            }
        }
        throw new IllegalArgumentException("No solution");
    }


    /**
     * 给一个长度为 n 的数组，数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
     * 例如输入一个长度为9的数组[1,2,3,2,2,2,5,4,2]。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。
     * <p>
     * 判断给定的array长度是否为零，为零则没有这样符合条件的数字，直接return 0
     * 我们创建一个hashmap，然后遍历这个array，hashmap的key是array里的不同数字，value是这些数字出现在array里的次数
     * 我们遍历hashmap，检测value（即当前数字出现的次数）是否大于数组array长度的一半，如果有这个数字，我们return回key（即当前遍历到的数字），
     * 如果我们走完了整个hashmap还没有发现这样一个数字，我们需要return 0
     */
    public int MoreThanHalfNum_Solution(int[] array) {
        if (array.length == 0) {
            return 0;
        }

        int len = array.length;
        int threshold = len / 2;
        Map<Integer, Integer> map = new HashMap<>();
        for (int value : array) {
            if (!map.containsKey(value)) {
                map.put(value, 1);
            } else {
                map.put(value, map.get(value) + 1);
            }
        }

        for (Integer key : map.keySet()) {
            if (map.get(key) > threshold) {
                return key;
            }
        }

        return 0;
    }

    /**
     * 给出一个仅包含字符'(',')','{','}','['和']',的字符串，判断给出的字符串是否是合法的括号序列
     * 括号必须以正确的顺序关闭，"()"和"()[]{}"都是合法的括号序列，但"(]"和"([)]"不合法。
     */
    public boolean isValid(String s) {
        StringBuffer ant = new StringBuffer();
        int n = s.length();
        char c, cc;
        for (int i = 0; i < n; i++) {
            c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                //要是全都是左边的符号 就直接添加到栈里面
                ant.append(c);
            } else {
                if (ant.length() == 0) return false;//要是空直接返回false
                cc = ant.charAt(ant.length() - 1);//cc表示栈顶元素
                if ((cc == '(' && c == ')') || (cc == '{' && c == '}') || (cc == '[' && c == ']')) {
                    //匹配成功直接删除末尾元素，模拟于出栈
                    ant.deleteCharAt(ant.length() - 1);
                    continue;
                } else {
                    return false;
                }
            }
        }
        //同样判断模拟栈最后是不是空
        if (ant.length() == 0)
            return true;
        else
            return false;
    }

    TreeNode root = new TreeNode();

    /**
     * 新建一个二叉树
     */
    public void createTreeNode() {
        root.val = 1;
        root.left = new TreeNode();
        root.left.val = 2;

        root.right = new TreeNode();
        root.right.val = 3;

        root.left.left = null;
        root.left.right = new TreeNode();
        root.left.right.val = 4;

        root.right.left = new TreeNode();
        root.right.left.val = 5;
        root.right.right = new TreeNode();
        root.right.right.val = 6;
    }

    /**
     * 求给定二叉树的最大深度，
     * 深度是指树的根节点到任一叶子节点路径上节点的数量。
     * 最大深度是所有叶子节点的深度的最大值。
     * （注：叶子节点是指没有子节点的节点。）
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        // write code here
        // 递归终止
        if (root == null) {
            return 0;
        }
        // dfs，先递归左子结点，再递归右子结点，最后求出每一子树的深度的最大值
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    @Test
    public void maxDepth() {
        createTreeNode();
        int maxDepth = maxDepth(root);
        System.out.println("二叉树的深度为：" + maxDepth);
    }

    /**
     * 输入两个递增的链表，单个链表的长度为n，合并这两个链表并使新链表中的节点仍然是递增排序的。
     */
    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        }
        if (list2.val > list1.val) {
            list1.next = Merge(list1.next, list2);
            return list1;
        } else {
            list2.next = Merge(list1, list2.next);
            return list2;
        }

    }


    /**
     * 输入两个无环的单向链表，找出它们的第一个公共结点，如果没有公共节点则返回空。
     * （注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        // 解法一：set解法
        //使用 Set 数据结构，先对某一条链表进行遍历，同时记录下来所有的节点。
        Set<ListNode> set = new HashSet<>();
        while (pHead1 != null) {
            set.add(pHead1);
            pHead1 = pHead1.next;
        }
        //然后在对第二链条进行遍历时，检查当前节点是否在 Set 中出现过，第一个在 Set 出现过的节点即是交点。
        while (pHead2 != null && !set.contains(pHead2)) {
            pHead2 = pHead2.next;

        }
        return pHead2;


        // 解法二：朴素解法
//        for (ListNode h1 = pHead1; h1 != null; h1 = h1.next) {
//            for (ListNode h2 = pHead2; h2 != null; h2 = h2.next) {
//                if (h1 == h2) return h1;
//            }
//        }
//        return null;
    }


    /**
     * 判断一个链表是否为回文结构
     *
     * @param head
     * @return
     */
    public boolean isPail(ListNode head) {
        // 方法一：因为需要判断是否为回文结构，所以要比较头尾的数据，而链表无法随机查询数据，所以可以先将链表转换成list。
        // n==1，返回true
        if (head.next == null) {
            return true;
        }
        List<Integer> nums = new ArrayList<Integer>();
        // 将链表转换成list
        while (head != null) {
            nums.add(head.val);
            head = head.next;
        }
        int i = 0;
        int j = nums.size() - 1;
        while (i < j) {
            // 不等则返回false
            // 这边有一个坑，如果不适用equals而使用!=的话，在牛客上对于有负数的测试用例可能会无法通过。
            if (!nums.get(i).equals(nums.get(j))) {
                return false;
            }
            ++i;
            --j;
        }
        return true;
    }

    /**
     * 使用栈
     *
     * @param head
     * @return
     */
    public boolean isPail2(ListNode head) {
        ListNode temp = head;
        Stack<Integer> stack = new Stack();
        //把链表节点的值存放到栈中
        while (temp != null) {
            stack.push(temp.val);
            temp = temp.next;
        }

        //然后再出栈
        while (head != null) {
            if (head.val != stack.pop()) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 一个朴素的做法是，对 int 的每一位进行检查，并统计  的个数。
     * 根据 与运算 定义，设二进制数字 nn ，则有：
     * 若 n & 1 = 0则 n 二进制 最右一位 为 0 ；
     * 若 n & 1 = 1则 n 二进制 最右一位 为 1 。
     */
    public int NumberOf1(int n) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            ans = ans + ((n >> i) & 1);
        }
        return ans;
    }

    @Test
    public void NumberOf1() {
        int number = 10;
        int ans = NumberOf1(number);
        System.out.println("十进制中的数为：" + number + "    对应二进制中的1的个数为：" + ans);
    }

    /**
     * 一个机器人在m×n大小的地图的左上角（起点）。
     * 机器人每次可以向下或向右移动。机器人要到达地图的右下角（终点）。
     * 可以有多少种不同的路径从起点走到终点？
     * 经典使用dp数组
     */
    public int uniquePaths(int m, int n) {
        // 定义dp数组
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 当 i = 0：dp[0][j] = dp[0][j-1]
                if (i == 0) {
                    dp[i][j] = 1; // 都是1是因为dp[0][j] = dp[0][j-1]，所以干脆全部赋值为1
                    continue;
                }
                // 当 j = 0：dp[i][0] = dp[i-1][0]
                if (j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                // 当 i > 1 && j > 1 :  dp[i][j] = dp[i][j-1] + dp[i-1][j]
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1]; // 返回到达终点的所有可行路径
    }

    @Test
    public void uniquePaths() {
        int uniquePaths = uniquePaths(2, 2);
        System.out.println(uniquePaths);
    }

    /**
     * 输入一个长度为n的整型数组array，数组中的一个或连续多个整数组成一个子数组，子数组最小长度为1。
     * 求所有子数组的和的最大值。
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        int[] dp = new int[array.length];
        int max = array[0];
        dp[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            // 动态规划，状态转移方程，确定dp[i]的最大值
            dp[i] = Math.max(dp[i - 1] + array[i], array[i]);
            // 每次比较，保存出现的最大值
            max = Math.max(max, dp[i]);
        }
        return max;

//        if(array.length == 1){
//            return array[0];
//        }
//        if(array == null){
//            return 0;
//        }
//        int max = array[0];
//        int[] dp = new int[array.length];
//        dp[0] = array[0];
//        for(int i = 1; i < array.length; i++){
//            int temp = dp[i-1]+array[i];
//            if(temp > array[i]){
//                dp[i] = temp;
//            }else{
//                dp[i] = array[i];
//            }
//            if(dp[i] > max){
//                max = dp[i];
//            }
//        }
//        return max;
//    }
    }

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
     * 滚动窗口实现前面两项分别为1和2的斐波那契数列，
     */
    public int JumpFloor(int n) {
        if (n <= 2) {
            return n;
        }
        int first = 1, second = 2, sum = 0;
        for (int i = 3; i <= n; ++i) {
            sum = first + second;
            first = second;
            second = sum;
        }
        return sum;
    }


}
