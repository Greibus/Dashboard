package org.tec.datos1.sort;

import org.tec.datos1.benchmark.Sorting;

public class InsertionSort<T extends Comparable<T>> implements Sorting<T> {
	
	private void swap(T[] array, int i, int j) {
		T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
	  }
	
	public void insertionSort(T[] array) {
		int start = 0;
        for (int i = 1; i < array.length; i++) {
        	int j = i;
        	int temp = i;
        	while (j != start) {
        		if (array[j - 1].compareTo(array[temp]) > 0) {
        			swap(array, j - 1, temp);
        			j --;
            		temp --;
        		} else {
        			break;
        		}
        		
        	}
        }
	}
	
	public void printInsertionSort(T[] array) {
        for (int i=0; i< array.length; ++i)
            System.out.print(array[i] + " ");
    }
	
    public void execute(T [] miArray) {
    	this.insertionSort(miArray);
    }
}	

