using System;


namespace org.tec.algo.sort
{
    public class Quicksort<T> : Sorting<T> where T : IComparable<T>{
        
        private void swap(T[] array, int i, int j) {
            T temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
	
        public void quickSort(T[] array) {
            quickSort(array, 0, array.Length -1);
        }
	
        private void quickSort(T[] array, int i, int j) {
            int left = i;
            int rigth = j;
            if ( i < j) {
                int pivote = (i + j)/ 2;
                while (i <= j) {
                    while (array[i].CompareTo(array[pivote]) < 0) {
                        i++;
                    }
                    while (array[j].CompareTo(array[pivote]) > 0) {
                        j--;
                    }
                    if (i <= j) {                                          
                        swap(array, i, j);
                        i++;
                        j--;
                    }
                }
                if (left < j)
                    quickSort(array, left, j);
                if (i < rigth)
                    quickSort(array, i, rigth);
            }
			
        }
	
        public void printQuickSort(T[] array) {
            for (int i=0; i< array.Length; ++i)
                Console.WriteLine(array[i] + " ");
        }
        
        public void execute(T [] miArray) {
            this.quickSort(miArray);
        }
        
    }
}