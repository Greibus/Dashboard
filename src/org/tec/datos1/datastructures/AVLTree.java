package org.tec.datos1.datastructures;

public class AVLTree<T extends Comparable<T>> {
	private AVLNodo<T> root;
	

	public AVLTree() {
		root = null;
	}
	

	public AVLNodo<T> obtenerroot() {
		return root;
	}

	// Buscar Nodo
	public AVLNodo<T> buscar(T d, AVLNodo<T> r) {
		if (root == null) {
			return null;
		} else if (r.dato == d) {
			return r;
		} else if (r.dato.compareTo(d) < 0) {
			return buscar(d, r.rigth);
		} else {
			return buscar(d, r.left);
		}
	}

	// Obtener el factor de equilibrio
	public int obtenerheight(AVLNodo<T> x) {
		if (x == null) {
			return -1;
		} else {
			return x.height;
		}
	}

	// Rotacion simple a la izquierda
	public AVLNodo<T> rotacionIzquierda(AVLNodo<T> c) {
		AVLNodo<T> auxiliar = c.left;
		c.left = auxiliar.rigth;
		auxiliar.rigth = c;
		c.height = Math.max(obtenerheight(c.left), obtenerheight(c.rigth)) + 1;
		auxiliar.height = Math.max(obtenerheight(auxiliar.left), obtenerheight(auxiliar.rigth)) + 1;
		return auxiliar;
	}

	// Rotacion simple derecha
	public AVLNodo<T> rotacionDerecha(AVLNodo<T> c) {
		AVLNodo<T> auxiliar = c.rigth;
		c.rigth = auxiliar.left;
		auxiliar.left = c;
		c.height = Math.max(obtenerheight(c.left), obtenerheight(c.rigth)) + 1;
		auxiliar.height = Math.max(obtenerheight(auxiliar.left), obtenerheight(auxiliar.rigth)) + 1;
		return auxiliar;
	}

	// Rotacion Doble a la derecha
	public AVLNodo<T> rotacionDobleIzquierda(AVLNodo<T> c) {
		AVLNodo<T> temp;
		c.left = rotacionDerecha(c.left);
		temp = rotacionIzquierda(c);
		return temp;
	}

	// Rotacion Doble a la derecha
	public AVLNodo<T> rotacionDobleDerecha(AVLNodo<T> c) {
		AVLNodo<T> temp;
		c.rigth = rotacionIzquierda(c.rigth);
		temp = rotacionDerecha(c);
		return temp;
	}

	// Metodo Insertar Balanceado
	public AVLNodo<T> insertarAVL(AVLNodo<T> nuevo, AVLNodo<T> subAr) {
		AVLNodo<T> nuevoPadre = subAr;
		if (nuevo.dato.compareTo(subAr.dato) < 0) {
			if (subAr.left == null) {
				subAr.left = nuevo;
			} else {
				subAr.left = insertarAVL(nuevo, subAr.left);
				if ((obtenerheight(subAr.left) - obtenerheight(subAr.rigth)) == 2) {
					if (nuevo.dato.compareTo(subAr.left.dato) < 0) {
						nuevoPadre = rotacionIzquierda(subAr);
					} else {
						nuevoPadre = rotacionDobleIzquierda(subAr);
					}
				}
			}
		} else if (nuevo.dato.compareTo(subAr.dato) > 0) {
			if (subAr.rigth == null) {
				subAr.rigth = nuevo;
			} else {
				subAr.rigth = insertarAVL(nuevo, subAr.rigth);
				if ((obtenerheight(subAr.rigth) - obtenerheight(subAr.left)) == 2) {
					if (nuevo.dato.compareTo(subAr.rigth.dato) > 0) {
						nuevoPadre = rotacionDerecha(subAr);
					} else {
						nuevoPadre = rotacionDobleDerecha(subAr);
					}
				}
			}
		} else {
			if(subAr.rigth!=null){
				insertarAVL(nuevo,subAr.rigth);
			}
			else{
				subAr.rigth=nuevo;
			}
		}
		// Actualizar altura o factor de equilibrio
		if (subAr.left == null && subAr.rigth != null) {
			subAr.height = subAr.rigth.height + 1;
		} else if (subAr.rigth == null && subAr.left != null) {
			subAr.height = subAr.left.height + 1;
		} else {
			subAr.height = Math.max(obtenerheight(subAr.left), obtenerheight(subAr.rigth)) + 1;
		}
		return nuevoPadre;
	}

	// Metodo para Insertar SImple
	public void insertar(T dato) {
		AVLNodo<T> nuevo = new AVLNodo<T>(dato);
		if (root == null) {
			root = nuevo;
		} else {
			root = insertarAVL(nuevo, root);
		}
	}

	
	// Buscar Menor
	public AVLNodo<T> findMin(AVLNodo<T> t) {
		if (t == null)
			return null;
		else if (t.left == null)
			return t;
		return findMin(t.left);
	}

	// Balancear
	public AVLNodo<T> balancear(AVLNodo<T> t) {
		if (t == null)
			return t;
		if (obtenerheight(t.left) - obtenerheight(t.rigth) > 1)
			if (obtenerheight(t.left.left) >= obtenerheight(t.left.rigth))
				t = rotacionIzquierda(t);
			else
				t = rotacionDobleIzquierda(t);
		else if (obtenerheight(t.rigth) - obtenerheight(t.left) > 1)
			if (obtenerheight(t.rigth.rigth) >= obtenerheight(t.rigth.left))
				t = rotacionDerecha(t);
			else
				t = rotacionDobleDerecha(t);
		t.height = Math.max(obtenerheight(t.left), obtenerheight(t.rigth)) + 1;
		return t;
	}

	// Eliminar
	public AVLNodo<T> eliminarAVL(T x, AVLNodo<T> t) {
		StringBuilder sb = new StringBuilder();
		StringBuilder sb1 = new StringBuilder();
		sb.append("");
		sb1.append("");
		sb.append(x);
		sb1.append(t.dato);
		String var = sb.toString();
		String var1 = sb1.toString();
		if (t == null)
			return t;
		int compareResult = var.compareTo(var1);
		if (compareResult < 0)
			t.left = eliminarAVL(x, t.left);
		else if (compareResult > 0)
			t.rigth = eliminarAVL(x, t.rigth);
		else if (t.left != null && t.rigth != null)

		{
			t.dato = findMin(t.rigth).dato;
			t.rigth = eliminarAVL(t.dato, t.rigth);
		} else
			t = (t.left != null) ? t.left : t.rigth;
		return balancear(t);
	}


}
