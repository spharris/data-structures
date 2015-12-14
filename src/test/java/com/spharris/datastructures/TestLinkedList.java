package com.spharris.datastructures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.*;

public class TestLinkedList {
	
	private static final int NUM_ITEMS = 10;
	
	private LinkedList<Integer> emptyList = new LinkedList<>();
	private LinkedList<Integer> singleItemList;
	private LinkedList<Integer> multiItemList;
	
	@Before
	public void createMultiItemList() {
		multiItemList = new LinkedList<>();
		for (int i = 0; i < NUM_ITEMS; i++) {
			multiItemList.add(i);
		}
	}
	
	@Before
	public void createSingleItemList() {
		singleItemList = new LinkedList<>();
		singleItemList.add(0);
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void isEmptyReturnsTrueForEmptyList() {
		assertThat(emptyList.isEmpty(), equalTo(true));
	}
	
	@Test
	public void emptyListHasZeroSize() {
		assertThat(emptyList.size(), equalTo(0));
	}
	
	@Test
	public void addOneItem() {
		assertThat(singleItemList.size(), equalTo(1));
		assertThat(singleItemList.isEmpty(), equalTo(false));
	}
	
	@Test
	public void getAnItem() {
		assertThat(singleItemList.get(0), equalTo(0));
	}
	
	@Test
	public void getAnItemThatDoesntExist() {
		thrown.expect(IndexOutOfBoundsException.class);
		
		emptyList.get(0);
	}
	
	@Test
	public void getSecondItem() {
		singleItemList.add(6);
		
		assertThat(singleItemList.get(0), equalTo(0));
		assertThat(singleItemList.get(1), equalTo(6));
	}
	
	@Test
	public void addManyItems() {
		assertThat(multiItemList.size(), equalTo(NUM_ITEMS));
		for (int i = 0; i < NUM_ITEMS; i++) {
			assertThat(multiItemList.get(i), equalTo(i));
		}
	}
	
	@Test
	public void insert() {
		multiItemList.insert(4, 16);
		assertThat(multiItemList.size(), equalTo(11));
		assertThat(multiItemList.get(4), equalTo(16));
	}
	
	@Test
	public void insertAfterEndThrowsException() {
		thrown.expect(IndexOutOfBoundsException.class);
		
		multiItemList.insert(15, 10);
	}
	
	@Test
	public void insertAtEnd() {
		multiItemList.insert(NUM_ITEMS, NUM_ITEMS);
		assertThat(multiItemList.size(), equalTo(11));
		for (int i = 0; i < NUM_ITEMS + 1; i++) {
			assertThat(multiItemList.get(i), equalTo(i));
		}
	}
	
	@Test
	public void insertAtFront() {
		multiItemList.insert(0, 25);
		assertThat(multiItemList.size(), equalTo(11));
		assertThat(multiItemList.get(0), equalTo(25));
		for (int i = 1; i < NUM_ITEMS + 1; i++) {
			assertThat(multiItemList.get(i), equalTo(i - 1));
		}
	}
	
	@Test
	public void removeFromEmptyList() {
		thrown.expect(IndexOutOfBoundsException.class);
		
		emptyList.removeAt(0);
	}
	
	@Test
	public void removeFromOneItemList() {
		int item = singleItemList.removeAt(0);
		
		assertThat(singleItemList.size(), equalTo(0));
		assertThat(item, equalTo(0));
	}
	
	@Test
	public void removeFromStartOfList() {
		int item = multiItemList.removeAt(0);
		assertThat(multiItemList.size(), equalTo(9));
		assertThat(item, equalTo(0));
	}
	
	@Test
	public void removeFromEndOfList() {
		int item = multiItemList.removeAt(9);
		assertThat(multiItemList.size(), equalTo(9));
		assertThat(item, equalTo(9));
	}
	
	@Test
	public void removeFromMiddleOfList() {
		int item = multiItemList.removeAt(3);
		assertThat(multiItemList.size(), equalTo(9));
		assertThat(item, equalTo(3));
	}
	
	@Test
	public void removeMultipleFromFront() {
		int first = multiItemList.removeAt(0);
		int second = multiItemList.removeAt(0);
		
		assertThat(multiItemList.size(), equalTo(8));
		assertThat(first, equalTo(0));
		assertThat(second, equalTo(1));
	}
	
	@Test
	public void removeMultipleFromEnd() {
		int first = multiItemList.removeAt(9);
		int second = multiItemList.removeAt(8);
		
		assertThat(multiItemList.size(), equalTo(8));
		assertThat(first, equalTo(9));
		assertThat(second, equalTo(8));
		
		for (int i = 0; i < multiItemList.size() - 1; i++) {
			assertThat(multiItemList.get(i), equalTo(i));
		}
	}
	
	@Test
	public void removeMultipleFromMiddle() {
		int first = multiItemList.removeAt(5);
		int second = multiItemList.removeAt(6);
		
		assertThat(multiItemList.size(), equalTo(8));
		assertThat(first, equalTo(5));
		assertThat(second, equalTo(7));
	}
	
	@Test
	public void emptyListContainsNothing() {
		assertThat(emptyList.contains(5), equalTo(false));
	}
	
	@Test
	public void listNotContainingValueReturnsFalse() {
		assertThat(singleItemList.contains(1), equalTo(false));
	}
	
	@Test
	public void listContainingValueReturnsTrue() {
		assertThat(singleItemList.contains(0), equalTo(true));
	}
	
	@Test
	public void listContainingValueAtEndTrue() {
		assertThat(multiItemList.contains(9), equalTo(true));
	}
	
	@Test
	public void removeItem() {
		int item = multiItemList.remove(5);
		
		assertThat(multiItemList.size(), equalTo(9));
		assertThat(multiItemList.contains(5), equalTo(false));
		assertThat(item, equalTo(5));
	}
	
	@Test
	public void removeNonExistingItem() {
		Integer item = multiItemList.remove(23);
		
		assertThat(item, equalTo(null));
	}
}
