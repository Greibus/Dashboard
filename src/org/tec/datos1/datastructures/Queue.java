package org.tec.datos1.datastructures;

import java.util.LinkedList;

public class Queue<T> {
	private LinkedList<T> list;
	
	public Queue() {
		this.list = new LinkedList<T>();
	}
	
	public void enqueue(T element) {
	    list.addLast(element);
	  }
	
	public void dequeue() {
		list.removeFirst();
	}
	
	public void imprimir( ) {
		for(int i = 0; i< list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	public boolean isEmpty() {
		return this.list.isEmpty();
	}


}
