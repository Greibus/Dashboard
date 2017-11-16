using System;

namespace org.tec.datastructures.Nodes {

    public class SplayNode<T> {

        public SplayNode<T> Left = null;
        public SplayNode<T> Right = null;
        public T Value;
        public int Height;
        public int Balance;

        public SplayNode(T value){
            this.Value = value;
        }

        public Boolean HasChilden(){
            return Left != null || Right != null;
        }

        public SplayNode<T> RotateLeft(){
            SplayNode<T> n1 = Right;
            Right = n1.Left;
            n1.Left = this;
            return n1;
        }

        public SplayNode<T> RotateRight()
        {
            SplayNode<T> n1 = Left;
            Left = n1.Right;
            n1.Right = this;
            return n1;
        }
    }
}