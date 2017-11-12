package org.tec.datos1.datastructures;



import org.tec.datos1.search.BinarySearch;
import org.tec.datos1.sort.*;

public class Main {
	public static void main(String[] args) {
//		ListaSimple<Integer> l1 = new ListaSimple<Integer>();
		ListaDoble<Integer> l2 = new ListaDoble<Integer>();
		ListaCircular<Integer> l3 = new ListaCircular<Integer>();
		Stack<Integer> p = new Stack<Integer>();
		Queue<Integer> q = new Queue<Integer>();
		AVLTree<Integer> b = new AVLTree<Integer>();
		BubbleSort<Integer> buble = new BubbleSort<Integer>();
		SelectionSort<Integer> selection = new SelectionSort<Integer>();
		InsertionSort<Integer> insertion = new InsertionSort<Integer>();
		MergeSort<Integer> merge = new MergeSort<Integer>();
		QuickSort<Integer> quick = new QuickSort<Integer>();
		ShellSort<Integer> shell = new ShellSort<Integer>();
		BinarySearch<Integer> bi = new BinarySearch<Integer>();
		
		
		
		
		b.insertar(10);
		b.insertar(8);
		b.insertar(6);
		b.insertar(2);
		b.insertar(4);
		
		Integer [] ary = {1, 3, 4, 6, 7, 8, 10, 13, 14};
		bi.binarySearch(5, ary);
		shell.shellSort(ary);
		//merge.mergeSort(ary);
		//merge.imprimir(ary);
		
		//quick.quickSort(ary);
		//quick.imprimir(ary);
	//	buble.bubbleSort(ary);
		//buble.imprimir(ary);
		
	//	selection.selectionSort(ary);
		//selection.imprimir(ary);
		
//		insertion.insertionSort(ary);
//  	insertion.imprimir(ary);
//		q.enqueue(3);
//		q.enqueue(5);
//		q.imprimir();
//		q.dequeue();
//		q.imprimir();
//		
//		
//		p.push(1);
//		p.push(2);
//		p.push(4);
//		p.imprimir();
//		p.pop();
//		p.imprimir();
//		p.peek();
		
//		l1.insetarInicio(12);
//		l1.insetarInicio(19);
//		l1.imprimir();
//		l1.insertarFinal(20);
//		l1.eliminar(12);
//		l1.imprimir();
//		l1.eliminar(50);
//		l1.imprimir();
		
//	
//		l2.insertarInicio(9);
//		l2.insertarInicio(19);
//		l2.insertarFinal(10);
//		l2.imprimir();
//		l2.eliminar(20);
//		l2.imprimir();
//		l2.insertarInicio(10);

//		
//	l3.insertarFinal(12);
//	l3.insertarFinal(14);
//	l3.insertarFinal(15);
//	l3.insertarInicio(4);
//	l3.imprimir();
//	l3.buscar(14);
//	l3.eliminar(4);
//	l3.imprimir();
//	
//		
		
	}

}
