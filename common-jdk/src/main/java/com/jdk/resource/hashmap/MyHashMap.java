package com.jdk.resource.hashmap;


import com.google.common.hash.Hashing;

/**
 * {desc}
 *
 * @author renhuibo
 * @date 2021/9/8 13:57
 */
public class MyHashMap<K,V> implements MyMap<K,V> {

  private int initialCapacity = MyMap.DEFAULT_INIT_CAPACITY;

  private float loadFactor = MyMap.DEFAULT_LOAD_FACTOR;

  private MyNode[] table;

  public MyHashMap(int initialCapacity,int loadFactor){
    if (initialCapacity < 0){
      throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
    }
    if (initialCapacity > MyMap.MAX_CAPACITY){
      initialCapacity = MyMap.MAX_CAPACITY;
    }
    if (loadFactor <= 0 || Float.isNaN(loadFactor)){
      throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
    }

    this.initialCapacity = initialCapacity;
    this.loadFactor = loadFactor;
    this.table = new MyNode[initialCapacity];
  }

  @Override
  public V put(K k, V v) {
    V oldValue = null;


    return null;
  }

  @Override
  public V get(K k) {
    return null;
  }

  /**
   * hash函数，主要使用 XOR异或 运算，使得散列相对均匀
   */
  public static int hashWithJdk(Object key){
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
  }

  public static int hashWithWeb(Object key){
    int h = key.hashCode();
    h ^= (h>>>20) ^ (h>>>12);
    return h ^ (h>>>7) ^ (h>>>4);
  }
}
