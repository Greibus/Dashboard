using System;

namespace org.tec.algo.search {
    
    
    public class Polation <T> where T : IComparable<T> {
        
        public int interpolationSearch(int find, int[] arra) {
            int min = 0;
            int max = arra.Length - 1;
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
}