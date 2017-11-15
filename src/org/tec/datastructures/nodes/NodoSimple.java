package org.tec.datastructures.nodes;



public class NodoSimple<T extends Comparable<T>> {
	private NodoSimple<T> siguiente;
	private T valor;
	
	public NodoSimple (T valor) {
		this.valor = valor;
		this.siguiente = null;
		
	}
	
	public T getValue() {
		return valor;
	}
	
	public void setValue(T valor) {
		this.valor = valor;
	}
	
	public NodoSimple<T> getNext() {
		return siguiente;
	}
	
	public void setNext(NodoSimple<T> siguiente) {
		this.siguiente = siguiente;
	}


}	
