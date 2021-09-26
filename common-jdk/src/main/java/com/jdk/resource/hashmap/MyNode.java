package com.jdk.resource.hashmap;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/9/8 13:59
 */
public class MyNode<K,V> implements MyMap.Entry<K,V> {

  private K k;
  private V v;

  // 单向链表
  private MyNode<K,V> next;

  public MyNode(){}

  public MyNode(K k,V v, MyNode<K,V> next){
    this.k = k;
    this.v = v;
    this.next = next;
  }

  @Override
  public K getKey() {
    return k;
  }

  @Override
  public V getValue() {
    return v;
  }
}
