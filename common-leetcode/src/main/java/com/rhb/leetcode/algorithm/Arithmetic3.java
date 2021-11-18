package com.rhb.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 两个有序数组中位数
 *
 * @author renhuibo
 * @date 2021/10/12 17:53
 */
public class Arithmetic3 {

  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    List<Integer> numList = new ArrayList<>();
    for(int n:nums1){
      numList.add(n);
    }
    for(int n:nums2){
      numList.add(n);
    }
    Collections.sort(numList);

    if(numList.size()==0){
      return 0.0d;
    }

    int yushu = numList.size() % 2;
    int zws = numList.size() / 2;
    if(yushu==0){
      double sum = (double)(numList.get(zws-1)+numList.get(zws));
      return sum/2d;
    }else{
      return numList.get(zws);
    }
  }

}
