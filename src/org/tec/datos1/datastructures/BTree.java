package org.tec.datos1.datastructures;

public class BTree<T extends Comparable<T>> {
	private BNodo<T> root;
	
	public BTree() {
		this.root = null;
	}
	
	public boolean contains(T element) {
		return this.contains(element, root);
	}
	
	private boolean contains(T element, BNodo<T> current) {
		if (current == null) {
			return false;
		} else {
			if (element.compareTo(current.element) < 0) {
				return contains(element,current.left);
			} else if (element.compareTo(current.element) > 0) {
				return contains(element, current.rigth);
			} else {
				return true;
			}
		}
	}
	
	public BNodo<T> findmin() {
		if (this.root == null) {
			return null;
		} else {
			return findmin(this.root);
		}
	}
	
	private BNodo<T> findmin(BNodo<T> nodo) {
		if (nodo == null) {
			return null;
		} else if (nodo.left == null ) {
			return nodo;
		} else {
			return findmin(nodo.left);
		}
	}
	
	public BNodo<T> findmax() {
		if (this.root == null) {
			return null;
		} else {
			return findmax(this.root);
		}
	}
	
	private BNodo<T> findmax(BNodo<T> nodo) {
		if (nodo == null) {
			return null;
		} else if (nodo.rigth == null) {
			return nodo;
		} else {
			return findmax(nodo.rigth);
		}
	}
	
	public void insert(T element) {
		this.root = this.insert(element, this.root);
	}
	
	private BNodo<T> insert(T element, BNodo<T> current) {
		if (current == null) 
			return new BNodo<T>(element, null, null);
		if (element.compareTo(current.element) < 0) {
			current.left = this.insert(element, current.left);
		} else if( element.compareTo(current.element) > 0) {
			current.rigth = this.insert(element, current.rigth);
		}
		return current;
	}
	
	public void remove(T element) {
		this.root = this.remove(element, this.root);
	}
	
	private BNodo<T> remove(T element, BNodo<T> nodo) {
		if (nodo == null) return nodo;
		if (element.compareTo(nodo.element) < 0) {
			nodo.left = remove(element, nodo.left);
		} else if (element.compareTo(nodo.element) > 0) {
			nodo.rigth = remove(element, nodo.rigth);
		} else {
			if (nodo.left != null && nodo.rigth != null) {
				BNodo<T> nodoMin;
				nodoMin = findmin(nodo.rigth);
				nodo.element = nodoMin.element;
				nodo.rigth = remove(nodo.element, nodo.rigth);
			} else {
				nodo = nodo.left != null ? nodo.left : nodo.rigth;
			}
		}
		return nodo;
	}
	
	public boolean isEmpty() {
		return this.root == null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
