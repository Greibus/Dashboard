package org.tec.datastructures.linear;

import org.tec.datastructures.nodes.NodoSimple;


public class SimpleList<T extends Comparable<T>> {
	private NodoSimple<T> primero;
	int numeroElementos;
	
	public SimpleList() {
		this.primero = null;
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
	
	public void printList() {
		NodoSimple<T> actual = this.primero;
		while ( actual != null) {
			System.out.println(actual.getValue());
			actual = actual.getNext();
		}
	}
	
	public void insertFirst (T valor) {
		if (this.primero == null) {
			this.primero = new NodoSimple<T>(valor);
			this.numeroElementos++;
		} else {
			NodoSimple<T> nuevo = new NodoSimple<T>(valor);
			nuevo.setNext(this.primero);
			this.primero = nuevo;
			this.numeroElementos++;
		}
		
	}
	
	public void insertEnd (T valor) {
		if (this.primero == null) {
			this.primero = new NodoSimple<T>(valor);
			this.numeroElementos++;
		} else {
			NodoSimple<T> actual = this.primero;
			while( actual.getNext() != null) {
				actual = actual.getNext();
			}
			actual.setNext(new NodoSimple<T>(valor));
		}	this.numeroElementos++;
		
	}
	
	public NodoSimple<T> search(T buscado) {
		NodoSimple<T> actual = this.primero;
		while(actual != null) {
			if (actual.getValue().compareTo(buscado) == 0) {
				return actual;
			} else {
				actual = actual.getNext();
			}
		}
		return null;
	}
	
	public void remove (T valor) {
		if (search(valor) != null) {
			if (this.primero.getValue() == valor) {
				this.primero = this.primero.getNext();
				this.numeroElementos--;
			} else {
				if (this.primero != null) {
					NodoSimple<T> anterior = this.primero;
					while( anterior.getNext().getValue() != valor){
						anterior = anterior.getNext();
					}
					NodoSimple<T> actual = anterior.getNext().getNext();
					anterior.setNext(actual);  
				}
        	 
			}
			this.numeroElementos--;
		
		}
		
	}
	
	public int getSize () {
		return this.numeroElementos;
	}
	
	
}



		