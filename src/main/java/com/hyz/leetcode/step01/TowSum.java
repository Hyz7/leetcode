package com.hyz.leetcode.step01;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 两数之和
 * @author: Hyz
 * @create: 2022-06-30
 **/
public class TowSum {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Arrays.toString(twoSum(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13}, 14)));
    }

    public static int[] twoSum(int[] nums, int target) throws InterruptedException {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        throw new InterruptedException("no two sum");
    }
}
