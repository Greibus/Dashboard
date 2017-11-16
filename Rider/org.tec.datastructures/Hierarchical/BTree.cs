
﻿using System;

namespace org.tec.datastructures.Hierarchical{
    
        internal abstract class BNode<T> where T : IComparable
    {
        
        internal int Index;

        internal T[] Keys { get; set; }
        internal int KeyCount;

        internal abstract BNode<T> GetParent();
        internal abstract BNode<T>[] GetChildren();

        internal BNode(int maxKeysPerNode)
        {
            Keys = new T[maxKeysPerNode];
        }

        internal int GetMedianIndex()
        {
            return (KeyCount / 2) + 1;
        }
    }

    internal class BTreeNode<T> : BNode<T> where T : IComparable
    {

        internal BTreeNode<T> Parent { get; set; }
        internal BTreeNode<T>[] Children { get; set; }

        internal bool IsLeaf => Children[0] == null;

        internal BTreeNode(int maxKeysPerNode, BTreeNode<T> parent)
            :base(maxKeysPerNode)
        {

            Parent = parent;
            Children = new BTreeNode<T>[maxKeysPerNode + 1];

        }

        internal override BNode<T> GetParent()
        {
            return Parent;
        }

        internal override BNode<T>[]  GetChildren()
        {
            return Children;
        }
    }

    public class BTree<T> where T : IComparable
    {
        public int Count { get; private set; }

        internal BTreeNode<T> Root;

        private int maxKeysPerNode;
        private int minKeysPerNode => maxKeysPerNode / 2;

        public BTree(int maxKeysPerNode)
        {
            if (maxKeysPerNode < 3)
            {
                throw new Exception("Max keys per node should be atleast 3.");
            }

            this.maxKeysPerNode = maxKeysPerNode;
        }

        public T Max
        {
            get
            {
                if (Root == null) return default(T);

                var maxNode = findMaxNode(Root);
                return maxNode.Keys[maxNode.KeyCount - 1];
            }
        }

        public T Min
        {
            get
            {
                if (Root == null) return default(T);

                var minNode = findMinNode(Root);
                return minNode.Keys[0];
            }
        }


        public bool HasItem(T value)
        {
            return find(Root, value) != null;
        }

        private BTreeNode<T> find(BTreeNode<T> node, T value)
        {

            if (node.IsLeaf)
            {
                for (int i = 0; i < node.KeyCount; i++)
                {

                    if (value.CompareTo(node.Keys[i]) == 0)
                    {
                        return node;
                    }
                }
            }
            else
            {
                for (int i = 0; i < node.KeyCount; i++)
                {
                    if (value.CompareTo(node.Keys[i]) == 0)
                    {
                        return node;
                    }

                    if (value.CompareTo(node.Keys[i]) < 0)
                    {
                        return find(node.Children[i], value);
                    }
                    else if (node.KeyCount == i + 1)
                    {
                        return find(node.Children[i + 1], value);
                    }
                }

            }

            return null;
        }
        public void Insert(T newValue)
        {
            if (Root == null)
            {
                Root = new BTreeNode<T>(maxKeysPerNode, null);
                Root.Keys[0] = newValue;
                Root.KeyCount++;
                Count++;
                return;
            }

            var leafToInsert = findInsertionLeaf(Root, newValue);

            insertAndSplit(ref leafToInsert, newValue, null, null);
            Count++;
        }


        private BTreeNode<T> findInsertionLeaf(BTreeNode<T> node, T newValue)
        {
            if (node.IsLeaf)
            {
                return node;
            }

            for (int i = 0; i < node.KeyCount; i++)
            {
                if (newValue.CompareTo(node.Keys[i]) < 0)
                {
                    return findInsertionLeaf(node.Children[i], newValue);
                }
                else if (node.KeyCount == i + 1)
                {
                    return findInsertionLeaf(node.Children[i + 1], newValue);
                }

            }

            return node;
        }

        private void insertAndSplit(ref BTreeNode<T> node, T newValue,
            BTreeNode<T> newValueLeft, BTreeNode<T> newValueRight)
        {
            if (node == null)
            {
                node = new BTreeNode<T>(maxKeysPerNode, null);
                Root = node;
            }

            if (node.KeyCount == maxKeysPerNode)
            {
                //divide the current node values + new Node as left & right sub nodes
                var left = new BTreeNode<T>(maxKeysPerNode, null);
                var right = new BTreeNode<T>(maxKeysPerNode, null);

                //median of current Node
                var currentMedianIndex = node.GetMedianIndex();

                //init currentNode under consideration to left
                var currentNode = left;
                var currentNodeIndex = 0;

                //new Median also takes new Value in to Account
                var newMedian = default(T);
                var newMedianSet = false;
                var newValueInserted = false;

                //keep track of each insertion
                int insertionCount = 0;

                //insert newValue and existing values in sorted order
                //to left & right nodes
                //set new median during sorting
                for (int i = 0; i < node.KeyCount; i++)
                {

                    //if insertion count reached new median
                    //set the new median by picking the next smallest value
                    if (!newMedianSet && insertionCount == currentMedianIndex)
                    {
                        newMedianSet = true;

                        //median can be the new value or node.keys[i] (next node key)
                        //whichever is smaller
                        if (!newValueInserted && newValue.CompareTo(node.Keys[i]) < 0)
                        {
                            //median is new value
                            newMedian = newValue;
                            newValueInserted = true;

                            if (newValueLeft != null)
                            {
                                setChild(currentNode, currentNode.KeyCount, newValueLeft);
                            }

                            //now fill right node
                            currentNode = right;
                            currentNodeIndex = 0;

                            if (newValueRight != null)
                            {
                                setChild(currentNode, 0, newValueRight);
                            }

                            i--;
                            insertionCount++;
                            continue;
                        }
                        else
                        {
                            //median is next node
                            newMedian = node.Keys[i];

                            //now fill right node
                            currentNode = right;
                            currentNodeIndex = 0;

                            continue;
                        }

                    }

                    //pick the smaller among newValue & node.Keys[i]
                    //and insert in to currentNode (left & right nodes)
                    //if new Value was already inserted then just copy from node.Keys in sequence
                    //since node.Keys is already in sorted order it should be fine
                    if (newValueInserted || node.Keys[i].CompareTo(newValue) < 0)
                    {
                        currentNode.Keys[currentNodeIndex] = node.Keys[i];
                        currentNode.KeyCount++;

                        //if child is set don't set again
                        //the child was already set by last newValueRight or last node
                        if (currentNode.Children[currentNodeIndex] == null)
                        {
                            setChild(currentNode, currentNodeIndex, node.Children[i]);
                        }

                        setChild(currentNode, currentNodeIndex + 1, node.Children[i + 1]);

                    }
                    else
                    {
                        currentNode.Keys[currentNodeIndex] = newValue;
                        currentNode.KeyCount++;

                        setChild(currentNode, currentNodeIndex, newValueLeft);
                        setChild(currentNode, currentNodeIndex + 1, newValueRight);

                        i--;
                        newValueInserted = true;
                    }

                    currentNodeIndex++;
                    insertionCount++;
                }

                //could be that thew newKey is the greatest 
                //so insert at end
                if (!newValueInserted)
                {
                    currentNode.Keys[currentNodeIndex] = newValue;
                    currentNode.KeyCount++;

                    setChild(currentNode, currentNodeIndex, newValueLeft);
                    setChild(currentNode, currentNodeIndex + 1, newValueRight);

                }

                //insert overflow element (newMedian) to parent
                var parent = node.Parent;
                insertAndSplit(ref parent, newMedian, left, right);

            }
            //newValue have room to fit in this node
            //so just insert in right spot in asc order of keys
            else
            {
                insertNonFullNode(ref node, newValue, newValueLeft, newValueRight);
            }

        }

        private void insertNonFullNode(ref BTreeNode<T> node, T newValue,
            BTreeNode<T> newValueLeft, BTreeNode<T> newValueRight)
        {
            var inserted = false;

            //insert in sorted order
            for (int i = 0; i < node.KeyCount; i++)
            {
                if (newValue.CompareTo(node.Keys[i]) < 0)
                {
                    insertAt(node.Keys, i, newValue);
                    node.KeyCount++;

                    //Insert children if any
                    setChild(node, i, newValueLeft);
                    insertChild(node, i + 1, newValueRight);


                    inserted = true;
                    break;
                }
            }

            if (!inserted)
            {
                node.Keys[node.KeyCount] = newValue;
                node.KeyCount++;

                setChild(node, node.KeyCount - 1, newValueLeft);
                setChild(node, node.KeyCount, newValueRight);


            }
        }



        public void Delete(T value)
        {
            var node = findDeletionNode(Root, value);

            if (node == null)
            {
                throw new Exception("Item do not exist in this tree.");
            }

            for (int i = 0; i < node.KeyCount; i++)
            {
                if (value.CompareTo(node.Keys[i]) == 0)
                {
                    //if node is leaf and no underflow
                    //then just remove the node
                    if (node.IsLeaf)
                    {
                        removeAt(node.Keys, i);
                        node.KeyCount--;

                        balance(node);

                    }
                    else
                    {
                        //replace with max node of left tree
                        var maxNode = findMaxNode(node.Children[i]);
                        node.Keys[i] = maxNode.Keys[maxNode.KeyCount - 1];

                        removeAt(maxNode.Keys, maxNode.KeyCount - 1);
                        maxNode.KeyCount--;

                        balance(maxNode);

                    }

                    Count--;
                    return;
                }

            }


        }

        /// <summary>
        /// return the node containing max value which will be a leaf at the right most
        /// </summary>
        /// <param name="asBTreeNode"></param>
        /// <returns></returns>
        private BTreeNode<T> findMinNode(BTreeNode<T> node)
        {
            //if leaf return node
            if (node.IsLeaf)
            {
                return node;
            }

            //step in to left most child
            return findMinNode(node.Children[0]);

        }

        private BTreeNode<T> findMaxNode(BTreeNode<T> node)
        {
            //if leaf return node
            if (node.IsLeaf)
            {
                return node;
            }

            //step in to right most child
            return findMaxNode(node.Children[node.KeyCount]);

        }

        private void balance(BTreeNode<T> node)
        {
            if (node == Root || node.KeyCount >= minKeysPerNode)
            {
                return;
            }

            var rightSibling = getRightSibling(node);

            if (rightSibling != null
                && rightSibling.KeyCount > minKeysPerNode)
            {
                leftRotate(node, rightSibling);
                return;
            }

            var leftSibling = getLeftSibling(node);

            if (leftSibling != null
                && leftSibling.KeyCount > minKeysPerNode)
            {
                rightRotate(leftSibling, node);
                return;
            }

            if (rightSibling != null)
            {
                sandwich(node, rightSibling);
            }
            else
            {
                sandwich(leftSibling, node);
            }


        }

        /// <summary>
        ///  merge two adjacent siblings to one node
        /// </summary>
        /// <param name="leftSibling"></param>
        /// <param name="rightSibling"></param>
        private void sandwich(BTreeNode<T> leftSibling, BTreeNode<T> rightSibling)
        {
            var separatorIndex = getNextSeparatorIndex(leftSibling);
            var parent = leftSibling.Parent;

            var newNode = new BTreeNode<T>(maxKeysPerNode, leftSibling.Parent);
            var newIndex = 0;

            for (int i = 0; i < leftSibling.KeyCount; i++)
            {
                newNode.Keys[newIndex] = leftSibling.Keys[i];

                if (leftSibling.Children[i] != null)
                {
                    setChild(newNode, newIndex, leftSibling.Children[i]);
                }

                if (leftSibling.Children[i + 1] != null)
                {
                    setChild(newNode, newIndex + 1, leftSibling.Children[i + 1]);
                }

                newIndex++;
            }

            //special case when left sibling is empty 
            if (leftSibling.KeyCount == 0 && leftSibling.Children[0] != null)
            {
                setChild(newNode, newIndex, leftSibling.Children[0]);
            }

            newNode.Keys[newIndex] = parent.Keys[separatorIndex];
            newIndex++;

            for (int i = 0; i < rightSibling.KeyCount; i++)
            {
                newNode.Keys[newIndex] = rightSibling.Keys[i];

                if (rightSibling.Children[i] != null)
                {
                    setChild(newNode, newIndex, rightSibling.Children[i]);
                }

                if (rightSibling.Children[i + 1] != null)
                {
                    setChild(newNode, newIndex + 1, rightSibling.Children[i + 1]);
                }

                newIndex++;
            }

            //special case when left sibling is empty 
            if (rightSibling.KeyCount == 0 && rightSibling.Children[0] != null)
            {
                setChild(newNode, newIndex, rightSibling.Children[0]);
            }

            newNode.KeyCount = newIndex;
            setChild(parent, separatorIndex, newNode);
            removeAt(parent.Keys, separatorIndex);
            parent.KeyCount--;

            removeChild(parent, separatorIndex + 1);


            if (parent.KeyCount == 0
                && parent == Root)
            {
                Root = newNode;
                Root.Parent = null;

                if (Root.KeyCount == 0)
                {
                    Root = null;
                }

                return;
            }

            if (parent.KeyCount < minKeysPerNode)
            {
                balance(parent);
            }
        }


        private void rightRotate(BTreeNode<T> leftSibling, BTreeNode<T> rightSibling)
        {
            var parentIndex = getNextSeparatorIndex(leftSibling);

            insertAt(rightSibling.Keys, 0, rightSibling.Parent.Keys[parentIndex]);
            rightSibling.KeyCount++;

            insertChild(rightSibling, 0, leftSibling.Children[leftSibling.KeyCount]);

            rightSibling.Parent.Keys[parentIndex] = leftSibling.Keys[leftSibling.KeyCount - 1];

            removeAt(leftSibling.Keys, leftSibling.KeyCount - 1);
            leftSibling.KeyCount--;

            removeChild(leftSibling, leftSibling.KeyCount + 1);


        }

        private void leftRotate(BTreeNode<T> leftSibling, BTreeNode<T> rightSibling)
        {
            var parentIndex = getNextSeparatorIndex(leftSibling);
            leftSibling.Keys[leftSibling.KeyCount] = leftSibling.Parent.Keys[parentIndex];
            leftSibling.KeyCount++;

            setChild(leftSibling, leftSibling.KeyCount, rightSibling.Children[0]);


            leftSibling.Parent.Keys[parentIndex] = rightSibling.Keys[0];

            removeAt(rightSibling.Keys, 0);
            rightSibling.KeyCount--;

            removeChild(rightSibling, 0);

        }

        private BTreeNode<T> findDeletionNode(BTreeNode<T> node, T value)
        {
            //if leaf then its time to insert
            if (node.IsLeaf)
            {
                for (int i = 0; i < node.KeyCount; i++)
                {
                    if (value.CompareTo(node.Keys[i]) == 0)
                    {
                        return node;
                    }
                }
            }
            else
            {
                //if not leaf then drill down to leaf
                for (int i = 0; i < node.KeyCount; i++)
                {
                    if (value.CompareTo(node.Keys[i]) == 0)
                    {
                        return node;
                    }
                    
                    if (value.CompareTo(node.Keys[i]) < 0)
                    {
                        return findDeletionNode(node.Children[i], value);
                    }
                    
                    else if (node.KeyCount == i + 1)
                    {
                        return findDeletionNode(node.Children[i + 1], value);
                    }

                }
            }

            return null;
        }

        private int getNextSeparatorIndex(BTreeNode<T> node)
        {
            var parent = node.Parent;

            if (node.Index == 0)
            {
                return 0;
            }
            else if (node.Index == parent.KeyCount)
            {
                return node.Index - 1;
            }
            else
            {
                return node.Index;
            }

        }

        private BTreeNode<T> getRightSibling(BTreeNode<T> node)
        {
            var parent = node.Parent;

            if (node.Index == parent.KeyCount)
            {
                return null;
            }

            return parent.Children[node.Index + 1];

        }

        private BTreeNode<T> getLeftSibling(BTreeNode<T> node)
        {
            if (node.Index == 0)
            {
                return null;
            }

            return node.Parent.Children[node.Index - 1];

        }

        private void setChild(BTreeNode<T> parent, int childIndex, BTreeNode<T> child)
        {
            parent.Children[childIndex] = child;

            if (child != null)
            {
                child.Parent = parent;
                child.Index = childIndex;
            }

        }

        private void insertChild(BTreeNode<T> parent, int childIndex, BTreeNode<T> child)
        {
            insertAt(parent.Children, childIndex, child);

            if (child != null)
            {
                child.Parent = parent;
            }

            for (int i = childIndex; i <= parent.KeyCount; i++)
            {
                if (parent.Children[i] != null)
                {
                    parent.Children[i].Index = i;
                }
            }
        }

        private void removeChild(BTreeNode<T> parent, int childIndex)
        {
            removeAt(parent.Children, childIndex);

            for (int i = childIndex; i <= parent.KeyCount; i++)
            {
                if (parent.Children[i] != null)
                {
                    parent.Children[i].Index = i;
                }

            }
        }

        private void insertAt<S>(S[] array, int index, S newValue)
        {
            Array.Copy(array, index, array, index + 1, array.Length - index - 1);
            array[index] = newValue;
        }

        private void removeAt<S>(S[] array, int index)
        {
            Array.Copy(array, index + 1, array, index, array.Length - index - 1);
        }

    }
}
        
 