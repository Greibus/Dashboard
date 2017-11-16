
﻿using System;
using org.tec.datastructures.Nodes;

namespace org.tec.datastructures.Hierarchical
{
    public class BinaryTree<T> where T : IComparable<T>{
        
        private BinaryTreeNode<T> _root;
	
        public BinaryTree() {
            _root = null; 
        }
	
        public BinaryTree(BinaryTreeNode<T> root) {
            _root = root;
        }
	
        public BinaryTreeNode<T> GetRoot() {
            return _root;
        }

        public void SetRoot(BinaryTreeNode<T> root) {
            _root = root;
        }

        public void Append(T value) {
		
            if (_root == null) {
                _root = new BinaryTreeNode<T>(value);
                return;
            }
            _root = AppendRecursive(value,_root);
		
        }

        private BinaryTreeNode<T> AppendRecursive(T value, BinaryTreeNode<T> node) {
            if (node == null) {
                node = new BinaryTreeNode<T>(value);
            }
            else if (node.GetValue().CompareTo(value) > 0) {
                node.SetLeft(AppendRecursive(value, node.GetLeft()));
            }
            else if (node.GetValue().CompareTo(value) < 0) {
                node.SetRight(AppendRecursive(value, node.GetRight()));
            }
            return node;
        }
	
        public BinaryTreeNode<T> Search(T value) {
            return SearchRecursive(value,_root);
        }

        private BinaryTreeNode<T> SearchRecursive(T value, BinaryTreeNode<T> node) {
            if (node == null) {
                return null;
            }
            if (node.GetValue().CompareTo(value) > 0) {
                return SearchRecursive(value, node.GetLeft());
            }
            if (node.GetValue().CompareTo(value) < 0) {
                return SearchRecursive(value, node.GetRight());
            }
            return node;
        }
	
        public void Delete(T value) {
            _root = DeleteRecursive(value, _root);
        }
	
        private BinaryTreeNode<T> DeleteRecursive(T value, BinaryTreeNode<T> node) {
            if (node == null) {
                return null;
            }
            if (node.GetValue().CompareTo(value) > 0) {
                node.SetLeft(DeleteRecursive(value, node.GetLeft()));
            }
            else if (node.GetValue().CompareTo(value) < 0) {
                node.SetRight(DeleteRecursive(value, node.GetRight()));
            } 
            else if (node.GetLeft() != null && node.GetRight() != null) {
                T replace = FindMin(node.GetRight()).GetValue();
			
                node.SetValue(replace);
                node.SetRight(DeleteRecursive(replace,node.GetRight()));
            }
            else {
                node = (node.GetLeft() == null) ? node.GetRight() : node.GetLeft();
            }
            return node;
        }

        private BinaryTreeNode<T> FindMin(BinaryTreeNode<T> node) {
            if (node.GetLeft() == null) {
                return node;
            }
            return FindMin(node.GetLeft());
        }
        
        
    }
}