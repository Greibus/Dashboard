using System;
using org.tec.datastructures.Nodes;

namespace org.tec.datastructures.Linear{
    
    public class CircularList<T> where T : IComparable<T>{
        
        private SimpleNode<T> _head;
	
        public CircularList() {
            _head = null;
            
        }
	
        public CircularList(SimpleNode<T> head, SimpleNode<T> next) {
            _head = head;
            if (_head != null) {
                _head.SetNext(next);
            }
		
        }
	
        public SimpleNode<T> GetHead(){
            return _head;
        }
	
	
        public void Append (T value) {
            if (_head == null){
                _head = new SimpleNode<T>(value);
                _head.SetNext(_head);
                return;
            }
            T headCopy = _head.GetValue();
            SimpleNode<T> headNextCopy = _head.GetNext();
		
            _head.SetValue(value);
		
            SimpleNode<T> insert = new SimpleNode<T>(headCopy);
		
            _head.SetNext(insert);
            insert.SetNext(headNextCopy);
		
            _head = insert;
        }
	
	
        public SimpleNode<T> Search(T value) {
            SimpleNode<T> temp = _head;
		
            if (_head == null) {
                return null;
            } 
		
            do {
                if(temp.GetValue().Equals(value)){
                    return temp;
                }
                temp = temp.GetNext();
			
            }while(temp != _head);
		
            return null;
        }

        public void Delete(T value) {
            SimpleNode<T> temp = _head;
		
            if (_head == null) {
                return;
            }
		
            do {
                if(temp.GetValue().Equals(value)) {
                    if (temp == _head) {
                        if (Length() == 1) {
                            _head = null;
                        }
                    }
                    temp.SetValue(temp.GetNext().GetValue());
                    if (temp.GetNext() == _head) {
                        _head = temp;
                    }
                    temp.SetNext(temp.GetNext().GetNext());
                    break;
                }
                temp = temp.GetNext();
			
            }while(temp != _head);
		
        }		
	
	
        public bool IsEmpty(){
            return _head == null;
        }
	
	
        public int Length() {
            SimpleNode<T> temp = _head;
            if (temp == null) {
                return 0;
            }
            int result = 0;
            do {
                result++;
                temp = temp.GetNext();
            }while (temp != _head);
            return result;
        }

        public void Clear() {
            _head = null;
        }
        
        
    }
}