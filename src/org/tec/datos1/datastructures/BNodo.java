package org.tec.datos1.datastructures;

public class BNodo<T extends Comparable<T>> {
	T element;
	BNodo<T> rigth;
	BNodo<T> left;
	
	public BNodo(T element) {
		this.element = element;
		this.rigth = null;
		this.left = null;
	}
	
	public BNodo(T element, BNodo<T> left, BNodo<T>rigth) {
		this.element = element;
		this.left = left;
		this.rigth = rigth;
	}
}