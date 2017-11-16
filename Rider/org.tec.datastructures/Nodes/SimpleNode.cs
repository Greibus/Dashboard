
﻿using System;

namespace org.tec.datastructures.Nodes
{
    public class SimpleNode<T> where T : IComparable<T>{
        
        private T _value;
        private SimpleNode <T> _next;
	

        public SimpleNode(T value){
            SetValue(value);
            SetNext(null);
        }
	
        public T GetValue() {
            return _value;
        }

        public void SetValue(T value) {
            _value = value;
        }

        public SimpleNode<T> GetNext() {
            return _next;
        }

        public void SetNext(SimpleNode<T> next) {
            _next = next;
        }

        public bool HasNext(){
            return _next != null;
        }
        
    }
}