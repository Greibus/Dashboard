﻿using System;
using System.Collections;
using System.Collections.Generic;

namespace Advanced.Algorithms.DataStructures
{
    /// <summary>
    /// A self expanding array (dynamic array) aka array vector
    /// </summary>
    /// <typeparam name="T"></typeparam>
    public class ArrayList<T> : IEnumerable<T>
    {

        private int initialArraySize;
        private int arraySize;

        private T[] array;

        private int currentEndPosition;
        public int Length => currentEndPosition;

        //constructor init 
        public ArrayList(int initalArraySize = 2, IEnumerable<T> initial = null)
        {
            if (initalArraySize < 2)
            {
                throw new Exception("Initial array size must be greater than 1");
            }

            initialArraySize = initalArraySize;
            arraySize = initalArraySize;
            array = new T[arraySize];

            if(initial!=null)
            {
                foreach(var item in initial)
                {
                    Add(item);
                }
            }
        }

        /// <summary>
        /// Overloaded constructor
        /// </summary>
        /// <param name="initial"></param>
        public ArrayList(IEnumerable<T> initial) 
            : this (2, initial){ }

        /// <summary>
        /// Expose indexer
        /// </summary>
        /// <param name="index"></param>
        /// <returns></returns>
        public T this[int index]
        {
            get { return ItemAt(index); }
            set { SetItem(index, value); }
        }

        //O(1)
        private T ItemAt(int i)
        {
            if (i >= Length)
                throw new System.Exception("Index exeeds array size");

            return array[i];
        }

        //O(1) amortized 
        public void Add(T item)
        {
            Grow();

            array[currentEndPosition] = item;
            currentEndPosition++;
        }

        /// <summary>
        /// Insert element at specified index
        /// </summary>
        /// <param name="index"></param>
        /// <param name="item"></param>
        public void InsertAt(int index, T item)
        {
            Grow();

            Shift(index);

            array[index] = item;
            currentEndPosition++;
        }

        /// <summary>
        /// shift the position of elements right by one starting at this index
        /// create a blank field at index
        /// </summary>
        /// <param name="index"></param>
        private void Shift(int index)
        {
            Array.Copy(array, index, array, index + 1, Length - index);
        }

        /// <summary>
        /// empty the list
        /// </summary>
        internal void Clear()
        {
            arraySize = initialArraySize;
            array = new T[arraySize];
            currentEndPosition = 0;
        }

        //O(1)
        private void SetItem(int i, T item)
        {
            if (i >= Length)
                throw new System.Exception("Index exeeds array size");

            array[i] = item;
        }

        //O(n) 
        public void RemoveItem(int i)
        {
            if (i >= Length)
                throw new System.Exception("Index exeeds array size");

            //shift elements
            for (int j = i; j < arraySize - 1; j++)
            {
                array[j] = array[j + 1];
            }

            currentEndPosition--;

            Shrink();
        }


        /// <summary>
        /// Grow array if needed
        /// </summary>
        private void Grow()
        {
            if (currentEndPosition == arraySize)
            {
                //increase array size exponentially on demand
                arraySize *= 2;

                var biggerArray = new T[arraySize];
                Array.Copy(array, 0, biggerArray, 0, currentEndPosition);
                array = biggerArray;
            }
        }

        /// <summary>
        /// Shrink array if needed
        /// </summary>
        private void Shrink()
        {
            if (currentEndPosition == arraySize / 2 && arraySize != initialArraySize)
            {
                //reduce array by half 
                arraySize /= 2;

                var smallerArray = new T[arraySize];
                Array.Copy(array, 0, smallerArray, 0, currentEndPosition);
                array = smallerArray;
            }
        }


        /// <summary>
        /// Returns as an array
        /// </summary>
        /// <returns></returns>
        public T[] ToArray()
        {
            var result = new T[Length];

            var i = 0;
            foreach(var item in this)
            {
                result[i] = item;
                i++;
            }

            return result;
        }

        /// <summary>
        /// Add's the given array to the end 
        /// </summary>
        /// <param name="array"></param>
        public void AddRange(T[] array)
        {
            foreach(var item in array)
            {
                Add(item);
            }
        }

        //Implementation for the GetEnumerator method.
        IEnumerator IEnumerable.GetEnumerator()
        {
            return GetEnumerator();
        }

        public IEnumerator<T> GetEnumerator()
        {
            return new ArrayListEnumerator<T>(array, Length);
        }
    }

    //  implement IEnumerator.
    public class ArrayListEnumerator<T> : IEnumerator<T>
    {
        private T[] array;

        // Enumerators are positioned before the first element
        // until the first MoveNext() call.
        int position = -1;
        int length;

        public ArrayListEnumerator(T[] list, int length)
        {
            this.length = length;
            array = list;
        }

        public bool MoveNext()
        {
            position++;
            return (position < length);
        }

        public void Reset()
        {
            position = -1;
        }

        object IEnumerator.Current
        {
            get
            {
                return Current;
            }
        }

        public T Current
        {
            get
            {

                try
                {
                    return array[position];
                }
                catch (IndexOutOfRangeException)
                {
                    throw new InvalidOperationException();
                }
            }
        }

        public void Dispose()
        {
            array = null;
            length = 0;
            position = -1;
        }
    }

}
