using System;

namespace org.tec.algo.sort {
    
    public class Selection<T> where T : IComparable<T> {
        
        private void swap(T[] array, int i, int j) {
            T temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
	
        public void selectionSort(T[] array) {
            for (int i = 0; i < array.Length - 1; i++) { 
                int min = i;
                int position = i;
                for (int j = i + 1; j < array.Length; j++ ) {
                    if (array[j].CompareTo(array[min]) < 0) {
                        min = j;
				
                    }
                }
                swap(array, position, min);
			
            }
		
        }
	

	
        public void printSelectionSort(T[] array) {
            for (int i=0; i< array.Length; ++i)
                Console.WriteLine(array[i] + " ");
        }
        
    }
}