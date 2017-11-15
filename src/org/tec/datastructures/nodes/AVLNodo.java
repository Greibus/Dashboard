package org.tec.datastructures.nodes;


public class AVLNodo<T extends Comparable<T>> {
	public T dato;
	public AVLNodo<T> rigth;
	public AVLNodo<T> left;
	public int height;
	
	public AVLNodo(T dato) {
		this.dato = dato;
		this.rigth = null;
		this.left = null;
		this.height = 0;
	}

}