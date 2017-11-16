using System;

namespace org.tec.algo.search{

    public class Binary<T> where T : IComparable<T> {

        public int binarySearch(T find, T[] array) {
            int min = 0;
            int max = array.Length - 1;
            while (min < max) {
                int mid = (max + min)/2;
                if (array[mid].CompareTo(find) == 0) {
                    return mid;
                } else if (array[mid].CompareTo(find) < 0) {
                    min = mid + 1;
                } else 
                    max = mid - 1;
            }
            Console.WriteLine("El dato find no se encuentra");
            return -1;
        }
        
    }
}