package com.rhb.leetcode.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *  两个数组获取交集
 *
 * @author renhuibo
 * @date 2021/9/16 11:17
 */
public class Arithmetic1 {

  static final int[] defaultArr1 = {1,1,2,3,4,5,5};

  static final int[] defaultArr2 = {1,1,5,2};

  public static void main(String[] args) {
    int[] ints = method1(defaultArr1, defaultArr2);
    System.out.println(Arrays.toString(ints));

    int[] ints2 = method2(defaultArr1,defaultArr2);
    System.out.println(Arrays.toString(ints2));
  }

  public static int[] method1(int[] arr1,int[] arr2){
    if(arr1.length>arr2.length){
      int[] temp = arr1;
      arr1 = arr2;
      arr2 = temp;
    }
    Map<Integer,Integer> map = new HashMap<>(arr1.length);
    for(int field:arr1){
      if(map.containsKey(field)){
        map.put(field,map.get(field)+1);
      }else{
        map.put(field,1);
      }
    }

    int[] intersectionArr = new int[arr1.length];
    int index = 0;
    for (int field:arr2){
      if(map.containsKey(field)&&map.get(field)>0){
        intersectionArr[index++] = field;
        map.put(field,map.get(field)-1);
      }
    }
    return intersectionArr;
  }

  /**
   * 排序数组使用双指针
   * - 两个指针，分别从两个数组index=0的位置移动
   * - 下标相同，值小的指针向后移动，任意数组结束，计算完结
   */
  public static int[] method2(int[] arr1,int[] arr2){
    // 排序
    Arrays.sort(arr1);
    Arrays.sort(arr2);

    if(arr1.length>arr2.length){
      int[] temp = arr1;
      arr1 = arr2;
      arr2 = temp;
    }

    int i,j,k;
    j = i = k = 0;
    int[] resArr = new int[arr1.length];
    while(true){
      if(i>=arr1.length || j>=arr2.length){
        break;
      }
      if(arr1[i]>arr2[j]){
        j++;
      }else if(arr1[i]<arr2[j]){
        i++;
      }else {
        resArr[k++] = arr1[i];
        j++;
        i++;
      }
    }

    return Arrays.copyOfRange(resArr,0,k);
  }

}
