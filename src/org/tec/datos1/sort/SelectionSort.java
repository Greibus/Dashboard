package org.tec.datos1.sort;

import org.tec.datos1.benchmark.Sorting;

public class SelectionSort<T extends Comparable<T>> implements Sorting<T> {
	
	private void swap(T[] array, int i, int j) {
		T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
	  }
	
	public void selectionSort(T[] array) {
		for (int i = 0; i < array.length - 1; i++) { 
				int min = i;
				int position = i;
			for (int j = i + 1; j < array.length; j++ ) {
				if (array[j].compareTo(array[min]) < 0) {
					min = j;
				
				}
			}
			swap(array, position, min);
			
		}
		
	}
	

	
	public void printSelectionSort(T[] array) {
        for (int i=0; i< array.length; ++i)
            System.out.print(array[i] + " ");
    }
	 public void execute(T [] miArray) {
		 this.selectionSort(miArray);   
	    }
}	

