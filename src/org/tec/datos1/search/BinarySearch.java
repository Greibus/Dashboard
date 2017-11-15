package org.tec.datos1.search;

public class BinarySearch<T extends Comparable<T>> {
	
	public int binarySearch(T find, T[] array) {
		int min = 0;
		int max = array.length - 1;
		while (min < max) {
			int mid = (max + min)/2;
			if (array[mid].compareTo(find) == 0) {
				return mid;
			} else if (array[mid].compareTo(find) < 0) {
				min = mid + 1;
			} else 
				max = mid - 1;
		}
		System.out.println("El dato find no se encuentra");
		return -1;
	}

}
