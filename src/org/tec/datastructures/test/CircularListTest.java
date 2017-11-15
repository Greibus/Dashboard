package org.tec.datastructures.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.tec.datastructures.linear.CircularList;

class CircularListTest {
	@Test
	public void TestInsertFirst() {
		CircularList<Integer> test = new CircularList<Integer>();
		assertNull(test.getFirst());
		test.insertFirst(1);
		assertNotNull(test.getFirst());
		int result = test.getFirst().getNext().getValue();
		assertEquals(1, result);
	}
	
	@Test
	public void TestPrint() {
		CircularList<Integer> test = new CircularList<Integer>();
		assertNull(test.getFirst());
		test.insertFirst(3);
		assertNotNull(test.getFirst());
		test.print();
		int result = test.getFirst().getValue();
		assertEquals(3, result);
	}
	
	@Test
	public void TestInsertEnd() {
		CircularList<Integer> test = new CircularList<Integer>();
		assertNull(test.getFirst());
		test.insertFirst(3);
		test.insertEnd(5);
		int result = test.getFirst().getNext().getNext().getValue();
		assertEquals(3, result);
		
	}
	
	@Test
	public void TestSearch() {
		CircularList<Integer> test = new CircularList<Integer>();
		assertNull(test.search(3));
		test.insertFirst(3);
		test.insertEnd(5);
		assertNotNull(test.getFirst());
		int result = test.search(5).getValue();
		assertEquals(5, result);
	}
	
	@Test
	public void TestSize() {
		CircularList<Integer> test = new CircularList<Integer>();
		assertEquals(0, test.getSize());
		test.insertFirst(3);
		test.insertEnd(5);
		test.insertEnd(6);
		int result = test.getSize();
		assertEquals(3, result);
		
	}
	@Test
	public void TestRemove() {
		CircularList<Integer> test = new CircularList<Integer>();
		test.insertFirst(3);
		test.insertEnd(5);
		test.insertEnd(8);
		assertEquals(3, test.getSize());
		test.remove(3);
		assertNotEquals(3, test.getFirst().getValue());
		int first = test.getFirst().getNext().getNext().getValue();
		assertEquals(5, first);
		int result = test.getSize();
		assertEquals(2, result);
	}

}
