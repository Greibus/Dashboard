
﻿using System;
using org.tec.datastructures.Nodes;

namespace org.tec.datastructures
{
    public class Stack<T> where T : IComparable<T>{
        
        private SimpleNode<T> _head;
	
        public Stack() {
            _head = null;
        }
	
        public Stack(SimpleNode<T> head) {
            _head = head;
        }
	
        public SimpleNode<T> Peak(){
            return _head;
        }
	
	
        public void Push (T value) {
            if (_head == null){
                _head = new SimpleNode<T>(value);
                return;
            }
            SimpleNode<T> appended = new SimpleNode<T>(value);
            appended.SetNext(_head);
            _head = appended;
        }

	
        public SimpleNode<T> Pop() {
            if (_head == null) {
                return null;
            }
            SimpleNode<T> popped = _head;
            _head = _head.GetNext();
            return popped;
        }

        public int Size() {
            SimpleNode<T> current = _head;
            int result = 0;
            while (current != null){
                result++;
                current = current.GetNext();
            }
            return result;
        }

        public void Clear() {
            _head = null;
        }
        
    }
}