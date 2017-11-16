package org.tec.datastructures.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.tec.datastructures.hierarchical.BinaryTree;

class BinaryTreeTest {
	@Test
	public void TestIsEmpty() {
		BinaryTree<Integer> test = new BinaryTree<Integer>();
		assertNotNull(test.isEmpty());
	}
	
	@Test
	public void TestContains() {
		BinaryTree<Integer> test = new BinaryTree<Integer>();
		assertTrue(test.isEmpty());
		test.insert(4);
		assertFalse(test.isEmpty());
		test.insert(6);
		test.insert(10);
		assertFalse(test.contains(120));
		assertTrue(test.contains(10));
	}
	@Test
	public void TestFindmin() {
		BinaryTree<Integer> test = new BinaryTree<Integer>();
		assertTrue(test.isEmpty());
		test.insert(50);
		assertFalse(test.isEmpty());
		test.insert(7);
		test.insert(11);
		int result = test.findmin().element;
		assertEquals(7, result);
	}
	@Test
	public void TestFinmax() {
		BinaryTree<Integer> test = new BinaryTree<Integer>();
		assertTrue(test.isEmpty());
		test.insert(10);
		assertFalse(test.isEmpty());
		test.insert(60);
		test.insert(14);
		int result = test.findmax().element;
		assertEquals(60, result);
	}
	@Test
	public void TestInsert() {
		BinaryTree<Integer> test = new BinaryTree<Integer>();
		assertTrue(test.isEmpty());
		test.insert(4);
		assertFalse(test.isEmpty());
		test.insert(6);
		test.insert(10);
		assertTrue(test.contains(10));
	}
	@Test
	public void TestRemove() {
		BinaryTree<Integer> test = new BinaryTree<Integer>();
		assertTrue(test.isEmpty());
		test.insert(4);
		assertFalse(test.isEmpty());
		test.insert(6);
		test.remove(7);
		test.remove(6);
		assertFalse(test.contains(7));
		assertFalse(test.contains(6));
	}

}
