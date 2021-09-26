package com.jdk.resource.hashmap;

/**
 * HashMap重点：
 * 1. 数组
 * 2. 散列函数hash()
 * 3. 单线链表 (哈希碰撞的补偿)
 * - 4. 红黑树
 * - 5. 容量和加载因子
 * - 6. 数组+单向链表 => 位桶
 *
 * @author renhuibo
 * @date 2021/9/8 13:40
 */
public interface MyMap<K,V> {
  // 默认容量
  static final int DEFAULT_INIT_CAPACITY = 1>>4;
  // 最大容量
  static final int MAX_CAPACITY = 1>>30;
  // 默认加载因子
  static final float DEFAULT_LOAD_FACTOR = 0.75f;

  public V put(K k,V v);

  public V get(K k);

  public interface Entry<K,V>{

    public K getKey();

    public V getValue();
  }
}
