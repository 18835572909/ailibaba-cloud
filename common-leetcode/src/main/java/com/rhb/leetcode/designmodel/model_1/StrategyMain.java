package com.rhb.leetcode.designmodel.model_1;

/**
 * 策略模式：定义算法家族，并且算法的变化不会影响到客户的使用。
 *
 * @author renhuibo
 * @date 2021/10/14 16:01
 */
public class StrategyMain {

  public static void main(String[] args) {
    int from = 10;
    int toValue1 = new StrategyContext(new Child1Strategy()).getToValue(10);
    int toValue2 = new StrategyContext(new Child2Strategy()).getToValue(10);
    int toValue3 = new StrategyContext(new Child3Strategy()).getToValue(10);
    System.out.println(toValue1+","+toValue2+","+toValue3);

    int toValue_1 = new StrategyContext(StrategyType.TYPE_1).getToValue(from);
    int toValue_2 = new StrategyContext(StrategyType.TYPE_2).getToValue(from);
    int toValue_3 = new StrategyContext(StrategyType.TYPE_3).getToValue(from);
    System.out.println(toValue_1+","+toValue_2+","+toValue_3);
  }

}
