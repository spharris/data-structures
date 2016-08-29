package io.github.spharris.datastructures;

import java.util.Iterator;
import java.util.ListIterator;

public class ArrayList<T> implements List<T> {

  private static final int INITIAL_SIZE = 8;
  private static final float INFLATION_FACTOR = 1.5f;
  
  private T[] data;
  private int size;
  
  @SuppressWarnings("unchecked")
  public ArrayList() {
    data = (T[]) new Object[INITIAL_SIZE];
    size = 0;
  }
  
  @SuppressWarnings("unchecked")
  private void expandData() {
    T[] newData = (T[]) new Object[(int) (((float) data.length) * INFLATION_FACTOR)];
    for (int i = 0; i < data.length; i++) {
      newData[i] = data[i];
    }
    
    data = newData;
  }
  
  private void checkIndex(int index) {
    if (size == 0 || index > size || index < 0) {
      throw new IndexOutOfBoundsException();
    }
  }
  
  private void checkSize() {
    if (size == data.length) {
      expandData();
    }
  }
  
  /**
   * This should only be called after size is reduced; it will not decrement size. 
   */
  
  @Override
  public Iterator<T> iterator() {
    return listIterator();
  }

  @Override
  public void add(T item) {
    checkSize();
    
    data[size] = item;
    size++;
  }

  @Override
  public void insert(int index, T item) {
    checkIndex(index);
    size++;
    expandDataAtIndex(index);
    
    data[index] = item;
  }

  private void expandDataAtIndex(int index) {
    checkSize();
    
    for (int i = size; i > index; i--) {
      data[i] = data[i - 1];
    }
  }
  
  @Override
  public T removeAt(int index) {
    checkIndex(index);
    
    T result = data[index];
    data[index] = null;
    size--;
    compactDataAtIndex(index);
    
    return result;
  }

  private void compactDataAtIndex(int index) {
    if (data[index] != null) {
      throw new IllegalArgumentException("Can only compact data at an empty index");
    }
    
    for (int i = index; i < size; i++) {
      data[i] = data[i + 1];
    }
  }
  
  @Override
  public T remove(T item) {
    for (int i = 0; i < size; i++) {
      if (data[i].equals(item)) {
        return removeAt(i);
      }
    }
    
    return null;
  }

  @Override
  public T get(int index) {
    checkIndex(index);
    
    return data[index];
  }

  @Override
  public boolean contains(T item) {
    for (int i = 0; i < size; i++) {
      if (data[i].equals(item)) {
        return true;
      }
    }
    
    return false;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public ListIterator<T> listIterator() {
    // TODO Auto-generated method stub
    return null;
  }
  
  private class ArrayListIterator<T> implements ListIterator<T> {
    
    @Override
    public boolean hasNext() {
      // TODO Auto-generated method stub
      return false;
    }

    @Override
    public T next() {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public boolean hasPrevious() {
      // TODO Auto-generated method stub
      return false;
    }

    @Override
    public T previous() {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public int nextIndex() {
      // TODO Auto-generated method stub
      return 0;
    }

    @Override
    public int previousIndex() {
      // TODO Auto-generated method stub
      return 0;
    }

    @Override
    public void remove() {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void set(T e) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void add(T e) {
      // TODO Auto-generated method stub
      
    }
    
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");

    ListIterator<T> it = listIterator();
    if (it.hasNext()) {
      sb.append(it.next());
      while(it.hasNext()) {
        sb.append(", " + it.next().toString());
      }
    }

    sb.append("]");
    return sb.toString();
  }

}
