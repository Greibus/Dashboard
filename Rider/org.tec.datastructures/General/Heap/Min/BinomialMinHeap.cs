﻿using System;
using System.Collections.Generic;

namespace Advanced.Algorithms.DataStructures.Heap.Min
{
    //TODO implement IEnumerable & make sure duplicates are handled correctly if its not already
    public class BinomialMinHeap<T> where T : IComparable
    {
        public int Count { get; private set; }

        internal DoublyLinkedList<BinomialHeapNode<T>> heapForest
            = new DoublyLinkedList<BinomialHeapNode<T>>();

        /// <summary>
        /// O(log(n)) complexity
        /// </summary>
        /// <param name="newItem"></param>
        public BinomialHeapNode<T> Insert(T newItem)
        {
            var newNode = new BinomialHeapNode<T>(newItem);

            var newHeapForest = new DoublyLinkedList<BinomialHeapNode<T>>();
            newHeapForest.InsertFirst(newNode);

            //updated pointer
            MergeSortedForests(newHeapForest);

            Meld();

            Count++;

            return newNode;
        }

        /// <summary>
        /// Merge roots with same degrees in Forest 
        /// </summary>
        private void Meld()
        {
            if (heapForest.Head == null)
            {
                return;
            }
                

            int i = 0;
            var cur = heapForest.Head;
            var next = heapForest.Head.Next != null ? heapForest.Head.Next : null;

            //TODO
            while (next != null)
            {
                //case 1
                //degrees are differant 
                //we are good to move ahead
                if (cur.Data.Degree != next.Data.Degree)
                {
                    i++;

                    cur = next;

                    if (cur.Next != null)
                    {
                        next = cur.Next;
                    }
                    else
                    {
                        next = null;
                    }

                    continue;
                }
                //degress of cur & next are same
                else
                {
                    //case 2 next degree equals next-next degree
                    if (next.Next != null &&
                        cur.Data.Degree == next.Next.Data.Degree)
                    {
                        i++;
                        cur = next;
                        next = cur.Next;
                        continue;
                    }

                    //case 3 cur value is less than next
                    if (cur.Data.Value.CompareTo(next.Data.Value) <= 0)
                    {
                        //add next as child of current
                        cur.Data.Children.Add(next.Data);
                        next.Data.Parent = cur.Data;
                        heapForest.Delete(next);

                        if (cur.Next != null)
                        {
                            next = cur.Next;
                        }
                        else
                        {
                            next = null;
                        }

                        continue;
                    }

                    //case 4 cur value is greater than next
                    if (cur.Data.Value.CompareTo(next.Data.Value) > 0)
                    {
                        //add current as child of next
                        next.Data.Children.Add(cur.Data);
                        cur.Data.Parent = next.Data;

                        heapForest.Delete(cur);

                        cur = next;

                        if (cur.Next != null)
                        {
                            next = cur.Next;
                        }
                        else
                        {
                            next = null;
                        }

                        continue;
                    }

                }

            }
        }

        /// <summary>
        /// O(log(n)) complexity
        /// </summary>
        /// <returns></returns>
        public T ExtractMin()
        {
            if (heapForest.Head == null)
                throw new Exception("Empty heap");

            var minTree = heapForest.Head;
            var current = heapForest.Head;

            //find minimum tree
            while (current.Next != null)
            {
                current = current.Next;

                if (minTree.Data.Value.CompareTo(current.Data.Value) > 0)
                {
                    minTree = current;
                }
            }

            //remove tree root
            heapForest.Delete(minTree);

            var newHeapForest = new DoublyLinkedList<BinomialHeapNode<T>>();
            //add removed roots children as new trees to forest
            for (int i = 0; i < minTree.Data.Children.Count; i++)
            {
                minTree.Data.Children[i].Parent = null;
                newHeapForest.InsertLast(minTree.Data.Children[i]);
            }

            MergeSortedForests(newHeapForest);

            Meld();

            Count--;

            return minTree.Data.Value;
        }

        /// <summary>
        /// Update the Heap with new value for this node pointer
        /// O(log(n)) complexity
        /// </summary>
        /// <param name="key"></param>
        public void DecrementKey(BinomialHeapNode<T> node)
        {
            var current = node;

            while (current.Parent != null
                && current.Value.CompareTo(current.Parent.Value) < 0)
            {
                var tmp = current.Value;
                current.Value = current.Parent.Value;
                current.Parent.Value = tmp;

                current = current.Parent;
            }
        }

        /// <summary>
        /// Unions this heap with another
        /// O(log(n)) complexity
        /// </summary>
        /// <param name="binomialHeap"></param>
        public void Union(BinomialMinHeap<T> binomialHeap)
        {
            MergeSortedForests(binomialHeap.heapForest);

            Meld();

            Count += binomialHeap.Count;
        }
        /// <summary>
        /// Merges the given sorted forest to current sorted Forest 
        /// & returns the last inserted node (pointer required for decrement-key)
        /// </summary>
        /// <param name="newHeapForest"></param>
        private void MergeSortedForests(DoublyLinkedList<BinomialHeapNode<T>> newHeapForest)
        {
            var @new = newHeapForest.Head;

            if (heapForest.Head == null)
            {
                heapForest = newHeapForest;
                return;
            }

            var current = heapForest.Head;

            //insert at right spot and move forward
            while (@new != null && current != null)
            {
                if (current.Data.Degree < @new.Data.Degree)
                {
                    current = current.Next;
                }
                else if (current.Data.Degree > @new.Data.Degree)
                {
                    heapForest.InsertBefore(current, new DoublyLinkedListNode<BinomialHeapNode<T>>(@new.Data));
                    @new = @new.Next;
                }
                else
                {
                    //equal
                    heapForest.InsertAfter(current, new DoublyLinkedListNode<BinomialHeapNode<T>>(@new.Data));
                    current = current.Next;
                    @new = @new.Next;
                }

            }

            //copy left overs
            while (@new != null)
            {
                heapForest.InsertAfter(heapForest.Tail, new DoublyLinkedListNode<BinomialHeapNode<T>>(@new.Data));
                @new = @new.Next;
            }

        }

        /// <summary>
        /// O(log(n)) complexity
        /// </summary>
        /// <returns></returns>
        public T PeekMin()
        {
            if (heapForest.Head == null)
                throw new Exception("Empty heap");

            var minTree = heapForest.Head;
            var current = heapForest.Head;

            //find minimum tree
            while (current.Next != null)
            {
                current = current.Next;

                if (minTree.Data.Value.CompareTo(current.Data.Value) > 0)
                {
                    minTree = current;
                }
            }

            return minTree.Data.Value;
        }
    }
}
