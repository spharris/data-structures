package io.github.spharris.datastructures;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * A doubly-linked list
 */
public class LinkedList<T> implements List<T> {

  private Node<T> head;
  private Node<T> tail;

  private int size = 0;

  /**
   * Adds an item to the end of the list
   */
  @Override
  public void add(T item) {
    Node<T> node = new Node<T>(item, null, tail);
    if (head == null) {
      head = node;
    }
    
    if (tail != null) {
      tail.next = node;
    }
    
    tail = node;
    size++;
  }

  @Override
  public void insert(int index, T item) {
    ListIterator<T> it = listIterator();
    for (int i = 0; i < index; i++) {
      it.next();
    }
    
    it.add(item);
  }

  @Override
  public T removeAt(int index) {
    ListIterator<T> it = listIterator();
    T data = it.next();
    for (int i = 0; i < index; i++) {
      data = it.next();
    }
    
    it.remove();
    return data;
  }

  @Override
  public T remove(T item) {
    ListIterator<T> it = listIterator();
    while (it.hasNext()) {
      T data = it.next();
      if (data.equals(item)) {
        it.remove();
        return data;
      }
    }

    return null;
  }

  @Override
  public T get(int index) {
    ListIterator<T> it = listIterator();
    for (int i = 0; i < index; i++) {
      it.next();
    }
    
    return it.next();
  }

  /**
   * Returns true if there is at least one item in the list equal to item
   */
  @Override
  public boolean contains(T item) {
    ListIterator<T> it = listIterator();
    while (it.hasNext()) {
      T data = it.next();
      if (data.equals(item)) {
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

  private class LinkedListIterator implements ListIterator<T> {

    private int index;
    private Node<T> previous;
    private Node<T> current;
    private Node<T> next;

    public LinkedListIterator() {
      next = head;
      previous = null;
      current = null;
      index = 0;
    }

    @Override
    public T next() {
      if (!hasNext()) {
        throw new IndexOutOfBoundsException();
      }

      T data = next.data;
      previous = next;
      current = next;
      next = next.next;
      index++;

      return data;
    }

    @Override
    public boolean hasNext() {
      return next != null;
    }

    @Override
    public void remove() {
      if (current == null) {
        throw new IllegalStateException();
      }
      
      if (current == head) {
         head = next;
         previous = null;

         if (next != null) {
           next.prev = null;
         }
      } else if (current == tail) {
        tail = previous;
        
        if (previous != null) {
          previous.next = null;
        }
      } else {
        if (current == next) {
          previous.next = next.next;
          next.next.prev = previous;
          next = next.next;
        } else {
          next.prev = previous.prev;
          previous.prev.next = next;
          previous = previous.prev;
        }
      }
      
      size--;
      current = null;
    }

    @Override
    public boolean hasPrevious() {
      return previous != null;
    }

    @Override
    public T previous() {
      if (!hasPrevious()) {
        throw new IndexOutOfBoundsException();
      }
      
      T data = previous.data;
      next = previous;
      current = previous;
      previous = previous.prev;
      index--;

      return data;
    }

    @Override
    public int nextIndex() {
      return index;
    }

    @Override
    public int previousIndex() {
      return index - 1;
    }

    @Override
    public void set(T e) {
      if (!hasNext()) {
        throw new IndexOutOfBoundsException();
      }
      
      if (current == null) {
        throw new IllegalStateException();
      }

      current.data = e;
    }

    @Override
    public void add(T e) {
      current = null;
      index++;
      size++;
      if (head == null) {
        head = new Node<T>(e, null, null);
        tail = head;
        previous = tail;
        return;
      }
      
      Node<T> newNode = new Node<T>(e, next, previous);
      if (previous != null) {
        previous.next = newNode;
      }
      
      previous = newNode;
      
      if (next != null) {
        next.prev = previous;
      }
      
      if (next == head) {
        head = newNode;
      }
      
      if (next == null) {
        tail = newNode;
      }
    }
  }

  private static class Node<T> {

    T data;

    Node<T> next;
    Node<T> prev;

    private Node(T data, Node<T> next, Node<T> prev) {
      this.data = data;
      this.next = next;
      this.prev = prev;
    }

    @Override
    public String toString() {
      if (data == null) {
        return null;
      } else {
        return data.toString();
      }
    }
  }

  @Override
  public Iterator<T> iterator() {
    return new LinkedListIterator();
  }

  @Override
  public ListIterator<T> listIterator() {
    return new LinkedListIterator();
  }
}
