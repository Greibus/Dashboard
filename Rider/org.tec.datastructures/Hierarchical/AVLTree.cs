
﻿using System;
using org.tec.datastructures.Nodes;

namespace org.tec.datastructures.Hierarchical
{
    public class AvlTree<T> where T : IComparable<T> {
        
        private AvlTreeNode<T> _root;
	
        public AvlTree() {
            _root = null;
        }
	
        public AvlTree(AvlTreeNode<T> root) {
            SetRoot(root);
        }

        public AvlTreeNode<T> GetRoot() {
            return _root;
        }

        public void SetRoot(AvlTreeNode<T> root) {
            _root = root;
        }
	
        private int ValidateHeight(AvlTreeNode<T> node){
            if (node == null) {
                return 0;
            }
            return node.GetHeight(); 
        }
	
        public void Append(T value) {
            _root = AppendRecursive(value,_root);
		
        }
	
        private AvlTreeNode<T> AppendRecursive(T value, AvlTreeNode<T> node) {
            if (node == null) {
                node = new AvlTreeNode<T>(value);
            }
            else if(value.CompareTo(node.GetValue()) < 0) {
                node.SetLeft(AppendRecursive(value, node.GetLeft()));
            }
            else if (value.CompareTo(node.GetValue()) > 0) {
                node.SetRight(AppendRecursive(value, node.GetRight()));
            }
            node = Balance(node);
            node.CalculateHeight();
            return node;
        }
	
        public AvlTreeNode<T> Search(T value){
            return SearchRecursive(value,_root);
        }
	
        private AvlTreeNode<T> SearchRecursive(T value, AvlTreeNode<T> node) {
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
            DeleteRecursive(value, _root);
        }

        private AvlTreeNode<T> DeleteRecursive(T value, AvlTreeNode<T> node) {
		
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
                if (node == _root) {
                    node = (node.GetLeft() == null) ? node.GetRight() : node.GetLeft();
                    _root = node;
                }else {
                    node = (node.GetLeft() == null) ? node.GetRight() : node.GetLeft();
                }
			
			
            }

            node = Balance(node);
            return node;
        }
	
        private AvlTreeNode<T> FindMin(AvlTreeNode<T> node) {
            if (node.GetLeft() == null) {
                return node;
            }
            return FindMin(node.GetLeft());
        }
	
        private AvlTreeNode<T> Balance(AvlTreeNode<T> node) {
		
            if (node == null) {
                return null;
            }
		
            int balance = ValidateHeight(node.GetLeft())
                          - ValidateHeight(node.GetRight());
            if (balance == 2){
                if (node.GetLeft().GetLeft() != null) {
                    if (node == _root){
                        _root = node.GetLeft();
                    }
                    node = node.RotateLeftChild();
				
                }
                else {
                    if (node == _root){
                        _root = node.GetLeft().GetRight();
                    }
                    node = node.RotateDoubleLeft();
                }
            }
            else if (balance == -2) {
                if (node.GetRight().GetRight() != null) {
                    if (node == _root) {
                        _root = node.GetRight();
                    } 
                    node = node.RotateRightChild();
                }
                else {
                    if (node == _root) {
                        _root = node.GetRight().GetLeft();
                    }
                    node = node.RotateDoubleRight();
                }
            }
            node.CalculateHeight();
            return node;
        }

    }
}