package org.tec.datos1.datastructures;

public class NodoSimple<T extends Comparable<T>> {
	private NodoSimple<T> siguiente;
	private T valor;
	
	public NodoSimple (T valor) {
		this.valor = valor;
		this.siguiente = null;
		
	}
	
	public T getValor() {
		return valor;
	}
	
	public void setValor(T valor) {
		this.valor = valor;
	}
	
	public NodoSimple<T> getSiguiente() {
		return siguiente;
	}
	
	public void setSiguiente(NodoSimple<T> siguiente) {
		this.siguiente = siguiente;
	}


}	

