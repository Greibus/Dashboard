package org.tec.datos1.sort;

import org.tec.datos1.benchmark.Sorting;

public class ShellSort<T extends Comparable<T>> implements Sorting<T> {
	private void swap(T[] array, int i, int j) {
		T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
	  }
	
	public void shellSort(T[] array) {
				int gap = (array.length - 1) / 2;
				while(gap > 0) {
						for(int index = 0 ;index+gap < array.length ; index++) {
						int indexK= 0;
						while(indexK <= index && index - indexK + gap > 0 && array[index-indexK].compareTo(array[index-indexK+gap]) > 0){
							swap (array, index - indexK, index - indexK + gap);
							indexK += gap;
						}
					}
					gap = gap / 2;
				}
			}
	
	public void printShellSort(T[] array) {
        for (int i=0; i< array.length; ++i)
            System.out.print(array[i] + " ");
    }
	
	 public void execute(T [] miArray) {
		 this.shellSort(miArray);   
	    }
}