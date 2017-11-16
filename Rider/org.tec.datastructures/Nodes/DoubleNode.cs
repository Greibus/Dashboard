<<<<<<< HEAD
﻿namespace org.tec.datastructures
{
    public class DoubleNode
    {
        
    }
=======
﻿using System;

namespace org.tec.datastructures.Nodes
{
    public class DoubleNode<T> where T : IComparable<T>{
        
        private T _value;
        private DoubleNode<T> _next;
        private DoubleNode<T> _previous;
	
        public DoubleNode(T value){
            _value = value;
            _next = null;
            _previous = null;
        }
	
        public DoubleNode(T value, DoubleNode<T> next, DoubleNode<T> previous) {
            _value = value;
            _next = next;
            _previous = previous;
        }

        public T GetValue() {
            return _value;
        }

        public void SetValue(T value) {
            _value = value;
        }

        public DoubleNode<T> GetNext() {
            return _next;
        }

        public void SetNext(DoubleNode<T> next) {
            _next = next;
        }

        public DoubleNode<T> GetPrevious() {
            return _previous;
        }
	
        public void SetPrevious(DoubleNode<T> previous) {
            _previous = previous;
        }

        public bool HasNext(){
            return _next != null;
        }
        
    }
>>>>>>> branch 'master' of https://github.com/Greibus/Dashboard.git
}