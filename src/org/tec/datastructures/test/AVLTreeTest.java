package org.tec.datastructures.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.tec.datastructures.hierarchical.AVLTree;
import org.tec.datastructures.nodes.AVLNodo;

class AVLTreeTest {

	@Test
	public void TestObtenerroot() {
		AVLTree<Integer> test = new AVLTree<Integer>();
		assertNull(test.obtenerroot());
		test.insertar(3);
		assertNotNull(test.obtenerroot());
		int result = test.obtenerroot().dato;
		assertEquals(3, result);
	}

	@Test
	public void TestBuscar() {
		AVLTree<Integer> test = new AVLTree<Integer>();
		test.insertar(2);
		assertNull(test.buscar(4));
		assertNotNull(test.buscar(2));
	}

	@Test
	public void TestObtenerheight() {
		AVLTree<Integer> test = new AVLTree<Integer>();
		test.insertar(6);
		test.insertar(4);
		test.insertar(2);
		int result = test.obtenerheight(test.obtenerroot());
		assertEquals(0, result);

	}

	@Test
	public void TestRotacionIzquierda() {
		AVLTree<Integer> test = new AVLTree<Integer>();
		test.insertar(9);
		test.insertar(4);
		test.insertar(2);
		int result = test.obtenerroot().dato;
		assertEquals(4, result);
	}

	@Test
	public void TestRotacionDerecha() {
		AVLTree<Integer> test = new AVLTree<Integer>();
		test.insertar(2);
		test.insertar(4);
		test.insertar(6);
		int result = test.obtenerroot().dato;
		assertEquals(4, result);
	}

	@Test
	public void TestRotacionDobleIzquierda() {
		AVLTree<Integer> test = new AVLTree<Integer>();
		test.insertar(9);
		test.insertar(4);
		test.insertar(6);
		int result = test.obtenerroot().dato;
		assertEquals(6, result);
	}

	@Test
	public void TestRotacionDobleDerecha() {
		AVLTree<Integer> test = new AVLTree<Integer>();
		test.insertar(9);
		test.insertar(6);
		test.insertar(7);
		int result = test.obtenerroot().dato;
		assertEquals(7, result);
	}

	public void TestInsertarAVL() {
		AVLTree<Integer> test = new AVLTree<Integer>();
		test.insertar(2);
		assertNotNull(test.buscar(2));
	}

	@Test
	public void TestInsertar() {
		AVLTree<Integer> test = new AVLTree<Integer>();
		test.insertar(3);
		test.insertar(5);
		assertNotNull(test.buscar(3));
		
		
	}

	
	@Test
	public void TestFindMin() {
		AVLTree<Integer> test = new AVLTree<Integer>();
		test.insertar(9);
		test.insertar(4);
		test.insertar(6);
		test.insertar(3);
		test.insertar(7);
		int result = test.findMin(test.obtenerroot()).dato;
		assertEquals(3, result);
	}

	@Test
	public void TestBalancear() {
		AVLTree<Integer> test = new AVLTree<Integer>();
		test.insertar(9);
		test.insertar(4);
		test.insertar(6);
		int result = test.obtenerheight(test.obtenerroot());
		assertEquals(0, result);
		
	}

	@Test
	public void TestEliminarAVL() {
		AVLTree<Integer> test = new AVLTree<Integer>();
		test.insertar(9);
		test.insertar(4);
		test.insertar(6);
		test.eliminarAVL(4);
		assertNull(test.buscar(4));
		
	}


}
