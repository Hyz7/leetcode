package com.hyz.leetcode.step02;

import java.math.BigDecimal;

/**
 * @description: 两数相加
 * @author: Hyz
 * @create: 2022-06-30
 **/
public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, null);
        ListNode l2 = new ListNode(9, null);
        ListNode l3 = new ListNode(9, null);
        ListNode l4 = new ListNode(9, null);
        ListNode l5 = new ListNode(9, null);
        ListNode l6 = new ListNode(9, null);
        ListNode l7 = new ListNode(9, null);
        ListNode l8 = new ListNode(9, null);
        ListNode l9 = new ListNode(9, null);
        ListNode l10 = new ListNode(9, null);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        l7.next = l8;
        l8.next = l9;
        l9.next = l10;
        ListNode l11 = new ListNode(9, null);
        System.out.println(addTwoNumbers(l1, l11));
    }


    /**
     * 思路：
     * 1.把两个链表的每一位数相加，超过10则进位，进位（1）加到下两个数之和上
     * 2.为保证链表长度一致，空位补0，如 789 + 56 = 789 + 056 = 854
     * 3.定义一个链表指向头节点用于最后返回结果，定义一个指向当前计算和的链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 用于指向头节点，返回结果
        ListNode pred = new ListNode(0);
        // 指向当前计算两数和的指针
        ListNode cur = pred;
        // 进位标志
        int carry = 0;
        while (l1 != null || l2 != null) {
            // 为保证链表长度一致，当前节点为空补0
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;

            // 当前节点和为：两个节点数相加再加进位
            int sum = val1 + val2 + carry;
            // 进位标志（直接对10取商）
            carry = sum / 10;
            // 当前和（因为两数之和一定小于20，所以直接对10取余，sum=0-9）
            sum = sum % 10;

            // 当前链表和
            cur.next = new ListNode(sum);
            // 后移指针
            cur = cur.next;

            // 链表后移，直到移动到最后（为null就到链表的最后了）
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        // 最后判断是否有进位
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }
        return pred.next;
    }

    /**
     * 错误思路：直接把两个链表反转后再相加，int会超出长度，BigDecimal其实可以实现但是leetcode编译不过，而且这个方法太蠢
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(-1), pre = dummyHead;
        // 先把链表遍历全部取出来用string拼接
        StringBuilder l1Num = new StringBuilder(String.valueOf(l1.val));
        StringBuilder l2Num = new StringBuilder(String.valueOf(l2.val));
        ListNode l1NextNode = l1.next;
        ListNode l2NextNode = l2.next;
        if (l1NextNode != null) {
            do {
                l1Num.append(l1NextNode.val);
            } while ((l1NextNode = l1NextNode.next) != null);
        }
        if (l2NextNode != null) {
            do {
                l2Num.append(l2NextNode.val);
            } while ((l2NextNode = l2NextNode.next) != null);
        }

        // 反转后相加
        String l1ReverseStr = l1Num.reverse().toString();
        String l2ReverseStr = l2Num.reverse().toString();
        String sum = new BigDecimal(l1ReverseStr).add(new BigDecimal(l2ReverseStr)).stripTrailingZeros().toPlainString();

        // 转string后反转生成链表
        char[] chars = sum.toCharArray();
        ListNode listNode = new ListNode();
        for (int i = chars.length - 1; i >= 0; i--) {
            int val = Integer.parseInt(String.valueOf(chars[i]));
            if (i == chars.length - 1) {
                // 第一个节点
                listNode.val = val;
            } else {
                // 找到最后一个节点
                ListNode lastNode = null;
                // 用于自旋查找最后一个节点
                ListNode tNode = listNode;
                do {
                    if (tNode.next != null) {
                        lastNode = tNode.next;
                    }
                } while ((tNode = tNode.next) != null);
                // 添加数据
                if (lastNode == null) {
                    // 第二个节点
                    listNode.next = new ListNode(val, null);
                } else {
                    // 第二个节点之后
                    lastNode.next = new ListNode(val, null);
                }
            }
        }
        return listNode;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            ListNode t = this;
            int i = 1;
            do {
                if (i == 1) {
                    sb.append(val);
                } else {
                    sb.append(t.val);
                }
                if (t.next != null) {
                    sb.append("->");
                }
                i++;
            } while ((t = t.next) != null);
            return sb.toString();
        }
    }
}
