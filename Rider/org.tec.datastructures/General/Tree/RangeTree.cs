﻿using System;
using System.Collections.Generic;

namespace Advanced.Algorithms.DataStructures
{
    /// <summary>
    /// range tree node
    /// </summary>
    /// <typeparam name="T"></typeparam>
    internal class RangeTreeNode<T> : IComparable where T : IComparable
    {
        internal T Data { get; set; }

        internal RangeTree<T> tree { get; set; }

        public int CompareTo(object obj)
        {
            return Data.CompareTo(((RangeTreeNode<T>)obj).Data);
        }

        public RangeTreeNode(T value)
        {
            Data = value;
            tree = new RangeTree<T>();
        }
    }

    //TODO implement IEnumerable & make sure duplicates are handled correctly if its not already
    //TODO support initial  bulk loading if possible
    /// <summary>
    /// range tree
    /// </summary>
    /// <typeparam name="T"></typeparam>
    public class DRangeTree<T> where T : IComparable
    {
        private readonly int dimensions;
        public int Count { get; private set; }

        private RangeTree<T> tree = new RangeTree<T>();

        public DRangeTree(int dimensions)
        {
            if (dimensions <= 0)
            {
                throw new Exception("Dimension should be greater than 0.");
            }

            this.dimensions = dimensions;
        }

        public void Insert(T[] value)
        {
            validateDimensions(value);

            var currentTree = tree;
            //get all overlaps
            //and insert next dimension value to each overlapping node
            for (int i = 0; i < dimensions; i++)
            {
                currentTree = currentTree.Insert(value[i]).tree;
            }

            Count++;
        }

        public void Delete(T[] value)
        {
            validateDimensions(value);
            var found = false;
            DeleteRecursive(tree, value, 0, ref found);

            if (found == false)
            {
                throw new Exception("Item not found.");
            }

            Count--;
        }

        /// <summary>
        /// recursively move until last dimension and then delete if found
        /// </summary>
        /// <param name="tree"></param>
        /// <param name="value"></param>
        /// <param name="currentDimension"></param>
        /// <param name="found"></param>
        private void DeleteRecursive(RangeTree<T> tree, T[] value, int currentDimension, ref bool found)
        {
            var node = tree.Find(value[currentDimension]);

            if (node != null)
            {
                if (currentDimension + 1 == dimensions)
                {
                    found = true;
                }
                else
                {
                    DeleteRecursive(node.tree, value, currentDimension + 1, ref found);
                }
            }

            //delete node if next dimension has no elements
            //or when this is the last dimension and we found element
            if (found && ((currentDimension + 1 == dimensions)
                || (node.tree.Count == 0 && currentDimension + 1 < dimensions)))
            {
                tree.Delete(value[currentDimension]);
            }

        }

        /// <summary>
        /// Get all points within given range
        /// </summary>
        /// <param name="start"></param>
        /// <param name="end"></param>
        /// <returns></returns>
        public List<T[]> GetInRange(T[] start, T[] end)
        {
            validateDimensions(start);
            validateDimensions(end);

            return GetInRange(tree, start, end, 0);

        }

        /// <summary>
        /// recursively visit node and return points within given range
        /// </summary>
        /// <param name="currentTree"></param>
        /// <param name="start"></param>
        /// <param name="end"></param>
        /// <param name="dimension"></param>
        /// <returns></returns>
        private List<T[]> GetInRange(
            RangeTree<T> currentTree,
            T[] start, T[] end, int dimension)
        {
            var nodes = currentTree.GetInRange(start[dimension], end[dimension]);

            if (dimension + 1 == dimensions)
            {
                var result = new List<T[]>();

                foreach (var node in nodes)
                {
                    var thisDimResult = new T[dimensions];
                    thisDimResult[dimension] = node.Data;
                    result.Add(thisDimResult);
                }

                return result;
            }
            else
            {
                var result = new List<T[]>();

                foreach (var node in nodes)
                {
                    var nextDimResult = GetInRange(node.tree, start, end, dimension + 1);

                    foreach (var nextResult in nextDimResult)
                    {
                        nextResult[dimension] = node.Data;
                        result.Add(nextResult);
                    }
                }

                return result;
            }

        }

        /// <summary>
        /// validate dimensions for point length
        /// </summary>
        /// <param name="start"></param>
        /// <param name="end"></param>
        private void validateDimensions(T[] start)
        {
            if (start.Length != dimensions)
            {
                throw new Exception(string.Format("Expecting {0} points.",
                    dimensions));
            }

        }
    }

    /// <summary>
    /// One dimensional range tree
    /// TODO support multiple dimensions 
    /// by nesting node with r-b tree for next dimension
    /// </summary>
    /// <typeparam name="T"></typeparam>
    internal class RangeTree<T> where T : IComparable
    {

        internal RedBlackTree<RangeTreeNode<T>> tree
            = new RedBlackTree<RangeTreeNode<T>>();

        internal int Count => tree.Count;

        public RangeTreeNode<T> Find(T value)
        {
            return tree.Find(new RangeTreeNode<T>(value)).Value;
        }

        internal RangeTreeNode<T> Insert(T value)
        {
            var newNode = new RangeTreeNode<T>(value);
            var result = tree.InsertAndReturnNewNode(newNode);
            return result.Value;
        }

        internal void Delete(T value)
        {
            tree.Delete(new RangeTreeNode<T>(value));
        }

        internal List<RangeTreeNode<T>> GetInRange(T start, T end)
        {
            return GetInRange(new List<RangeTreeNode<T>>(),
                new Dictionary<RedBlackTreeNode<RangeTreeNode<T>>, bool>(),
                tree.Root, start, end);
        }

        private List<RangeTreeNode<T>> GetInRange(List<RangeTreeNode<T>> result,
            Dictionary<RedBlackTreeNode<RangeTreeNode<T>>, bool> visited,
            RedBlackTreeNode<RangeTreeNode<T>> currentNode,
            T start, T end)
        {

            if (currentNode.IsLeaf)
            {
                //start is less than current node
                if (InRange(currentNode, start, end))
                {
                    foreach (var v in currentNode.Values)
                    {
                        result.Add(v);
                    }
                }
            }
            //if start is less than current
            //move left
            else
            {
                if (start.CompareTo(currentNode.Value.Data) <= 0)
                {
                    if (currentNode.Left != null)
                    {
                        GetInRange(result, visited, currentNode.Left, start, end);
                    }


                    //start is less than current node
                    if (!visited.ContainsKey(currentNode)
                        && InRange(currentNode, start, end))
                    {
                        foreach (var v in currentNode.Values)
                        {
                            result.Add(v);
                        }

                        visited.Add(currentNode, false);
                    }
                }
                //if start is greater than current
                //and end is greater than current
                //move right
                if (end.CompareTo(currentNode.Value.Data) >= 0)
                {
                    if (currentNode.Right != null)
                    {
                        GetInRange(result, visited, currentNode.Right, start, end);
                    }

                    //start is less than current node
                    if (!visited.ContainsKey(currentNode)
                        && InRange(currentNode, start, end))
                    {
                        foreach (var v in currentNode.Values)
                        {
                            result.Add(v);
                        }

                        visited.Add(currentNode, false);
                    }
                }
            }

            return result;
        }

        /// <summary>
        /// checks if current node is in search range
        /// </summary>
        /// <param name="currentNode"></param>
        /// <param name="start"></param>
        /// <param name="end"></param>
        /// <returns></returns>
        private bool InRange(RedBlackTreeNode<RangeTreeNode<T>> currentNode, T start, T end)
        {
            //start is less than current & end is greater than current
            return start.CompareTo(currentNode.Value.Data) <= 0
                && end.CompareTo(currentNode.Value.Data) >= 0;
        }
    }
}
