package org.tec.datastructures.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.tec.datastructures.linear.DoubleList;

public class DoubleListTest {

	@Test
	public void TestInsertFirst() {
		DoubleList<Integer> test = new DoubleList<Integer>();
		assertNull(test.getFirst());
		test.insertFirst(1);
		assertNotNull(test.getFirst());
		assertNull(test.getFirst().getPrevious());
		int result = test.getFirst().getValue();
		assertEquals(1, result);
	}
	
	@Test
	public void TestPrint() {
		DoubleList<Integer> test = new DoubleList<Integer>();
		assertNull(test.getFirst());
		test.insertFirst(3);
		assertNotNull(test.getFirst());
		test.print();
		int result = test.getFirst().getValue();
		assertEquals(3, result);
	}
	
	@Test
	public void TestInsertEnd() {
		DoubleList<Integer> test = new DoubleList<Integer>();
		assertNull(test.getFirst());
		test.insertFirst(3);
		test.insertEnd(5);
		assertNotNull(test.getFirst().getNext().getPrevious());
		int result = test.getFirst().getNext().getValue();
		assertEquals(5, result);
		
	}
	
	@Test
	public void TestSearch() {
		DoubleList<Integer> test = new DoubleList<Integer>();
		assertNull(test.search(3));
		test.insertFirst(3);
		test.insertEnd(5);
		assertNotNull(test.getFirst());
		int result = test.search(5).getValue();
		assertEquals(5, result);
	}
	
	@Test
	public void TestSize() {
		DoubleList<Integer> test = new DoubleList<Integer>();
		assertEquals(0, test.getSize());
		test.insertFirst(3);
		test.insertEnd(5);
		test.insertEnd(6);
		int result = test.getSize();
		assertEquals(3, result);
		
	}
	@Test
	public void TestRemove() {
		DoubleList<Integer> test = new DoubleList<Integer>();
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