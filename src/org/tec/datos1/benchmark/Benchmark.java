package org.tec.datos1.benchmark;



public class Benchmark<T extends Comparable<T>> {
	
	public long medir(Sorting<T> sort, T [] array) {
		long total;
		long starTime = System.nanoTime();
		sort.execute(array);
		long endTime = System.nanoTime();
		total = endTime - starTime;
		return total;
		
		
	}

	

}
