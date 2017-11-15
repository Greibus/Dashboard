package org.tec.datos1.sort;

import org.tec.datos1.benchmark.Sorting;

public class MergeSort<T extends Comparable<T>> implements Sorting<T>{
	
	public void mergeSort(T[] array) {
		T[] tmp = array.clone();
		mergeSort(array, tmp,  0,  array.length - 1);
	}
	
	private void mergeSort(T[]array, T[]tmp, int left, int rigth) {
		if( left < rigth ) {
			int middle = (left + rigth) / 2;
			mergeSort(array, tmp, left, middle);
			mergeSort(array, tmp, middle + 1, rigth);
			merge(array, tmp, left, middle + 1, rigth);
		}
	}


    private void merge(T[]array, T[] tmp, int left, int rigth, int rigthEnd ) {
        int leftEnd = rigth - 1;
        int k = left;
        int num = rigthEnd - left + 1;
        while(left <= leftEnd && rigth <= rigthEnd)
            if(array[left].compareTo(array[rigth]) <= 0)
                tmp[k++] = array[left++];
            else
                tmp[k++] = array[rigth++];
        while(left <= leftEnd)   
            tmp[k++] = array[left++];
        while(rigth <= rigthEnd)
            tmp[k++] = array[rigth++];
        for(int i = 0; i < num; i++, rigthEnd--)
            array[rigthEnd] = tmp[rigthEnd];
    }
 

	
	public void printMergeSort(T[] array) {
        for (int i=0; i< array.length; ++i)
            System.out.print(array[i] + " ");
    }
	
    public void execute(T [] miArray) {
    	this.mergeSort(miArray);
    }

}
