package org.tec.datastructures.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.tec.datastructures.linear.SimpleList;

class SimpleListTest {
	@Test
	public void TestInsertFirst() {
		SimpleList<Integer> test = new SimpleList<Integer>();
		assertNull(test.getFirst());
		test.insertFirst(1);
		assertNotNull(test.getFirst());
		int result = test.getFirst().getValue();
		assertEquals(1, result);
	}
	
	@Test
	public void TestPrintList() {
		SimpleList<Integer> test = new SimpleList<Integer>();
		assertNull(test.getFirst());
		test.insertFirst(3);
		assertNotNull(test.getFirst());
		test.printList();
		int result = test.getFirst().getValue();
		assertEquals(3, result);
	}
	
	@Test
	public void TestInsertEnd() {
		SimpleList<Integer> test = new SimpleList<Integer>();
		assertNull(test.getFirst());
		test.insertFirst(3);
		test.insertEnd(5);
		assertNotNull(test.getFirst());
		int result = test.getFirst().getNext().getValue();
		assertEquals(5, result);
		
	}
	
	@Test
	public void TestSearch() {
		SimpleList<Integer> test = new SimpleList<Integer>();
		assertNull(test.search(3));
		test.insertFirst(3);
		test.insertEnd(5);
		assertNotNull(test.getFirst());
		int result = test.search(5).getValue();
		assertEquals(5, result);
	}
	
	@Test
	public void TestSize() {
		SimpleList<Integer> test = new SimpleList<Integer>();
		assertEquals(0, test.getSize());
		test.insertFirst(3);
		test.insertEnd(5);
		test.insertEnd(6);
		int result = test.getSize();
		assertEquals(3, result);
		
	}
	@Test
	public void TestRemove() {
		SimpleList<Integer> test = new SimpleList<Integer>();
		test.insertFirst(3);
		test.insertEnd(5);
		test.insertEnd(8);
		assertEquals(3, test.getSize());
		test.remove(5);
		assertNotEquals(5, test.getFirst().getNext().getValue());
		int result = test.getSize();
		assertEquals(2, result);
	}
}
