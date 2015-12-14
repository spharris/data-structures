package com.spharris.datastructures;

/**
 * A doubly-linked list
 */
public class LinkedList<T> {
	
	private Node<T> first;
	private Node<T> last;
	
	private int size = 0;
	
	/**
	 * Adds an item to the end of the list
	 */
	public void add(T item) {
		if (first == null) {
			first = new Node<>(item, null, null);
			last = first;
		} else {
			Node<T> newNode = new Node<>(item, null, last);
			last.setNext(newNode);
			last = newNode;
		}
		
		size++;
	}
	
	public void insert(int index, T item) {
		if (index > size) {
			throw new IndexOutOfBoundsException();
		}
		
		if (index == size) {
			Node<T> node = last;
			Node<T> newNode = new Node<>(item, node, null);
			node.setNext(newNode);
		} else {
			Node<T> node = getNode(index);
			Node<T> newNode = new Node<>(item, node.getPrev(), node);
			node.getPrev().setNext(newNode);
			node.setPrev(newNode);
		}
		
		size++;
	}
	
	public T remove(int index) {
		return null;
	}
	
	public T remove(T item) {
		return null;
	}
	
	public T get(int index) {
		Node<T> node = getNode(index);
		return node.getData();
	}
	
	public boolean contains(T item) {
		return false;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	private Node<T> getNode(int index) {
		if (index > (size - 1)) {
			throw new IndexOutOfBoundsException();
		}
		
		int currentIndex = 0;
		Node<T> currentNode = first;
		while (currentIndex != index) {
			currentNode = currentNode.getNext();
			currentIndex++;
		}
		
		return currentNode;
	}
	
	private static class Node<T> {
		
		private T data;
		
		private Node<T> next;
		private Node<T> prev;
		
		private Node(T data, Node<T> next, Node<T> prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
		
		public Node<T> getNext() {
			return next;
		}
		
		public void setNext(Node<T> node) {
			this.next = node;
		}
		
		public Node<T> getPrev() {
			return prev;
		}
		
		public void setPrev(Node<T> node) {
			this.prev = node;
		}
		
		public T getData() {
			return data;
		}
	}
}
