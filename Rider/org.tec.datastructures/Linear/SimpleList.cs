
﻿using System;
using org.tec.datastructures.Nodes;

namespace org.tec.datastructures.Linear
{
    public class SimpleList<T> where T : IComparable<T>{
        
        private SimpleNode<T> _head;
	
        public SimpleList() {
            _head = null;
        }
	
        public SimpleList(SimpleNode<T> head) {
            _head = head;
        }
	
        public SimpleNode<T> GetHead(){
            return _head;
        }
	
	
        public void Append (T value) {
            if (_head == null){
                _head = new SimpleNode<T>(value);
                return;
            }
            SimpleNode<T> temp = _head;
            while (temp.HasNext()){
                temp = temp.GetNext();
            }
            temp.SetNext(new SimpleNode<T>(value));
        }

	
        public SimpleNode<T> Search(T value) {
            SimpleNode<T> temp = _head;
            while(temp != null){
                if(temp.GetValue().Equals(value)){
                    break;
                }
                temp = temp.GetNext();
            }
            return temp;
        }

        public void Delete(T value) {
            SimpleNode<T> temp = _head;
            SimpleNode<T> prev = null;
            while(temp != null){
                if(temp.GetValue().Equals(value)){
                    if (temp == _head){
                        _head = temp.GetNext();
                    }
                    else{
                        prev.SetNext(temp.GetNext());
                    }
                    break;
                }
                prev = temp;
                temp = temp.GetNext();
            }
		
        }		
	
	
        public bool IsEmpty(){
            return _head == null;
        }
	
	
        public int Length() {
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