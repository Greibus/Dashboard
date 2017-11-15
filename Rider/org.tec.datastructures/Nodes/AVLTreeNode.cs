using System;

namespace org.tec.datastructures.Nodes{
    
    public class AvlTreeNode<T> where T : IComparable<T>{
        
        private T _value;
        private AvlTreeNode<T> _right;
        private AvlTreeNode<T> _left;
        private int _height;

        public AvlTreeNode(T value, AvlTreeNode<T> right, AvlTreeNode<T> left, int height) {
            _value = value;
            _right = right;
            _left = left;
            _height = height;
        }

        public AvlTreeNode(T value) {
            _value = value;
            _right = null;
            _left = null;
            _height = 0;
		
        }

        public T GetValue() {
            return _value;
        }

        public void SetValue(T value) {
            _value = value;
        }

        public AvlTreeNode<T> GetRight() {
            return _right;
        }

        public AvlTreeNode<T> GetLeft() {
            return _left;
        }

        public void SetRight(AvlTreeNode<T> right) {
            _right = right;
        }

        public void SetLeft(AvlTreeNode<T> left) {
            _left = left;
        }

        public int GetHeight() {
            return _height;
        }

        public void SetHeight(int height) {
            _height = height;
        }

        public bool HasChildren() {
            return (_right != null || _left != null);
        }
	
        public void CalculateHeight() {
		
            int newHeight;
            if (!HasChildren()) {
                newHeight = 0;
            }else if (_right == null) {
                newHeight = _left.GetHeight();
            }else if (_left == null) {
                newHeight = _right.GetHeight();
            }else {
                newHeight = (_right.GetHeight() > _left.GetHeight() 
                    ? _right.GetHeight():_left.GetHeight()); 
            }
            SetHeight(newHeight + 1);
        }
	
        public AvlTreeNode<T> RotateLeftChild() {
            AvlTreeNode<T> n1 = _left;
            _left = n1._right;
            n1._right = this;
            CalculateHeight();
            n1.CalculateHeight();
            return n1;
        }
	
        public AvlTreeNode<T> RotateDoubleLeft(){
            _left = _left.RotateRightChild();
            return RotateLeftChild();
        }

        public AvlTreeNode<T> RotateRightChild() {
            AvlTreeNode<T> n1 = _right;
            _right = n1._left;
            n1._left = this;
            CalculateHeight();
            n1.CalculateHeight();
            return n1;
        }
	
        public AvlTreeNode<T> RotateDoubleRight(){
            _right = _right.RotateLeftChild();
            return RotateRightChild();
        }
	
    }	
 
        
        
        
}
