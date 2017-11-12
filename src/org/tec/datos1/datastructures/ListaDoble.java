package org.tec.datos1.datastructures;

public class ListaDoble<T extends Comparable<T>> {
	private NodoDoble<T> primero;
	private int numeroElementos;


	public ListaDoble() {
		this.primero = null;
		this.numeroElementos = 0;

	}
	
	
	public boolean isEmpty() {
		return this.primero == null;
	}
	
	public NodoDoble<T> getPrimero() {
		return primero;
	}

	public void setPrimero(NodoDoble<T> primero) {
		this.primero = primero;
	}
	


	
	public void insertarFinal(T valor) {
		if (this.primero == null) {
			this.primero = new NodoDoble<T>(valor);
			this.numeroElementos++;
		} else {
			NodoDoble<T> actual = this.primero;
			NodoDoble<T> nuevo = new NodoDoble<T>(valor);
			while (actual.getSiguiente() != null) {
				actual = actual.getSiguiente();
			}
			actual.setSiguiente(nuevo);
			nuevo.setAnterior(actual);
		}
		this.numeroElementos++;
		
	}
	
	public void insertarInicio(T valor) {
		if (this.primero == null) {
			this.primero = new NodoDoble<T>(valor);
			this.numeroElementos++;
		} else {
			NodoDoble<T> nuevo = new NodoDoble<T>(valor);
			nuevo.setSiguiente(this.primero);
			this.primero.setAnterior(nuevo);
			this.primero = nuevo;
		}
		this.numeroElementos++;
	}

	public void imprimir() {
		NodoDoble<T> actual = this.primero;
		while (actual != null) {
			System.out.println(actual.getValor());
			actual = actual.getSiguiente();
		}		
	}
	


	public NodoDoble<T> buscar(T buscado) {
		NodoDoble<T> actual = this.primero;
		while (actual != null) {
			if (actual.getValor().compareTo(buscado) == 0) {
				return actual;
			} else {
				actual = actual.getSiguiente();
			}
		}
		return null;
		
	}
	
	public int getNumeroElementos() {
		return numeroElementos;
	}
	
	
	public void eliminar(T valor) {
		if (buscar(valor) != null) {
			if (this.primero != null) {
				if (this.primero.getValor() == valor) {
		            this.primero = this.primero.getSiguiente();
		            this.primero.setAnterior(null);
		            this.numeroElementos--;
				} else {
				NodoDoble<T> actual = this.primero;
				NodoDoble<T> anterior = null;
				while ( actual != null) {
					if (actual.getValor().compareTo(valor) == 0) {
						if (anterior == null) {
                            this.primero = this.primero.getSiguiente();
                            actual.setSiguiente(null);
                            actual= this.primero;
               
						} else {
							anterior.setSiguiente(actual.getSiguiente());
                            actual.setSiguiente(null);
                            actual = anterior.getSiguiente();
						}
					} else {
						anterior = actual;
						actual = actual.getSiguiente();
					}
					

				}
				this.numeroElementos--;
			
				}	
				
			}		
		}		
	}			
	
}