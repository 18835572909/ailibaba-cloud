package com.rhb.leetcode.designmodel.model_1;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/10/14 15:51
 */
public class Child1Strategy implements SuperStrategy {

  @Override
  public int execute(int from){
    return from+1;
  }

}
