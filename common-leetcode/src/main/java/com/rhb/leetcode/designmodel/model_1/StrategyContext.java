package com.rhb.leetcode.designmodel.model_1;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/10/14 15:50
 */
public class StrategyContext {

  private SuperStrategy strategy;

  public StrategyContext(SuperStrategy strategy){
    this.strategy = strategy;
  }

  public StrategyContext(StrategyType type){
    switch (type){
      case TYPE_1:this.strategy = new Child1Strategy();break;
      case TYPE_2:this.strategy = new Child2Strategy();break;
      case TYPE_3:this.strategy = new Child3Strategy();break;
      default:
        throw new RuntimeException("type参数错误");
    }
  }

  public int getToValue(int from){
    return strategy.execute(from);
  }

}
