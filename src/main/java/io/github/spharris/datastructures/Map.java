package io.github.spharris.datastructures;

public interface Map<K, V> {
  void put(K key, V value);
  V get(K key);
  boolean containsKey(K key);
  
  int size();
  boolean isEmpty();
  
  
  interface Entry<K, V> {
    K key();
    V value();
  }
}
