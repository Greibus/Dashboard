package org.tec.datastructures.nodes;



public class BinaryNodo<T extends Comparable<T>> {
	public T element;
	public BinaryNodo<T> left, rigth;
	
	public BinaryNodo(T element) {
		this.element = element;
		this.rigth = null;
		this.left = null;
	}

	public BinaryNodo(T element, BinaryNodo<T> left, BinaryNodo<T> rigth) {
		this.element = element;
		this.left = left;
		this.rigth = rigth;
	}
}
	