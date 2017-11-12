package org.tec.datos1.datastructures;

public class ListaSimple<T extends Comparable<T>> {
	private NodoSimple<T> primero;
	int numeroElementos;
	
	public ListaSimple() {
		this.primero = null;
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
	
	public void imprimir() {
		NodoSimple<T> actual = this.primero;
		while ( actual != null) {
			System.out.println(actual.getValor());
			actual = actual.getSiguiente();
		}
	}
	
	public void insetarInicio (T valor) {
		if (this.primero == null) {
			this.primero = new NodoSimple<T>(valor);
			this.numeroElementos++;
		} else {
			NodoSimple<T> nuevo = new NodoSimple<T>(valor);
			nuevo.setSiguiente(this.primero);
			this.primero = nuevo;
			this.numeroElementos++;
		}
		
	}
	
	public void insertarFinal (T valor) {
		if (this.primero == null) {
			this.primero = new NodoSimple<T>(valor);
			this.numeroElementos++;
		} else {
			NodoSimple<T> actual = this.primero;
			while( actual.getSiguiente() != null) {
				actual = actual.getSiguiente();
			}
			actual.setSiguiente(new NodoSimple<T>(valor));
		}	this.numeroElementos++;
		
	}
	
	public NodoSimple<T> buscar(T buscado) {
		NodoSimple<T> actual = this.primero;
		while(actual != null) {
			if (actual.getValor().compareTo(buscado) == 0) {
				return actual;
			} else {
				actual = actual.getSiguiente();
			}
		}
		return null;
	}
	
	public void eliminar (T valor) {
		if (buscar(valor) != null) {
			if (this.primero.getValor() == valor) {
				this.primero = this.primero.getSiguiente();
				this.numeroElementos--;
			} else {
				if (this.primero != null) {
					NodoSimple<T> anterior = this.primero;
					while( anterior.getSiguiente().getValor() != valor){
						anterior = anterior.getSiguiente();
					}
					NodoSimple<T> actual = anterior.getSiguiente().getSiguiente();
					anterior.setSiguiente(actual);  
				}
        	 
			}
			this.numeroElementos--;
		
		}
		
	}
	
	public int getElementos () {
		return this.numeroElementos;
	}
	
	
}
		
		
		
	
	
	


	