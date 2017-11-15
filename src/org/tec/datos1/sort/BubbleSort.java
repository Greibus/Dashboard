package org.tec.datos1.sort;

import org.tec.datos1.benchmark.Sorting;

public class BubbleSort<T extends Comparable<T>> implements Sorting<T> {
	
	private void swap(T[] array, int i, int j) {
		T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
	  }
	
   
    public void bubbleSort(T[] array) {
    	int last = 0;
    	boolean pasada = true;
        while (pasada) {
          pasada = false;
          for (int i = 1; i < array.length - last; i++) {
        	  if (array[i - 1].compareTo(array[i]) > 0) {
        		  swap(array, i, i - 1);
        		  pasada = true;
        	  }
          } last += 1;
        }       
    }

    
 
    public void printBubbleSort(T[] array) {
        for (int i=0; i< array.length; ++i)
            System.out.print(array[i] + " ");
    }
    
 
    public void execute(T [] miArray) {
    	this.bubbleSort(miArray);
    }


}