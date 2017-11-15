package org.tec.datastructures.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.tec.datastructures.linear.Stack;

class StackTest {
	
	@Test
	public void TestPush() {
		Stack<Integer> test = new Stack<Integer>();
		test.push(1);
		test.push(5);
		
		int result = test.peek();
		assertEquals(5, result);
	}
	
	@Test
	public void TestPop() {
		Stack<Integer> test = new Stack<Integer>();
		assertNull(test.pop());
		test.push(4);
		test.push(3);
		int pek = test.peek();
		assertEquals(3, pek);
		test.pop();
		int result = test.peek();
		assertEquals(4, result);
	}
	
	@Test
	public void TestPeek() {
		Stack<Integer> test = new Stack<Integer>();
		assertNull(test.peek());
		test.push(3);
		test.push(1);
		test.push(5);
		test.push(34);
		int result = test.peek();
		assertEquals(34, result);
	}
	
	@Test
	public void TestSize() {
		Stack<Integer> test = new Stack<Integer>();
		assertNull(test.peek());
		test.push(3);
		test.push(1);
		test.pop();
		int size = test.size();
		assertEquals(1, size);
		test.push(7);
		int result = test.size();
		assertEquals(2, result);
	}
	
	@Test
	public void TestPrint() {
		Stack<Integer> test = new Stack<Integer>();
		assertNull(test.peek());
		test.push(5);
		test.pop();
		test.print();
		assertNull(test.peek());
		test.push(6);
		test.print();
		int result = test.peek();
		assertEquals(6, result);
	}
	
	
	@Test
	public void TestEmpty() {
		Stack<Integer> test = new Stack<Integer>();
		assertNull(test.peek());
		test.push(4);
		test.push(2);
		assertNotNull(test.peek());
	}
}
