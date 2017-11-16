using System;

namespace org.tec.datastructures.Nodes {

    public class SplayNode<T> {

        public SplayNode<T> left = null;
        public SplayNode<T> right = null;
        public T value;
        public int height;
        public int balance;

        public SplayNode(T value){
            this.value = value;
        }

        public Boolean hasChilden(){
            return left != null || right != null;
        }

        public SplayNode<T> RotateLeft(){
            SplayNode<T> n1 = right;
            right = n1.left;
            n1.left = this;
            return n1;
        }

        public SplayNode<T> RotateRight()
        {
            SplayNode<T> n1 = left;
            left = n1.right;
            n1.right = this;
            return n1;
        }
    }
}