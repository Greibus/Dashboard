package org.tec.datastructures.nodes;


public class NodoDoble<T extends Comparable<T>> {
	private NodoDoble<T> siguiente;
	private NodoDoble<T> anterior;
	private T valor;
	
	public NodoDoble() {
		
	}

	public NodoDoble(T valor) {
		this.valor = valor;
		this.siguiente = null;
		this.anterior = null;
		
	}
	
	
	public NodoDoble<T> getNext() {
		return siguiente;
	}

	public void setNext(NodoDoble<T> siguiente) {
		this.siguiente = siguiente;
	}

	public T getValue() {
		return valor;
	}

	public void setValue(T valor) {
		this.valor = valor;
	}
	
	public void setPrevious(NodoDoble<T> anterior) {
		this.anterior = anterior;
	}
	
	public NodoDoble<T> getPrevious() {
		return anterior;
	}


}

