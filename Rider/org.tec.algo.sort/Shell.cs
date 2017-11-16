using System;

namespace org.tec.algo.sort
{
    public class Shell<T> where T : IComparable<T>{
        
        private void swap(T[] array, int i, int j) {
            T temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
	
        public void shellSort(T[] array) {
            int gap = (array.Length - 1) / 2;
            while(gap > 0) {
                for(int index = 0 ;index+gap < array.Length ; index++) {
                    int indexK= 0;
                    while(indexK <= index && index - indexK + gap > 0 && array[index-indexK].CompareTo(array[index-indexK+gap]) > 0){
                        swap (array, index - indexK, index - indexK + gap);
                        indexK += gap;
                    }
                }
                gap = gap / 2;
            }
        }
	
        public void printShellSort(T[] array) {
            for (int i=0; i< array.Length; ++i)
                Console.WriteLine(array[i] + " ");
        } 
        
    }
}