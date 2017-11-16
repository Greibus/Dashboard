using System;

namespace org.tec.algo.sort {
    
    public class Merge<T> where T : IComparable<T> {
        
        public void MergeSort(T[] array) {
            T[] tmp = array.Clone() as T[];
            MergeSort(array, tmp,  0,  array.Length - 1);
        }
	
        private void MergeSort(T[]array, T[]tmp, int left, int rigth) {
            if( left < rigth ) {
                int middle = (left + rigth) / 2;
                MergeSort(array, tmp, left, middle);
                MergeSort(array, tmp, middle + 1, rigth);
                merge(array, tmp, left, middle + 1, rigth);
            }
        }


        private void merge(T[]array, T[] tmp, int left, int rigth, int rigthEnd ) {
            int leftEnd = rigth - 1;
            int k = left;
            int num = rigthEnd - left + 1;
            while(left <= leftEnd && rigth <= rigthEnd)
                if(array[left].CompareTo(array[rigth]) <= 0)
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
 

	
        public void PrintMergeSort(T[] array) {
            for (int i=0; i< array.Length; ++i)
                Console.WriteLine(array[i] + " ");
        }
        
    }
}