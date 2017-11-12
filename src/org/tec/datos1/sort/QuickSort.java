package org.tec.datos1.sort;

public class QuickSort<T extends Comparable<T>> {
	
	private void swap(T[] arreglo, int i, int j) {
		T temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
	  }
	
	public void quickSort(T[] arreglo) {
		quickSort(arreglo, 0, arreglo.length -1);
	}
	
	private void quickSort(T[] arreglo, int i, int j) {
		int izq = i;
		int der = j;
		if ( i < j) {
			int pivote = (i + j)/ 2;
			while (i <= j) {
				while (arreglo[i].compareTo(arreglo[pivote]) < 0) {
					i++;
				}
				while (arreglo[j].compareTo(arreglo[pivote]) > 0) {
					j--;
				}
				
				if (i <= j) {                                          
			         swap(arreglo, i, j);
			         i++;
			         j--;
			     }
				
			}
			if (izq < j)
	            quickSort(arreglo, izq, j);
	        if (i < der)
	            quickSort(arreglo, i, der);
	    }
			
	}
	public void imprimir(T[] array) {
        for (int i=0; i< array.length; ++i)
            System.out.print(array[i] + " ");
    }
}

