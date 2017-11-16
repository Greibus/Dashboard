package org.tec.datos1.search;

public class InterpolationSearch<T extends Comparable<T>> {
	
	public int interpolationSearch(int find, Integer[] arra) {
        int min = 0;
        int max = arra.length - 1;
        int middle;
        while (arra[min] <= find && arra[max] >= find) {
             middle = min + ((find - arra[min]) * (max - min)) / (arra[max] - arra[min]);
             if (arra[middle] == find)
            	 return middle;
             else if (arra[middle] < find)
                 min = middle + 1;
             else if (arra[middle] > find)
                 max = middle - 1;
       }
        return -1;
        
	}
}
