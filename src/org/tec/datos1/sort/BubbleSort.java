package org.tec.datos1.sort;



public class BubbleSort<T extends Comparable<T>> {
	
	private void swap(T[] arreglo, int i, int j) {
		T temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
	  }
	
   
    public void bubbleSort(T[] arreglo) {
    	int last = 0;
    	boolean pasada = true;
        while (pasada) {
          pasada = false;
          for (int i = 1; i < arreglo.length - last; i++) {
        	  if (arreglo[i - 1].compareTo(arreglo[i]) > 0) {
        		  swap(arreglo, i, i - 1);
        		  pasada = true;
        	  }
        		  
    			
          } last += 1;
        }       
    }

    
 
    public void imprimir(T[] array) {
        for (int i=0; i< array.length; ++i)
            System.out.print(array[i] + " ");
    }
 
}