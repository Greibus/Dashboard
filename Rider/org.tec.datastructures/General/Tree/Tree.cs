﻿using System;

namespace Advanced.Algorithms.DataStructures
{
    public class TreeNode<T> : IComparable where T : IComparable
    {
        public T Value { get; set; }

        public TreeNode<T> Parent { get; set; }
        public SinglyLinkedList<TreeNode<T>> Children { get; set; }

        public bool IsLeaf => Children.Count() == 0;

        public TreeNode(TreeNode<T> parent, T value)
        {
            this.Parent = parent;
            this.Value = value;

            Children = new SinglyLinkedList<TreeNode<T>>();
        }

        public int CompareTo(object obj)
        {
            return CompareTo(obj as TreeNode<T>);
        }

        public int CompareTo(TreeNode<T> treeNode)
        {
            return Value.CompareTo(treeNode.Value);
        }
    }

    //TODO support initial  bulk loading if possible
    //TODO implement IEnumerable & make sure duplicates are handled correctly if its not already
    public class Tree<T> where T : IComparable
    {

        private TreeNode<T> Root { get; set; }
        public int Count { get; private set; }


        //O(n)
        public bool HasItem(T value)
        {
            if (Root == null)
            {
                return false;
            }

            return Find(Root, value) != null;
        }

        //O(n)
        private TreeNode<T> Find(T value)
        {
            if (Root == null)
            {
                return null;
            }

            return Find(Root, value);
        }

        //O(n)
        public int GetHeight()
        {
            return GetHeight(Root);
        }
        //O(n)
        private int GetHeight(TreeNode<T> node)
        {
            if (node == null)
            {
                return -1;
            }

            var children = node.Children.GetAllNodes();

            int currentHeight = -1;

            for (int i = 0; i < children.Count; i++)
            {
                var childHeight = GetHeight(children[i]);

                if (currentHeight < childHeight)
                {
                    currentHeight = childHeight;
                }
            }

            currentHeight++;

            return currentHeight;
        }

        //O(1)
        //add the new child under this parent
        public void Insert(T parentValue, T value)
        {
            if (Root == null)
            {
                Root = new TreeNode<T>(null, value);
                Count++;
                return;
            }

            var parent = Find(parentValue);

            if (parent == null)
            {
                throw new ArgumentNullException("parent");
            }

            var exists = Find(Root, value) != null;

            if (exists)
            {
                throw new ArgumentNullException("value already exists");
            }

            parent.Children.InsertFirst(new TreeNode<T>(parent, value));
            Count++;
        }

        public void Delete(T value)
        {
            Delete(Root.Value, value);
        }

        //O(n)
        //remove the node with the given identifier from the descendants if it can be deleted unambiguosly
        public void Delete(T parentValue, T value)
        {
            var parent = Find(parentValue);

            if (parent == null)
            {
                throw new Exception("Cannot find parent");
            }

            var itemToRemove = Find(parent, value);

            if (itemToRemove == null)
            {
                throw new Exception("Cannot find item");
            }

            if (itemToRemove != null)
            {
                //if item is root
                if (itemToRemove.Parent == null)
                {
                    if (itemToRemove.Children.Count() == 0)
                    {
                        Root = null;
                    }
                    else
                    {
                        if (itemToRemove.Children.Count() == 1)
                        {
                            Root = itemToRemove.Children.DeleteFirst();
                            Root.Parent = null;
                        }
                        else
                        {
                            throw new Exception("Node have multiple children. Cannot delete node unambiguosly");
                        }
                    }

                }
                else
                {
                    if (itemToRemove.Children.Count() == 0)
                    {
                        itemToRemove.Parent.Children.Delete(itemToRemove);
                    }
                    else
                    {
                        if (itemToRemove.Children.Count() == 1)
                        {
                            var orphan = itemToRemove.Children.DeleteFirst();
                            orphan.Parent = itemToRemove.Parent;

                            itemToRemove.Parent.Children.InsertFirst(orphan);
                            itemToRemove.Parent.Children.Delete(itemToRemove);
                        }
                        else
                        {
                            throw new Exception("Node have multiple children. Cannot delete node unambiguosly");
                        }
                    }
                }
                Count--;
            }


        }

        //O(n)
        //find the node with the given identifier among descendants of parent
        private TreeNode<T> Find(TreeNode<T> parent, T value)
        {

            if (parent.Value.CompareTo(value) == 0)
            {
                return parent;
            }

            var children = parent.Children.GetAllNodes();

            for (int i = 0; i < children.Count; i++)
            {
                var result = Find(children[i], value);

                if (result != null)
                {
                    return result;
                }
            }

            return null;
        }

    }

}
