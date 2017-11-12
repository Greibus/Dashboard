package org.tec.datos1.sort;

public class ShellSort<T extends Comparable<T>> {
	private void swap(T[] arreglo, int i, int j) {
		T temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
	  }
	
	public void shellSort(T[] arreglo) {
		boolean noCambios = true;
		for(int k = arreglo.length/2 ; k > 1; k--) {
			while (noCambios) {
				noCambios = false;
				for(int i = k; i< arreglo.length; i++) {
					if(arreglo[i-k].compareTo(arreglo[i]) > 0) { // y si están desordenados
						swap(arreglo, i, i-k);
//						aux=A[i]; // se reordenan
//						A[i]=A[i-salto];
//						A[i-salto]=aux;
//			
						noCambios = true;
					}
				}	
			}
		}
	}			
}
	

//	
//	for(int i = 0; k < arreglo.length - 1; i++) {
//		if (arreglo[i].compareTo(arreglo[k + i]) > 0) {
//			swap(arreglo, i, k + i);
	

