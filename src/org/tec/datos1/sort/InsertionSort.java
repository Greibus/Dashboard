package org.tec.datos1.sort;

public class InsertionSort<T extends Comparable<T>> {
	
	private void swap(T[] arreglo, int i, int j) {
		T temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
	  }
	
	public void insertionSort(T[] arreglo) {
		int inicio = 0;
        for (int i = 1; i < arreglo.length; i++) {
        	int j = i;
        	int temp = i;
        	while (j != inicio) {
        		if (arreglo[j - 1].compareTo(arreglo[temp]) > 0) {
        			swap(arreglo, j - 1, temp);
        			j --;
            		temp --;
        		} else {
        			break;
        		}
        		
        	}
        }
	}
	
	public void imprimir(T[] array) {
        for (int i=0; i< array.length; ++i)
            System.out.print(array[i] + " ");
    }
	
}	

