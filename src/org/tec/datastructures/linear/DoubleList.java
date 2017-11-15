package org.tec.datastructures.linear;

import org.tec.datastructures.nodes.NodoDoble;

public class DoubleList<T extends Comparable<T>> {
	private NodoDoble<T> primero;
	private int numeroElementos;


	public DoubleList() {
		this.primero = null;
		this.numeroElementos = 0;

	}
	
	
	public boolean isEmpty() {
		return this.primero == null;
	}
	
	public NodoDoble<T> getFirst() {
		return primero;
	}

	public void setFirst(NodoDoble<T> primero) {
		this.primero = primero;
	}
	


	
	public void insertEnd(T valor) {
		if (this.primero == null) {
			this.primero = new NodoDoble<T>(valor);
			this.numeroElementos++;
		} else {
			NodoDoble<T> actual = this.primero;
			NodoDoble<T> nuevo = new NodoDoble<T>(valor);
			while (actual.getNext() != null) {
				actual = actual.getNext();
			}
			actual.setNext(nuevo);
			nuevo.setPrevious(actual);
		}
		this.numeroElementos++;
		
	}
	
	public void insertFirst(T valor) {
		if (this.primero == null) {
			this.primero = new NodoDoble<T>(valor);
		} else {
			NodoDoble<T> nuevo = new NodoDoble<T>(valor);
			nuevo.setNext(this.primero);
			this.primero.setPrevious(nuevo);
			this.primero = nuevo;
		}
		this.numeroElementos++;
	}

	public void print() {
		NodoDoble<T> actual = this.primero;
		while (actual != null) {
			System.out.println(actual.getValue());
			actual = actual.getNext();
		}		
	}
	


	public NodoDoble<T> search(T buscado) {
		NodoDoble<T> actual = this.primero;
		while (actual != null) {
			if (actual.getValue().compareTo(buscado) == 0) {
				return actual;
			} else {
				actual = actual.getNext();
			}
		}
		return null;
		
	}
	
	public int getSize() {
		return numeroElementos;
	}
	
	
	public void remove(T valor) {
		if (search(valor) != null) {
			if (this.primero != null) {
				if (this.primero.getValue() == valor) {
		            this.primero = this.primero.getNext();
		            this.primero.setPrevious(null);
				} else {
				NodoDoble<T> actual = this.primero;
				NodoDoble<T> anterior = null;
				while ( actual != null) {
					if (actual.getValue().compareTo(valor) == 0) {
						if (anterior == null) {
                            this.primero = this.primero.getNext();
                            actual.setNext(null);
                            actual= this.primero;
               
						} else {
							anterior.setNext(actual.getNext());
                            actual.setNext(null);
                            actual = anterior.getNext();
						}
					} else {
						anterior = actual;
						actual = actual.getNext();
					}
					

				}
				}	
			}
			this.numeroElementos--;
		}		
	}			
	
}

