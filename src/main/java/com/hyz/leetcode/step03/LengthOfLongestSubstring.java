package com.hyz.leetcode.step03;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @description: 无重复字符的最长子串
 * @author: Hyz
 * @create: 2022-07-01
 **/
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("qrsvbspk"));
        System.out.println(lengthOfLongestSubstring("dvdf"));
        System.out.println(lengthOfLongestSubstring("aab"));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        // 存字符串长度
        Map<String, Integer> map = new HashMap<>();
        // 保存字符串
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 判断当前字符串是否出现过
            if (!str.toString().contains(String.valueOf(c))) {
                str.append(c);
            } else {
                // 已经出现，保存字符串和长度
                map.put(str.toString(), str.length());

                String s1 = String.valueOf(str.substring(str.indexOf(String.valueOf(c))+1));
                str = new StringBuilder(s1);
                if (!str.toString().contains(String.valueOf(c))) {
                    str.append(c);
                }
            }

        }
        // 字符串没有重复的情况
        if (!map.containsKey(str.toString())) {
            // 判断当前字符串是否重复
            Map<Character, Integer> m = new HashMap<>(str.length());
            boolean repeat = true;
            for (int i = 0; i < str.length(); i++) {
                if (m.containsKey(str.charAt(i))) {
                    repeat = false;
                    break;
                }
                m.put(str.charAt(i), 1);
            }
            if (repeat) {
                map.put(str.toString(), str.length());
            }
        }
        // 遍历map取出最大的value
        AtomicReference<Integer> max = new AtomicReference<>(0);
        map.forEach((k, v) -> {
            max.set(Math.max(max.get(), v));
        });
        return max.get();
    }

    /**
     * 思路：利用map来保存每个字符，判断存在字符key则把value重置为1，否则所有value+1
     * 1.遍历字符串，保存字段为key，value为1到map
     * 2.如果存在当前key，则把当前key的value置位1，其他的value+1
     * 3.遍历完成字符串后，遍历map取出最大的value
     * <p>
     * 题意理解错误，是最长的字符串长度，不是最多不重复的字符长度
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring1(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 先遍历map，所有value+1
            map.forEach((k, v) -> {
                map.put(k, map.get(k) + 1);
            });
            // 把当前key置为1
            map.put(c, 1);
        }
        // 遍历map取出最大的value
        AtomicReference<Integer> max = new AtomicReference<>(0);
        map.forEach((k, v) -> {
            max.set(Math.max(max.get(), v));
        });
        return max.get();
    }
}
