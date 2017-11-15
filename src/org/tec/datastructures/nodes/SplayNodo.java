package org.tec.datastructures.nodes;



public class SplayNodo<Key extends Comparable<Key>, Value>{
    public Key key;           
    public Value value;        
    public SplayNodo<Key, Value> left;
	public SplayNodo<Key, Value> right;  

    public SplayNodo(Key key, Value value) {
        this.key   = key;
        this.value = value;
    }
}