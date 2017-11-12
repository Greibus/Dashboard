package org.tec.datos1.datastructures;

public class ListaCircular<T extends Comparable<T>> {
	private NodoSimple<T> primero;
	private NodoSimple<T> ultimo;
	private int numeroElementos;
	

	public ListaCircular() {
		this.primero = null;
		this.ultimo = this.primero;
		this.numeroElementos = 0;
		
	}
				
	public boolean isEmpty() {
		return this.primero == null;

	}
	
	
	public NodoSimple<T> getPrimero() {
		return primero;
	}

	public void setPrimero(NodoSimple<T> primero) {
		this.primero = primero;
	}

	public void insertarFinal(T valor) {
		if (this.primero == null) {
			this.primero = new NodoSimple<T>(valor);
			this.primero.setSiguiente(this.primero);
			this.ultimo = this.primero;
		} else {
			NodoSimple<T> actual = new NodoSimple<T>(valor);
			this.ultimo.setSiguiente(actual);
			actual.setSiguiente(this.primero);
			this.ultimo = actual;
		}
		this.numeroElementos++;
			
	}
	
	public void insertarInicio(T valor) {
		if(this.primero == null) {
			this.primero = new NodoSimple<T>(valor);
			this.primero.setSiguiente(this.primero);
			this.ultimo = this.primero;
		} else {
			NodoSimple<T> actual = new NodoSimple<T>(valor);
			actual.setSiguiente(this.primero);
			this.primero = actual;
			ultimo.setSiguiente(this.primero);
		}
		this.numeroElementos++;
			
	}
	
		
	public void imprimir() {
		NodoSimple<T> actual = this.primero;
		while (actual != this.ultimo) {
			System.out.println(actual.getValor());
			actual = actual.getSiguiente();
		}
		System.out.println(this.ultimo.getValor());

	}
	

	public NodoSimple<T> buscar(T buscado) {
		NodoSimple<T> actual = this.primero;
		while (actual.getSiguiente() != this.ultimo.getSiguiente()) {
			if (actual.getValor().compareTo(buscado) == 0) {
				return actual;
			} else {
				actual = actual.getSiguiente();
			}
		}
		if (this.ultimo.getValor().compareTo(buscado) == 0) {
			return this.ultimo;
		}
		return null;
	}
	
	

	public void eliminar(T valor) {
		if (buscar(valor) != null) {
			if (this.primero.getValor() == valor) {
				this.primero = this.primero.getSiguiente();
				this.ultimo.setSiguiente(this.primero);
			} else {
				NodoSimple<T> actual = this.primero;
				while(actual.getSiguiente().getValor() != valor) {
					actual = actual.getSiguiente();
				}
				if (actual.getSiguiente() == this.ultimo) {
                    actual.setSiguiente(this.primero);
                    this.ultimo = actual;
                } else {
                	NodoSimple<T> siguiente = actual.getSiguiente();
                	actual.setSiguiente(siguiente.getSiguiente());	
                }
			}
		}
		this.numeroElementos--;
	}

	public int getNumeroElementos() {
		return numeroElementos;
	}


}
