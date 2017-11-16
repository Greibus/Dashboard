﻿using System;
using System.Collections.Generic;

namespace Advanced.Algorithms.DataStructures.Heap.Min
{
    //TODO implement IEnumerable & make sure duplicates are handled correctly if its not already
    public class PairingMinHeap<T> where T : IComparable
    {
        internal PairingHeapNode<T> Root;

        public int Count { get; private set; }

        /// <summary>
        /// Insert a new Node
        /// </summary>
        /// <param name="newItem"></param>
        /// <returns></returns>
        public PairingHeapNode<T> Insert(T newItem)
        {
            var newNode = new PairingHeapNode<T>(newItem);
            Root = Meld(Root, newNode);
            Count++;

            return newNode;

        }

        /// <summary>
        ///  O(n), Amortized O(log(n))
        ///  Melds all the nodes to one single Root Node
        /// </summary>
        /// <param name="headNode"></param>
        private void Meld(PairingHeapNode<T> headNode)
        {
            if (headNode == null)
                return;

            var passOneResult = new List<PairingHeapNode<T>>();

            var current = headNode;

            if (current.Next == null)
            {
                headNode.Next = null;
                headNode.Previous = null;
                passOneResult.Add(headNode);
            }
            else
            {
                while (true)
                {
                    if (current == null)
                    {
                        break;
                    }

                    if (current.Next != null)
                    {
                        var next = current.Next;
                        var nextNext = next.Next;
                        passOneResult.Add(Meld(current, next));
                        current = nextNext;
                    }
                    else
                    {
                        var lastInserted = passOneResult[passOneResult.Count - 1];
                        passOneResult[passOneResult.Count - 1] = Meld(lastInserted, current);
                        break;

                    }
                }

            }

            var passTwoResult = passOneResult[passOneResult.Count - 1];

            if (passOneResult.Count == 1)
            {
                Root = passTwoResult;
                return;
            }


            for (int i = passOneResult.Count - 2; i >= 0; i--)
            {
                current = passOneResult[i];
                passTwoResult = Meld(passTwoResult, current);
            }

            Root = passTwoResult;
        }

        /// <summary>
        /// makes the smaller node parent of other and returns the Parent
        /// </summary>
        /// <param name="node1"></param>
        /// <param name="node2"></param>
        private PairingHeapNode<T> Meld(PairingHeapNode<T> node1,
            PairingHeapNode<T> node2)
        {


            if (node2 != null)
            {
                node2.Previous = null;
                node2.Next = null;
            }

            if (node1 == null)
            {
                return node2;
            }

            node1.Previous = null;
            node1.Next = null;

            if (node1.Value.CompareTo(node2.Value) <= 0)
            {

                AddChild(ref node1, node2);
                return node1;
            }
            else
            {

                AddChild(ref node2, node1);
                return node2;
            }
        }

        /// <summary>
        /// Returns the min
        /// </summary>
        /// <returns></returns>
        public T ExtractMin()
        {
            var min = Root;
            Meld(Root.ChildrenHead);
            Count--;
            return min.Value;
        }

        /// <summary>
        /// Update heap after a node value was decremented
        /// </summary>
        /// <param name="node"></param>
        public void DecrementKey(PairingHeapNode<T> node)
        {
            if (node == Root)
                return;

            DeleteChild(node);

            Root = Meld(Root, node);
        }

        /// <summary>
        /// Merge another heap with this heap
        /// </summary>
        /// <param name="PairingHeap"></param>
        public void Merge(PairingMinHeap<T> PairingHeap)
        {
            Root = Meld(Root, PairingHeap.Root);
            Count = Count + PairingHeap.Count;
        }

        /// <summary>
        /// O(1) time complexity
        /// </summary>
        /// <returns></returns>
        public T PeekMin()
        {
            if (Root == null)
                throw new Exception("Empty heap");

            return Root.Value;
        }

        /// <summary>
        /// Add new child to parent node
        /// </summary>
        /// <param name="parent"></param>
        /// <param name="child"></param>
        private void AddChild(ref PairingHeapNode<T> parent, PairingHeapNode<T> child)
        {
            if (parent.ChildrenHead == null)
            {
                parent.ChildrenHead = child;
                child.Previous = parent;
                return;
            }

            var head = parent.ChildrenHead;

            child.Previous = head;
            child.Next = head.Next;

            if (head.Next != null)
            {
                head.Next.Previous = child;
            }

            head.Next = child;

        }

        /// <summary>
        /// delete node from parent
        /// </summary>
        /// <param name="node"></param>
        private void DeleteChild(PairingHeapNode<T> node)
        {
            //if this node is the child head pointer of parent
            if (node.IsHeadChild)
            {
                var parent = node.Previous;

                //use close sibling as new parent child pointer
                if (node.Next != null)
                {
                    node.Next.Previous = parent;
                }

                parent.ChildrenHead = node.Next;
            }
            else
            {
                //just do regular deletion from linked list
                node.Previous.Next = node.Next;

                if (node.Next != null)
                {
                    node.Next.Previous = node.Previous;
                }
            }
        }


    }


}
