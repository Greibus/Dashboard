using System;

namespace org.tec.algo.sort {
    public interface Sorting <T> where T : IComparable<T> {

        void execute(T[] myArray); 
            
        }
       
    }
