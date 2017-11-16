package org.tec.datos1.benchmark;



import org.tec.datos1.search.BinarySearch;
import org.tec.datos1.search.InterpolationSearch;
import org.tec.datos1.sort.*;

import java.awt.Color;

import javax.swing.JFrame;

import org.math.plot.*;


public class Main<T extends Comparable<T>> {
	public static void main(String[] args) {

		BubbleSort<Integer> buble = new BubbleSort<Integer>();
		SelectionSort<Integer> selection = new SelectionSort<Integer>();
		InsertionSort<Integer> insertion = new InsertionSort<Integer>();
		MergeSort<Integer> merge = new MergeSort<Integer>();
		QuickSort<Integer> quick = new QuickSort<Integer>();
		ShellSort<Integer> shell = new ShellSort<Integer>();
		BinarySearch<Integer> bi = new BinarySearch<Integer>();
		Benchmark<Integer> bench = new Benchmark<Integer>();


		// PARA GRAFICAR
		double[] numeroElementos = new double[10];
		numeroElementos[0] = 50;
		numeroElementos[1] = 100;
		numeroElementos[2] = 200;
		numeroElementos[3] = 300;
		numeroElementos[4] = 400;
		numeroElementos[5] = 500;
		numeroElementos[6] = 600;
		numeroElementos[7] = 700;
		numeroElementos[8] = 800;
		numeroElementos[9] = 900;
		
		Integer [] array50 = new Integer[50];
		Integer [] array100 = new Integer[100];
		Integer [] array200 = new Integer[200];
		Integer [] array300 = new Integer[300];
		Integer [] array400 = new Integer[400];
		Integer [] array500 = new Integer[500];
		Integer [] array600 = new Integer[600];
		Integer [] array700 = new Integer[700];
		Integer [] array800 = new Integer[800];
		Integer [] array900 = new Integer[900];
		
	    for (int x=0;x<array50.length;x++)
	        array50[x] = (int) (Math.random()*50)+1;
	    
	    for (int x=0;x<array100.length;x++)
	        array100[x] = (int) (Math.random()*100)+1;
	    
	    for (int x=0;x<array200.length;x++)
	        array200[x] = (int) (Math.random()*200)+1;
	    
	    for (int x=0;x<array300.length;x++)
	        array300[x] = (int) (Math.random()*300)+1;
	    
	    for (int x=0;x<array400.length;x++)
	        array400[x] = (int) (Math.random()*400)+1;
	    
	    for (int x=0;x<array500.length;x++)
	        array500[x] = (int) (Math.random()*500)+1;
	    
	    for (int x=0;x<array600.length;x++)
	        array600[x] = (int) (Math.random()*600)+1;
	    
	    for (int x=0;x<array700.length;x++)
	        array700[x] = (int) (Math.random()*700)+1;
	    
	    for (int x=0;x<array800.length;x++)
	        array800[x] = (int) (Math.random()*800)+1;
	    
	    for (int x=0;x<array900.length;x++)
	        array900[x] = (int) (Math.random()*900)+1;
	    
	    double [] quicksortN = new double[10];
	    quicksortN[0] = bench.medir(quick, array50.clone());
	    quicksortN[1] = bench.medir(quick, array100.clone());
	    quicksortN[2] = bench.medir(quick, array200.clone());
	    quicksortN[3] = bench.medir(quick, array300.clone());
	    quicksortN[4] = bench.medir(quick, array400.clone());
	    quicksortN[5] = bench.medir(quick, array500.clone());
	    quicksortN[6] = bench.medir(quick, array600.clone());
	    quicksortN[7] = bench.medir(quick, array700.clone());
	    quicksortN[8] = bench.medir(quick, array800.clone());
	    quicksortN[9] = bench.medir(quick, array900.clone());
	    double [] bubblesortN = new double[10];
	    bubblesortN[0] = bench.medir(buble, array50.clone());
	    bubblesortN[1] = bench.medir(buble, array100.clone());
	    bubblesortN[2] = bench.medir(buble, array200.clone());
	    bubblesortN[3] = bench.medir(buble, array300.clone());
	    bubblesortN[4] = bench.medir(buble, array400.clone());
	    bubblesortN[5] = bench.medir(buble, array500.clone());
	    bubblesortN[6] = bench.medir(buble, array600.clone());
	    bubblesortN[7] = bench.medir(buble, array700.clone());
	    bubblesortN[8] = bench.medir(buble, array800.clone());
	    bubblesortN[9] = bench.medir(buble, array900.clone());
	    double [] insertionsortN = new double[10];
	    insertionsortN[0] = bench.medir(insertion, array50.clone());
	    insertionsortN[1] = bench.medir(insertion, array100.clone());
	    insertionsortN[2] = bench.medir(insertion, array200.clone());
	    insertionsortN[3] = bench.medir(insertion, array300.clone());
	    insertionsortN[4] = bench.medir(insertion, array400.clone());
	    insertionsortN[5] = bench.medir(insertion, array500.clone());
	    insertionsortN[6] = bench.medir(insertion, array600.clone());
	    insertionsortN[7] = bench.medir(insertion, array700.clone());
	    insertionsortN[8] = bench.medir(insertion, array800.clone());
	    insertionsortN[9] = bench.medir(insertion, array900.clone());
	    double [] mergesortN = new double[10];
	    mergesortN[0] = bench.medir(merge, array50.clone());
	    mergesortN[1] = bench.medir(merge, array100.clone());
	    mergesortN[2] = bench.medir(merge, array200.clone());
	    mergesortN[3] = bench.medir(merge, array300.clone());
	    mergesortN[4] = bench.medir(merge, array400.clone());
	    mergesortN[5] = bench.medir(merge, array500.clone());
	    mergesortN[6] = bench.medir(merge, array600.clone());
	    mergesortN[7] = bench.medir(merge, array700.clone());
	    mergesortN[8] = bench.medir(merge, array800.clone());
	    mergesortN[9] = bench.medir(merge, array900.clone());
	    double [] selectionsortN = new double[10];
	    selectionsortN[0] = bench.medir(selection, array50.clone());
	    selectionsortN[1] = bench.medir(selection, array100.clone());
	    selectionsortN[2] = bench.medir(selection, array200.clone());
	    selectionsortN[3] = bench.medir(selection, array300.clone());
	    selectionsortN[4] = bench.medir(selection, array400.clone());
	    selectionsortN[5] = bench.medir(selection, array500.clone());
	    selectionsortN[6] = bench.medir(selection, array600.clone());
	    selectionsortN[7] = bench.medir(selection, array700.clone());
	    selectionsortN[8] = bench.medir(selection, array800.clone());
	    selectionsortN[9] = bench.medir(selection, array900.clone());
	    double [] shellsortN = new double[10];
	    shellsortN[0] = bench.medir(shell, array50.clone());
	    shellsortN[1] = bench.medir(shell, array100.clone());
	    shellsortN[2] = bench.medir(shell, array200.clone());
	    shellsortN[3] = bench.medir(shell, array300.clone());
	    shellsortN[4] = bench.medir(shell, array400.clone());
	    shellsortN[5] = bench.medir(shell, array500.clone());
	    shellsortN[6] = bench.medir(shell, array600.clone());
	    shellsortN[7] = bench.medir(shell, array700.clone());
	    shellsortN[8] = bench.medir(shell, array800.clone());
	    shellsortN[9] = bench.medir(shell, array900.clone());
	    
	    //for (i = 0;)
	    
	    System.out.println(" ");
	    System.out.println("QUICKSORT ");
	    for (int i = 0; i< quicksortN.length;i++) {
	    	System.out.println(quicksortN[i]/1000);	
	    }
	    System.out.println(" ");
	    System.out.println("SELECTIONSORT ");
	    for (int i = 0; i< selectionsortN.length;i++) {
	    	System.out.println(selectionsortN[i]/1000);	
	    }
	    System.out.println(" ");
	    System.out.println("INSERTIONSORT ");
	    for (int i = 0; i< insertionsortN.length;i++) {
	    	System.out.println(insertionsortN[i]/1000);	
	    }
	    System.out.println(" ");
	    System.out.println("SHELLSORT ");
	    for (int i = 0; i< shellsortN.length;i++) {
	    	System.out.println(shellsortN[i]/1000);	
	    }
	    System.out.println(" ");
	    System.out.println("MERGESORT ");
	    for (int i = 0; i< mergesortN.length;i++) {
	    	System.out.println(mergesortN[i]/1000);	
	    }
	    System.out.println(" ");
	    System.out.println("BUBBLESORT ");
	    for (int i = 0; i< bubblesortN.length;i++) {
	    	System.out.println(bubblesortN[i]/1000);	
	    }
	    
	    Plot2DPanel plot = new Plot2DPanel();
	    
	    plot.addLegend("SOUTH");
	    plot.addLinePlot("quickSort", Color.RED, numeroElementos, quicksortN);
	    plot.addLinePlot("mergeSort", Color.BLUE, numeroElementos, mergesortN);
	    plot.addLinePlot("bubbleSort", Color.CYAN, numeroElementos, bubblesortN);
	    plot.addLinePlot("insertionSort", Color.BLACK, numeroElementos, insertionsortN);
	    plot.addLinePlot("selectionSort", Color.GRAY, numeroElementos, selectionsortN);
	    plot.addLinePlot("shellSort", Color.GREEN, numeroElementos, shellsortN);
	    plot.setAxisLabels("<X>", "NumeroDatos");
	    plot.setAxisLabels("NumeroDatos", "Tiempo");
	    
	    JFrame frame = new JFrame(" rendimiento ");
	    
	    frame.setSize(600, 600);
	    frame.setContentPane(plot);
	    frame.setVisible(true);
		
	}

}
