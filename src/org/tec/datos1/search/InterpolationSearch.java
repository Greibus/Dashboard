package org.tec.datos1.search;

public class InterpolationSearch<T extends Comparable<T>> {
	
	public int interpolationSearch(T find, T[] array) {
        int min = 0;
        int max = array.length - 1;
        int middle;
        while (array[min].compareTo(find) <= 0 && array[max].compareTo(find) >= 0) {
             //middle = min + ((find - array[min]) * (max - min)) / (array[max] - array[min]);
//             if (array[middle] == find)
//            	 return middle;
//             else if (array[middle].compareTo(find) < 0)
//                 min = middle + 1;
//             else if (array[middle].compareTo(find) > 0)
//                 max = middle - 1;
       }
        return -1;
//        if (arreglo[min] == buscado)
//            return min;
//        else
//            return -1; 
    }    

}
