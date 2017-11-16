using System;

namespace org.tec.algo.sort {
    
    public class Insert<T> where T : IComparable<T>{
        
        private void swap(T[] array, int i, int j) {
            T temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
	
        public void insertionSort(T[] array) {
            int start = 0;
            for (int i = 1; i < array.Length; i++) {
                int j = i;
                int temp = i;
                while (j != start) {
                    if (array[j - 1].CompareTo(array[temp]) > 0) {
                        swap(array, j - 1, temp);
                        j --;
                        temp --;
                    } else {
                        break;
                    }
        		
                }
            }
        }
	
        public void printInsertionSort(T[] array) {
            for (int i=0; i< array.Length; ++i)
                Console.WriteLine(array[i] + " ");
        }
        
        
    }
}