package org.tec.datastructures.test;


import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.TestRule;
import org.tec.datos1.benchmark.Sorting;
import org.tec.datos1.sort.*;

import com.carrotsearch.junitbenchmarks.AbstractBenchmark;
import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import com.carrotsearch.junitbenchmarks.annotation.AxisRange;
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkMethodChart;


@AxisRange(min = 0, max = 1)
@BenchmarkMethodChart(filePrefix = "benchmark-sorts")
class SortingTest extends AbstractBenchmark {
	
	private static Integer [] array1000000 = new Integer[5000000];
	@BenchmarkOptions(benchmarkRounds = 20, warmupRounds = 0)
	@BeforeClass
	public static void prepare() {
		for (int x=0;x<array1000000.length;x++) {
	        array1000000[x] = (int) (Math.random()*5000000)+1;
		}
	}
	@Rule
	public TestRule benchmarkRun = new BenchmarkRule();

	@Test
	public void mergesort() throws InterruptedException {
		MergeSort<Integer> test = new MergeSort<Integer>();
		Thread.sleep(20);
		runTest(test, array1000000.clone());
		

	}
	@Test
	public void shellsort() throws InterruptedException {
		ShellSort<Integer> test = new ShellSort<Integer>();
		Thread.sleep(20);
		runTest(test, array1000000.clone());
		
	}
	
	@Test
	public void bubblesort() throws InterruptedException {
		BubbleSort<Integer> test = new BubbleSort<Integer>();
		Thread.sleep(20);
		runTest(test, array1000000.clone());
	}
	
	@Test
	public void insertionsort() throws InterruptedException {
		InsertionSort<Integer> test = new InsertionSort<Integer>();
		Thread.sleep(20);
		runTest(test, array1000000.clone());
	}
	
	@Test
	public void selectionsort() throws InterruptedException {
		SelectionSort<Integer> test = new SelectionSort<Integer>();
		Thread.sleep(20);
		runTest(test, array1000000.clone());
	}
	
	@Test
	public void quicksort() throws InterruptedException {
		QuickSort<Integer> test = new QuickSort<Integer>();
		Thread.sleep(20);
		runTest(test, array1000000.clone());
	}
	 private void runTest(Sorting<Integer>  sort, Integer[] array) {
		 prepare();
		 
		 
		 
	 }
}  


