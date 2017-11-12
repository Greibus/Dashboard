package org.tec.datos1.search;

public class BinarySearch<T extends Comparable<T>> {
	
	public T binarySearch(T buscado, T[] arreglo) {
		int min = 0;
		int max = arreglo.length - 1;
		while (min < max) {
			int mitad = (max + min)/2;
			if (arreglo[mitad].compareTo(buscado) == 0) {
				return buscado;
			} else if (arreglo[mitad].compareTo(buscado) < 0) {
				min = mitad + 1;
			} else 
				max = mitad - 1;
		}
		System.out.println("El dato buscado no se encuentra");
		return null;
	}

}
