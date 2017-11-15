package org.tec.datastructures.linear;

import java.util.LinkedList;

public class Queue<T> {
	private LinkedList<T> list;
	private int numElements;
	
	public Queue() {
		this.list = new LinkedList<T>();
	}
	
	public void enqueue(T element) {
	    list.addLast(element);
	    this.numElements++;
	  }
	
	public void dequeue() {
		if (!list.isEmpty()) {
			list.removeFirst();
			this.numElements--;
		}
	}
	
	public void print( ) {
		for(int i = 0; i< list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	public boolean isEmpty() {
		return this.list.isEmpty();
	}
	
	public int size() {
		return this.numElements;
	}
	
	public T peek() {
		if (! list.isEmpty())
			return this.list.getFirst();
		else
			return null;
	}
	

}
