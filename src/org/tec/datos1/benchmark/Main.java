package org.tec.datos1.benchmark;

import org.tec.datastructures.hierarchical.AVLTree;
import org.tec.datastructures.hierarchical.BTreeP;
import org.tec.datastructures.linear.CircularList;
import org.tec.datastructures.linear.DoubleList;
import org.tec.datastructures.linear.Queue;
import org.tec.datastructures.linear.Stack;
import org.tec.datos1.search.BinarySearch;
import org.tec.datos1.sort.*;


import static org.math.array.StatisticSample.*;


public class Main<T extends Comparable<T>> {
	public static void main(String[] args) {
//		ListaSimple<Integer> l1 = new ListaSimple<Integer>();
//		DoubleList<Integer> l2 = new DoubleList<Integer>();
//		CircularList<Integer> l3 = new CircularList<Integer>();
//		Stack<Integer> p = new Stack<Integer>();
//		Queue<Integer> q = new Queue<Integer>();
//		AVLTree<Integer> b = new AVLTree<Integer>();
		BubbleSort<Integer> buble = new BubbleSort<Integer>();
		SelectionSort<Integer> selection = new SelectionSort<Integer>();
		InsertionSort<Integer> insertion = new InsertionSort<Integer>();
		MergeSort<Integer> merge = new MergeSort<Integer>();
		QuickSort<Integer> quick = new QuickSort<Integer>();
		ShellSort<Integer> shell = new ShellSort<Integer>();
		BinarySearch<Integer> bi = new BinarySearch<Integer>();
		BTreeP ba = new BTreeP(2);
		Benchmark<Integer> bench = new Benchmark<Integer>();
		
//		double[] x = randomNormal(1000, 0, 1);
//		T[] result = (T[])x.length;
//		bench.medir(buble, x);
//		l3.insertFirst(3);
//		l3.insertFirst(2);
//		l3.insertFirst(1);
//		l3.insertEnd(5);
	//	l3.search(10);
		// PARA GRAFICAR
		Integer [] numeroElementos = new Integer[5];
		numeroElementos[0] = 100;
		numeroElementos[1] = 200;
		numeroElementos[2] = 500;
		numeroElementos[3] = 700;
		numeroElementos[4] = 1000;
		
		Integer [] array100 = new Integer[100];
		Integer [] array200 = new Integer[200];
		Integer [] array500 = new Integer[500];
		Integer [] array700 = new Integer[700];
		Integer [] array1000 = new Integer[1000];
		
	    for (int x=0;x<array100.length;x++)
	        array100[x] = (int) (Math.random()*100)+1;
	    
	    for (int x=0;x<array200.length;x++)
	        array200[x] = (int) (Math.random()*200)+1;
	    
	    for (int x=0;x<array500.length;x++)
	        array500[x] = (int) (Math.random()*500)+1;
	    
	    for (int x=0;x<array700.length;x++)
	        array700[x] = (int) (Math.random()*700)+1;
	    
	    for (int x=0;x<array1000.length;x++)
	        array1000[x] = (int) (Math.random()*1000)+1;
	    
	    Long [] quicksortN = new Long[5];
	    quicksortN[0] = bench.medir(quick, array100.clone());
	    quicksortN[1] = bench.medir(quick, array200.clone());
	    quicksortN[2] = bench.medir(quick, array500.clone());
	    quicksortN[3] = bench.medir(quick, array700.clone());
	    quicksortN[4] = bench.medir(quick, array1000.clone());
	    Long [] bubblesortN = new Long[5];
	    bubblesortN[0] = bench.medir(buble, array100.clone());
	    bubblesortN[1] = bench.medir(buble, array200.clone());
	    bubblesortN[2] = bench.medir(buble, array500.clone());
	    bubblesortN[3] = bench.medir(buble, array700.clone());
	    bubblesortN[4] = bench.medir(buble, array1000.clone());
	    Long [] insertionsortN = new Long[5];
	    insertionsortN[0] = bench.medir(insertion, array100.clone());
	    insertionsortN[1] = bench.medir(insertion, array200.clone());
	    insertionsortN[2] = bench.medir(insertion, array500.clone());
	    insertionsortN[3] = bench.medir(insertion, array700.clone());
	    insertionsortN[4] = bench.medir(insertion, array1000.clone());
	    Long [] mergesortN = new Long[5];
	    mergesortN[0] = bench.medir(merge, array100.clone());
	    mergesortN[1] = bench.medir(merge, array200.clone());
	    mergesortN[2] = bench.medir(merge, array500.clone());
	    mergesortN[3] = bench.medir(merge, array700.clone());
	    mergesortN[4] = bench.medir(merge, array1000.clone());
	    Long [] selectionsortN = new Long[5];
	    selectionsortN[0] = bench.medir(selection, array100.clone());
	    selectionsortN[1] = bench.medir(selection, array200.clone());
	    selectionsortN[2] = bench.medir(selection, array500.clone());
	    selectionsortN[3] = bench.medir(selection, array700.clone());
	    selectionsortN[4] = bench.medir(selection, array1000.clone());
	    Long [] shellsortN = new Long[5];
	    shellsortN[0] = bench.medir(shell, array100.clone());
	    shellsortN[1] = bench.medir(shell, array200.clone());
	    shellsortN[2] = bench.medir(shell, array500.clone());
	    shellsortN[3] = bench.medir(shell, array700.clone());
	    shellsortN[4] = bench.medir(shell, array1000.clone());
	    
	    System.out.println(" ");
	    System.out.println("QUICKSORT ");
	    for (int i = 0; i< quicksortN.length;i++) {
	    	System.out.println(quicksortN[i]);	
	    }
	    System.out.println(" ");
	    System.out.println("SELECTIONSORT ");
	    for (int i = 0; i< selectionsortN.length;i++) {
	    	System.out.println(selectionsortN[i]);	
	    }
	    System.out.println(" ");
	    System.out.println("INSERTIONSORT ");
	    for (int i = 0; i< insertionsortN.length;i++) {
	    	System.out.println(insertionsortN[i]);	
	    }
	    System.out.println(" ");
	    System.out.println("SHELLSORT ");
	    for (int i = 0; i< shellsortN.length;i++) {
	    	System.out.println(shellsortN[i]);	
	    }
	    System.out.println(" ");
	    System.out.println("MERGESORT ");
	    for (int i = 0; i< mergesortN.length;i++) {
	    	System.out.println(mergesortN[i]);	
	    }
	    System.out.println(" ");
	    System.out.println("BUBBLESORT ");
	    for (int i = 0; i< bubblesortN.length;i++) {
	    	System.out.println(bubblesortN[i]);	
	    }
		//ba.insert(ba, 10);
		//ba.insert(ba, 11);
		//ba.insert(ba, 2);
		//ba.insert(ba, 40);
		//ba.insert(ba, 1);
		
		
		//Integer [] ary = {7, 6, 8, 9, 3, 2, 10, 5, 1};
		//bi.binarySearch(5, ary);
		//shell.shellSort(ary);
		//String [] arry = {"f", "x", "c", "a", "x" };
		//merge.mergeSort(ary);
		//merge.imprimir(ary);
		
		//quick.quickSort(ary);
		//quick.imprimir(ary);
	//	buble.bubbleSort(ary);
		//buble.imprimir(ary);
		
		//selection.selectionSort(arry);
		//selection.printSelectionSort(arry);
		
//		insertion.insertionSort(ary);
		//insertion.printInsertionSort(ary);
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
