package io.github.spharris.datastructures;

import java.util.ListIterator;

public interface List<T> extends Iterable<T> {
  void add(T item);
  void insert(int index, T item);
  T removeAt(int index);
  T remove(T item);
  T get(int index);
  boolean contains(T item);
  int size();
  boolean isEmpty();
  ListIterator<T> listIterator();
}
