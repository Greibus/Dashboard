package org.tec.datastructures.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.tec.datastructures.linear.Queue;

class QueueTest {

	@Test
	public void TestEnqueue() {
		Queue<Integer> test = new Queue<Integer>();
		test.enqueue(1);
		test.enqueue(3);
		int result = test.peek();
		assertEquals(1, result);
	}
	
	@Test
	public void TestDequeue() {
		Queue<Integer> test = new Queue<Integer>();
		assertNull(test.peek());
		test.enqueue(1);
		test.enqueue(3);
		test.dequeue();
		int result = test.peek();
		assertEquals(3, result);
	}
	
	@Test
	public void TestPeek() {
		Queue<Integer> test = new Queue<Integer>();
		assertNull(test.peek());
		test.enqueue(1);
		test.enqueue(3);
		test.enqueue(6);
		test.enqueue(17);
		int result = test.peek();
		assertEquals(1, result);
	}
	
	@Test
	public void TestSize() {
		Queue<Integer> test = new Queue<Integer>();
		assertNull(test.peek());
		test.enqueue(1);
		test.enqueue(3);
		test.dequeue();
		test.enqueue(4);
		int result = test.size();
		assertEquals(2, result);
	}
	
	@Test
	public void TestPrint() {
		Queue<Integer> test = new Queue<Integer>();
		assertNull(test.peek());
		test.enqueue(1);
		test.dequeue();
		test.print();
		assertNull(test.peek());
		test.enqueue(3);
		test.print();
		int result = test.peek();
		assertEquals(3, result);
	}
	
	@Test
	public void TestEmpty() {
		Queue<Integer> test = new Queue<Integer>();
		assertNull(test.peek());
		test.enqueue(1);
		test.enqueue(3);
		assertNotNull(test.peek());
	}
	
	
	
	
	
	
	
	
	
	
	
}
