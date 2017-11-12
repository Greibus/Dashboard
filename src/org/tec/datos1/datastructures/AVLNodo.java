package org.tec.datos1.datastructures;

public class AVLNodo<T extends Comparable<T>> {
	T dato;
	AVLNodo<T> rigth;
	AVLNodo<T> left;
	public int height;
	
	public AVLNodo(T dato) {
		this.dato = dato;
		this.rigth = null;
		this.left = null;
		this.height = 0;
	}
	
	public AVLNodo(T dato, AVLNodo<T> left, AVLNodo<T>rigth, int height) {
		this.dato = dato;
		this.left = left;
		this.rigth = rigth;
		this.height = height;
	}
	
	public void setHeight(int height) {
		this.height = height;
		
	}
	
	public int getHeight() {
		return this.height;
	}


}




