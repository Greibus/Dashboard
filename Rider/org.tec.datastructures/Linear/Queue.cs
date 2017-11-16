<<<<<<< HEAD
﻿namespace org.tec.datastructures
{
    public class Queue
    {
        
    }
=======
﻿using System;
using org.tec.datastructures.Nodes;

namespace org.tec.datastructures.Linear
{
    public class Queue<T> where T : IComparable<T>{
        
        private SimpleNode<T> _head;
	
        public Queue() {
            _head = null;
        }
	
        public Queue(SimpleNode<T> head) {
            _head = head;
        }
	
        public void Enqueue(T value) {
            if (_head == null) {
                _head = new SimpleNode<T>(value);
                return;
            }
		
            SimpleNode<T> temp = new SimpleNode<T>(value);
            temp.SetNext(_head);
            _head = temp;
        }
	
        public SimpleNode<T> Dequeue() {
            if (_head == null) {
                return null;
            }
		
            SimpleNode<T> temp = _head;
            SimpleNode<T> prev = null;
            while(temp.HasNext()) {
                prev= temp;
                temp = temp.GetNext();
            }
            if (prev == null) {
                Clear();
                return temp;
			
            }
            prev.SetNext(null);
            return temp;
        }
	
        public SimpleNode<T> Peak() {
            if (_head == null) {
                return null;
            }
		
            SimpleNode<T> temp = _head;
            while(temp.HasNext()) {
                temp = temp.GetNext();
            }
            return temp;
        }
	
        public int Size() {
            SimpleNode<T> temp = _head;
            int result = 0;
            while (temp != null){
                result++;
                temp = temp.GetNext();
            }
            return result;
        }

        public void Clear() {
            _head = null;
        }
        
    }
>>>>>>> branch 'master' of https://github.com/Greibus/Dashboard.git
}