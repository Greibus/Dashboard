package org.tec.datos1.datastructures;

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
	
	
	public NodoDoble<T> getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(NodoDoble<T> siguiente) {
		this.siguiente = siguiente;
	}

	public T getValor() {
		return valor;
	}

	public void setValor(T valor) {
		this.valor = valor;
	}
	
	public void setAnterior(NodoDoble<T> anterior) {
		this.anterior = anterior;
	}
	
	public NodoDoble<T> getAnterior() {
		return anterior;
	}


}
