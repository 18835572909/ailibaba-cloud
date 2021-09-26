package com.rhb.leetcode.algorithm;

import java.util.Arrays;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/9/16 15:02
 */
public class Arithmetic2 {

  public static void main(String[] args) {
    int[] nums ={2,5,5,11};
    int target = 10;
    System.out.println(Arrays.toString(twoSum(nums,target)));

    int len = lengthOfLongestSubstring("abcabcbb");
    System.out.println(len);
  }

  public static int[] twoSum(int[] nums, int target) {
    int[] resArr = new int[2];
    for(int i=0;i<nums.length;i++){
      for(int j=i+1;j<nums.length;j++){
        if(nums[i]+nums[j]==target){
          resArr[0] = i<j?i:j;
          resArr[1] = i<j?j:i;
          break;
        }
      }
      if(resArr[1]>0){
        break;
      }
    }
    return resArr;
  }

  /**
   * 获取最大不重复字符串的长度
   *
   * 以这个字符串为例：abcabcbb，当i等于3时，也就是指向了第二个a, 此时我就需要查之前有没有出现过a,
   * 如果出现了是在哪一个位置出现的。然后通过last[index] 查到等于1, 也就是说，如果start 依然等于0
   * 的话，那么当前窗口就有两个a了，也就是字符串重复了，所以我们需要移动当前窗口的start指针，移动
   * 到什么地方呢？移动到什么地方，窗口内就没有重复元素了呢？ 对了，就是a上一次出现的位置的下一个
   * 位置，就是1 + 1 = 2。当start == 2, 当前窗口就没有了重复元素，那么以当前字符为结尾的最长无重
   * 复子串就是bca,然后再和之前的res取最大值。然后i指向后面的位置，按照同样思路计算。
   */
  public static int lengthOfLongestSubstring(String s) {
    /**
     * 记录字符上一次出现的位置
     *
     * last[index]=value;
     * index : s中字符的ASCII码值
     * value : s中字符的下标
     */
    int[] last = new int[128];
    for(int i = 0; i < 128; i++) {
      last[i] = -1;
    }

    int n = s.length();

    /**
     * 最长不重复字符长度
     */
    int res = 0;
    int start = 0; // 窗口开始位置
    for(int i = 0; i < n; i++) {
      /**
       * ASCII码值
       */
      int index = s.charAt(i);
      /**
       * last[index]+1 ：s中字符下标向右移动1位
       *
       * abcabcbb
       * last[97] = 0
       * last[98] = 1
       * last[99] = 2
       * last[97] = 3
       * last[98] = 4
       * last[99] = 5
       * last[98] = 6
       * last[98] = 7
       *
       * start: 字符下标+1
       * 如果字符不重复，start=0
       *
       * 当 last[index]+1=0 时，字符没有重复
       * 当 last[index]+1!=0 时，字符重复，start成为原来位置的下一位
       *
       */
      start = Math.max(start, last[index] + 1);

      System.out.println("start:"+start);
      /**
       * i-start+1: 标识当前不重复字符的长度
       *
       * res：这些不重复的字符中最长的长度
       */
      res   = Math.max(res, i - start + 1);
      /**
       * 更新字符对应ASCII码，下标位置的值
       */
      last[index] = i;
    }

    return res;
  }

}
