package org.tec.datos1.sort;

public class SelectionSort<T extends Comparable<T>> {
	
	private void swap(T[] arreglo, int i, int j) {
		T temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
	  }
	
	public void selectionSort(T[] arreglo) {
		for (int i = 0; i < arreglo.length - 1; i++) { 
				int min = i;
				int posicion = i;
			for (int j = i + 1; j < arreglo.length; j++ ) {
				if (arreglo[j].compareTo(arreglo[min]) < 0) {
					min = j;
				
				}
			}
			swap(arreglo, posicion, min);
			
		}
		
	}
	

	
	public void imprimir(T[] array) {
        for (int i=0; i< array.length; ++i)
            System.out.print(array[i] + " ");
    }
}	

