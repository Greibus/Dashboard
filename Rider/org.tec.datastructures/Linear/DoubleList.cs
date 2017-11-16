<<<<<<< HEAD
﻿namespace org.tec.datastructures
{
    public class DoubleList
    {
        
    }
=======
﻿using System;
using org.tec.datastructures.Nodes;

namespace org.tec.datastructures.Linear
{
    public class DoubleList<T> where T : IComparable<T>{
        
        DoubleNode<T> _head;
	
        public DoubleList(DoubleNode<T> head) {
            _head = head;
        }
        public DoubleList() {
            _head = null;
        }
	
        public DoubleNode<T> GetHead(){
            return _head;
        }
	
	
        public void Append(T value) {
            if (_head == null){
                _head = new DoubleNode<T>(value);
                return;
            }
            DoubleNode<T> temp = _head;
            while (temp.HasNext()){
                temp = temp.GetNext();
            }
            DoubleNode<T> temp2 = new DoubleNode<T>(value); 
            temp.SetNext(temp2);
            temp2.SetPrevious(temp);
        }
	
	
        public DoubleNode<T> Search(T value) {
            DoubleNode<T> temp = _head;
            while(temp != null){
                if(temp.GetValue().Equals(value)){
                    return temp;
                }
                temp = temp.GetNext();
            }
            return null;
        }

	
        public void Delete(T value) {
            DoubleNode<T> temp = _head;
            while(temp != null){
                if(temp.GetValue().Equals(value)){
                    if (temp == _head){
                        _head = temp.GetNext();
                    }
                    else{
                        temp.GetPrevious().SetNext(temp.GetNext());
                    }
                    break;
				
                }
                temp = temp.GetNext();
            }
	
        }
	
        public bool IsEmpty(){
            return _head == null;
        }

	
        public int Length() {
            DoubleNode<T> current = _head;
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
>>>>>>> branch 'master' of https://github.com/Greibus/Dashboard.git
}