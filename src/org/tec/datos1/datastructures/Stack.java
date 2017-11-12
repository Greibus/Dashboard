package org.tec.datos1.datastructures;

import java.util.LinkedList;

public class Stack<T extends Comparable<T>> {
	private LinkedList<T> list;
	
	public Stack() {
		this.list = new LinkedList<T>();
	}
	
	public void push(T element) {
		this.list.addLast(element);
	}
	
	public T pop() {
		return this.list.removeLast();
		
	}
	public T peek() {
		return this.list.getLast();
	}
	
	public void imprimir() {
		for(int i = 0;i< list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	public boolean isEmpty() {
		return this.list.isEmpty();
	}

}
