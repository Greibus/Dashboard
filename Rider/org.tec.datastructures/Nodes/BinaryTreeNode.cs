
﻿using System;

namespace org.tec.datastructures.Nodes
{
    public class BinaryTreeNode<T> where T : IComparable<T>{
     
        private T _value;
        private BinaryTreeNode<T> _right;
        private BinaryTreeNode<T> _left;
	
        public BinaryTreeNode(T value,  BinaryTreeNode<T> right, BinaryTreeNode<T> left) {
            _value = value;
            _right = right;
            _left = left;
        }
	
        public BinaryTreeNode(T value) {
            _value = value;
            _right = null ;
            _left = null  ;
        }
	
        public T GetValue() {
            return _value;
        }

        public void SetValue(T value) {
            _value = value;
        }

        public BinaryTreeNode<T> GetRight() {
            return _right;
        }

        public BinaryTreeNode<T> GetLeft() {
            return _left;
        }
	
        public void SetRight(BinaryTreeNode<T> right) {
            _right = right;
        }
	
        public void SetLeft(BinaryTreeNode<T> left) {
            _left = left;
        }


        public bool HasChildren(){
            return (_right != null || _left != null);
        }
        
    }
}