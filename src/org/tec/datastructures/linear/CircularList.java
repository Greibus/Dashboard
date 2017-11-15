package org.tec.datastructures.linear;

import org.tec.datastructures.nodes.NodoSimple;

public class CircularList<T extends Comparable<T>> {
	private NodoSimple<T> primero;
	private NodoSimple<T> ultimo;
	private int numeroElementos;
	

	public CircularList() {
		this.primero = null;
		this.ultimo = this.primero;
		this.numeroElementos = 0;
		
	}
				
	public boolean isEmpty() {
		return this.primero == null;

	}
	
	
	public NodoSimple<T> getFirst() {
		return primero;
	}

	public void setFirst(NodoSimple<T> primero) {
		this.primero = primero;
	}

	public void insertEnd(T valor) {
		if (this.primero == null) {
			this.primero = new NodoSimple<T>(valor);
			this.primero.setNext(this.primero);
			this.ultimo = this.primero;
		} else {
			NodoSimple<T> actual = new NodoSimple<T>(valor);
			this.ultimo.setNext(actual);
			actual.setNext(this.primero);
			this.ultimo = actual;
		}
		this.numeroElementos++;
			
	}
	
	public void insertFirst(T valor) {
		if(this.primero == null) {
			this.primero = new NodoSimple<T>(valor);
			this.primero.setNext(this.primero);
			this.ultimo = this.primero;
		} else {
			NodoSimple<T> actual = new NodoSimple<T>(valor);
			actual.setNext(this.primero);
			this.primero = actual;
			ultimo.setNext(this.primero);
		}
		this.numeroElementos++;
			
	}
	
		
	public void print() {
		NodoSimple<T> actual = this.primero;
		while (actual != this.ultimo) {
			System.out.println(actual.getValue());
			actual = actual.getNext();
		}
		System.out.println(this.ultimo.getValue());

	}
	

	public NodoSimple<T> search(T buscado) {
		if (this.primero == null)
			return null;
		NodoSimple<T> actual = this.primero.getNext();
		while (actual != this.ultimo.getNext()) {
			if (actual.getValue().compareTo(buscado) == 0) {
				return actual;
			} else {
				actual = actual.getNext();
			}
		}
		if (this.primero.getValue().compareTo(buscado) == 0) {
			return this.ultimo;
		}
		return null;
	}
	
	

	public void remove(T valor) {
		if (search(valor) != null) {
			if (this.primero.getValue() == valor) {
				this.primero = this.primero.getNext();
				this.ultimo.setNext(this.primero);
			} else {
				NodoSimple<T> actual = this.primero;
				while(actual.getNext().getValue() != valor) {
					actual = actual.getNext();
				}
				if (actual.getNext() == this.ultimo) {
                    actual.setNext(this.primero);
                    this.ultimo = actual;
                } else {
                	NodoSimple<T> siguiente = actual.getNext();
                	actual.setNext(siguiente.getNext());	
                }
			}
		}
		this.numeroElementos--;
	}

	public int getSize() {
		return numeroElementos;
	}


}
