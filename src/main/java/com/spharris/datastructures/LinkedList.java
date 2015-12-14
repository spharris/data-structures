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
		insert(size, item);
	}
	
	public void insert(int index, T item) {
		if (index > size) {
			throw new IndexOutOfBoundsException();
		}
		
		if (index == 0) {
			Node<T> newNode = new Node<>(item, first, null);
			if (first == null) {
				// This was the first item in the list
				last = newNode;
			} else {
				first.setPrev(newNode);
			}
			
			first = newNode;
		} else if (index == size) {
			// Inserting at end of list. Don't set a next item
			Node<T> node = last;
			Node<T> newNode = new Node<>(item, null, node);
			last = newNode;
			
			// node can't be null because the list would have been empty and it would have
			// been caught above
			node.setNext(newNode);
		} else {
			// Setting in the middle of the list
			Node<T> node = getNode(index);
			Node<T> newNode = new Node<>(item, node.getPrev(), node);
			node.getPrev().setNext(newNode);
			node.setPrev(newNode);
		}
		
		size++;
	}
	
	public T remove(int index) {
		Node<T> node = getNode(index);
		
		if (node == first) {
			first = node.getNext();
		}
		
		if (node == last) {
			last = node.getPrev();
		}
		
		if (node.getPrev() != null) {
			node.getPrev().setNext(node.getNext());
		}
		
		if (node.getNext() != null) {
			node.getNext().setPrev(node.getPrev());
		}
		
		size--;
		
		return node.getData();
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
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		if (size > 0) {
			sb.append(first.toString());
			for (int i = 1; i < size; i++) {
				sb.append(", " + get(i).toString());
			}
		}
		
		sb.append("]");
		return sb.toString();
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
		
		public String toString() {
			if (data == null) {
				return null;
			} else {
				return data.toString();
			}
		}
	}
}
