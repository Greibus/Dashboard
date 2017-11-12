package org.tec.datos1.sort;



public class MergeSort<T extends Comparable<T>> {
	
	public void mergeSort(T[] arreglo) {
		T[] tmp = arreglo.clone();
		mergeSort(arreglo, tmp,  0,  arreglo.length - 1);
	}
	
	private void mergeSort(T[]arreglo, T[]tmp, int izq, int der) {
		if( izq < der ) {
			int mitad = (izq + der) / 2;
			mergeSort(arreglo, tmp, izq, mitad);
			mergeSort(arreglo, tmp, mitad + 1, der);
			merge(arreglo, tmp, izq, mitad + 1, der);
		}
	}


    private void merge(T[]arreglo, T[] tmp, int izq, int der, int derEnd ) {
        int izqEnd = der - 1;
        int k = izq;
        int num = derEnd - izq + 1;

        while(izq <= izqEnd && der <= derEnd)
            if(arreglo[izq].compareTo(arreglo[der]) <= 0)
                tmp[k++] = arreglo[izq++];
            else
                tmp[k++] = arreglo[der++];

        while(izq <= izqEnd)   
            tmp[k++] = arreglo[izq++];

        while(der <= derEnd)
            tmp[k++] = arreglo[der++];

        for(int i = 0; i < num; i++, derEnd--)
            arreglo[derEnd] = tmp[derEnd];
    }
 

	
	public void imprimir(T[] array) {
        for (int i=0; i< array.length; ++i)
            System.out.print(array[i] + " ");
    }

}
