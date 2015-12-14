package com.spharris.datastructures;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.*;

public class TestLinkedList {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void isEmptyReturnsTrueForEmptyList() {
		// Arrange
		LinkedList<Object> l = new LinkedList<>();
		
		// Act
		// None
		
		// Assert
		assertThat(l.isEmpty(), equalTo(true));
	}
	
	@Test
	public void emptyListHasZeroSize() {
		// Arrange
		LinkedList<Object> l = new LinkedList<Object>();
		
		// Act
		// None
		
		// Assert
		assertThat(l.size(), equalTo(0));
	}
	
	@Test
	public void addOneItem() {
		LinkedList<Integer> l = new LinkedList<>();
		
		l.add(5);
		
		assertThat(l.size(), equalTo(1));
		assertThat(l.isEmpty(), equalTo(false));
	}
	
	@Test
	public void getAnItem() {
		LinkedList<Integer> l = new LinkedList<>();
		
		l.add(5);
		
		assertThat(l.get(0), equalTo(5));
	}
	
	@Test
	public void getAnItemThatDoesntExist() {
		LinkedList<Integer> l = new LinkedList<>();
		
		thrown.expect(IndexOutOfBoundsException.class);
		
		l.get(0);
	}
	
	@Test
	public void getSecondItem() {
		LinkedList<Integer> l = new LinkedList<>();
		
		l.add(5);
		l.add(6);
		
		assertThat(l.get(0), equalTo(5));
		assertThat(l.get(1), equalTo(6));
	}
	
	@Test
	public void addManyItems() {
		LinkedList<Integer> l = new LinkedList<>();
		
		int numItems = 10;
		for (int i = 0; i < numItems; i++) {
			l.add(i);
		}
		
		assertThat(l.size(), equalTo(numItems));
		for (int i = 0; i < numItems; i++) {
			assertThat(l.get(i), equalTo(i));
		}
	}
	
	@Test
	public void insert() {
		LinkedList<Integer> l = new LinkedList<>();
		
		int numItems = 10;
		for (int i = 0; i < numItems; i++) {
			l.add(i);
		}
		
		l.insert(4, 16);
		assertThat(l.size(), equalTo(11));
		assertThat(l.get(4), equalTo(16));
	}
	
	@Test
	public void insertAfterEndThrowsException() {
		LinkedList<Integer> l = new LinkedList<>();
		
		int numItems = 10;
		for (int i = 0; i < numItems; i++) {
			l.add(i);
		}
		
		thrown.expect(IndexOutOfBoundsException.class);
		
		l.insert(15, 10);
	}
	
	@Test
	public void insertAtEnd() {
		LinkedList<Integer> l = new LinkedList<>();
		
		int numItems = 10;
		for (int i = 0; i < numItems; i++) {
			l.add(i);
		}
		
		l.insert(numItems, numItems);
		assertThat(l.size(), equalTo(11));
		for (int i = 0; i < numItems + 1; i++) {
			assertThat(l.get(i), equalTo(i));
		}
	}
	
	@Test
	public void insertAtFront() {
		LinkedList<Integer> l = new LinkedList<>();
		
		int numItems = 10;
		for (int i = 0; i < numItems; i++) {
			l.add(i);
		}
		
		l.insert(0, 25);
		assertThat(l.size(), equalTo(11));
		assertThat(l.get(0), equalTo(25));
		for (int i = 1; i < numItems + 1; i++) {
			assertThat(l.get(i), equalTo(i - 1));
		}
	}
}
