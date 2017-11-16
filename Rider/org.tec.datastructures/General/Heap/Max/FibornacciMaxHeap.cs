using System;
using System.Collections.Generic;

namespace org.tec.datastructures.General.Heap.Max
{
    public class FibornacciMaxHeap<T> where T : IComparable
    {
        internal FibornacciHeapNode<T> heapForestHead;

        private FibornacciHeapNode<T> maxNode = null;

        public int Count { get; private set; }

        
        public FibornacciHeapNode<T> Insert(T newItem)
        {
            var newNode = new FibornacciHeapNode<T>(newItem);

            MergeForests(newNode);

            if (maxNode == null)
            {
                maxNode = newNode;
            }
            else
            {
                if (maxNode.Value.CompareTo(newNode.Value) < 0)
                {
                    maxNode = newNode;
                }
            }

            Count++;

            return newNode;
        }

        
        private void Meld()
        {

            if (heapForestHead == null)
            {
                maxNode = null;
                return;
            }

            var mergeDictionary = new Dictionary<int, FibornacciHeapNode<T>>();

            var current = heapForestHead;
            maxNode = current;
            while (current != null)
            {
                current.Parent = null;
                var next = current.Next;
                if (!mergeDictionary.ContainsKey(current.Degree))
                {
                  

                    mergeDictionary.Add(current.Degree, current);

                    if (maxNode == current)
                    {
                        maxNode = null;
                    }

                    DeleteNode(ref heapForestHead, current);

                    current = next;
                    continue;
                }
                else
                {
                    var currentDegree = current.Degree;
                    var existing = mergeDictionary[currentDegree];

                    if (existing.Value.CompareTo(current.Value) > 0)
                    {
                        current.Parent = existing;

                        DeleteNode(ref heapForestHead, current);

                        var childHead = existing.ChildrenHead;
                        InsertNode(ref childHead, current);
                        existing.ChildrenHead = childHead;

                        existing.Degree++;

                        InsertNode(ref heapForestHead, existing);
                        current = existing;
                        current.Next = next;

                    }
                    else
                    {
                        existing.Parent = current;

                        var childHead = current.ChildrenHead;
                        InsertNode(ref childHead, existing);
                        current.ChildrenHead = childHead;

                        current.Degree++;
                    }


                    if (maxNode == null
                        || maxNode.Value.CompareTo(current.Value) < 0)
                    {
                        maxNode = current;
                    }

                    mergeDictionary.Remove(currentDegree);

                }

            }

            if (mergeDictionary.Count > 0)
            {
                foreach (var node in mergeDictionary)
                {
                    InsertNode(ref heapForestHead, node.Value);

                    if (maxNode == null
                        || maxNode.Value.CompareTo(node.Value.Value) < 0)
                    {
                        maxNode = node.Value;
                    }
                }

                mergeDictionary.Clear();
            }

        }


        public T ExtractMax()
        {
            if (heapForestHead == null)
                throw new Exception("Empty heap");

            var maxValue = maxNode.Value;

            DeleteNode(ref heapForestHead, maxNode);

            MergeForests(maxNode.ChildrenHead);
            Meld();

            Count--;

            return maxValue;
        }


      
        public void IncrementKey(FibornacciHeapNode<T> node)
        {

            if (node.Parent == null
                && maxNode.Value.CompareTo(node.Value) < 0)
            {
                maxNode = node;
            }

            var current = node;

            if (current.Parent != null
                && current.Value.CompareTo(current.Parent.Value) > 0)
            {

                var parent = current.Parent;

                if (parent.LostChild)
                {
                    parent.LostChild = false;

                    var grandParent = parent.Parent;

                    if (grandParent != null)
                    {
                        Cut(parent);
                        Cut(current);
                    }
                }
                else
                {
                    Cut(current);
                }
            }

        }
        private void Cut(FibornacciHeapNode<T> node)
        {
            var parent = node.Parent;

            var childHead = node.Parent.ChildrenHead;
            DeleteNode(ref childHead, node);
            node.Parent.ChildrenHead = childHead;

            node.Parent.Degree--;
            if (parent.Parent != null)
            {
                parent.LostChild = true;
            }
            node.LostChild = false;
            node.Parent = null;

            InsertNode(ref heapForestHead, node);

            if (maxNode.Value.CompareTo(node.Value) < 0)
            {
                maxNode = node;
            }

        }

        public void Union(FibornacciMaxHeap<T> FibornacciHeap)
        {
            MergeForests(FibornacciHeap.heapForestHead);
            Count = Count + FibornacciHeap.Count;
        }

        private void MergeForests(FibornacciHeapNode<T> headPointer)
        {
            var current = headPointer;
            while (current != null)
            {
                var next = current.Next;
                InsertNode(ref heapForestHead, current);
                current = next;
            }

        }

        private void InsertNode(ref FibornacciHeapNode<T> head, FibornacciHeapNode<T> newNode)
        {
            newNode.Next = newNode.Previous = null;

            if (head == null)
            {
                head = newNode;
                return;
            }

            head.Previous = newNode;
            newNode.Next = head;

            head = newNode;
        }

        private void DeleteNode(ref FibornacciHeapNode<T> heapForestHead, FibornacciHeapNode<T> deletionNode)
        {
            if (deletionNode == heapForestHead)
            {
                if (deletionNode.Next != null)
                {
                    deletionNode.Next.Previous = null;
                }

                heapForestHead = deletionNode.Next;
                deletionNode.Next = null;
                deletionNode.Previous = null;
                return;
            }

            deletionNode.Previous.Next = deletionNode.Next;

            if (deletionNode.Next != null)
            {
                deletionNode.Next.Previous = deletionNode.Previous;
            }

            deletionNode.Next = null;
            deletionNode.Previous = null;
        }

        public T PeekMax()
        {
            if (heapForestHead == null)
                throw new Exception("Empty heap");

            return maxNode.Value;
        }
    }


}
