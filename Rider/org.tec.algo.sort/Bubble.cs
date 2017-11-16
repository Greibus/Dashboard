using System;

namespace org.tec.algo.sort {
    
    public class Bubble<T> : Sorting<T> where T : IComparable<T> {
       
        private void swap(T[] array, int i, int j) {
            T temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
	
   
        public void bubbleSort(T[] array) {
            int last = 0;
            bool pasada = true;
            while (pasada) {
                pasada = false;
                for (int i = 1; i < array.Length - last; i++) {
                    if (array[i - 1].CompareTo(array[i]) > 0) {
                        swap(array, i, i - 1);
                        pasada = true;
                    }
                } last += 1;
            }       
        }

    
 
        public void printBubbleSort(T[] array) {
            for (int i=0; i< array.Length; ++i)
                Console.WriteLine(array[i] + " ");
        }
        
        public void execute(T [] miArray) {
            this.bubbleSort(miArray);
        }
    }
}