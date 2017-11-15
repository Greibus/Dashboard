package org.tec.datastructures.linear;

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
		if (!list.isEmpty())
			return this.list.removeLast();
		return null;
		
	}
	public T peek() {
		if (!list.isEmpty())
			return this.list.getLast();
		return null;
	}
	
	public void print() {
		if (!list.isEmpty()) {
			for(int i = 0;i< list.size(); i++) {
				System.out.println(list.get(i));
			}
		}
	}
	public boolean isEmpty() {
		return this.list.isEmpty();
	}
	
	public int size() {
		return this.list.size();
	}

}
