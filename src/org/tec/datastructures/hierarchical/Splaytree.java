package org.tec.datastructures.hierarchical;

import org.tec.datastructures.nodes.SplayNodo;

public class Splaytree <Key extends Comparable<Key>, Value> {
	public  SplayNodo<Key, Value> root;   
	public static int cont=0;

    public boolean contains(Key key) {
        return get(key) != null;
    }

   
    public Value get(Key key) {
        root = splay(root, key);
        int cmp = key.compareTo(root.key);
        if (cmp == 0) return root.value;
        else          return null;
    }    


    public void put(Key key, Value value) {
        // Pasa el key a la raiz
        if (root == null) {
            root = new SplayNodo<Key, Value>(key, value);
            return;
        }
        
        root = splay(root, key);

        int cmp = key.compareTo(root.key);
        
        // Inserta nuevo nodo a la raiz
        if (cmp < 0) {
            SplayNodo<Key, Value> n = new SplayNodo<Key, Value>(key, value);
            n.left = root.left;
            n.right = root;
            root.left = null;
            root = n;
        }

        // Inserta nuevo nodo a la raiz
        else if (cmp > 0) {
            SplayNodo<Key, Value> n = new SplayNodo<Key, Value>(key, value);
            n.right = root.right;
            n.left = root;
            root.right = null;
            root = n;
        }

        // Remplaza el valor de key
        else if (cmp == 0) {
            root.value = value;
        }

    }
    
  
    public void remove(Key key) {
        if (root == null) return; // arbol vacio
        
        root = splay(root, key);

        int cmp = key.compareTo(root.key);
        
        if (cmp == 0) {
            if (root.left == null) {
                root = root.right;
            } 
            else {
                SplayNodo<Key, Value> x = root.right;
                root = root.left;
                splay(root, key);
                root.right = x;
                
            }
        }

    }
    
    
    private SplayNodo<Key, Value> splay(SplayNodo<Key, Value> h, Key key) {
        if (h == null) return null;

        int cmp1 = key.compareTo(h.key);

        if (cmp1 < 0) {
            if (h.left == null) {
                return h;
            }
            int cmp2 = key.compareTo(h.left.key);
            if (cmp2 < 0) {
                h.left.left = splay(h.left.left, key);
                h = rotateRight(h);
            }
            else if (cmp2 > 0) {
                h.left.right = splay(h.left.right, key);
                if (h.left.right != null)
                    h.left = rotateLeft(h.left);
            }
            
            if (h.left == null) return h;
            else                return rotateRight(h);
        }

        else if (cmp1 > 0) { 
            if (h.right == null) {
                return h;
            }

            int cmp2 = key.compareTo(h.right.key);
            if (cmp2 < 0) {
                h.right.left  = splay(h.right.left, key);
                if (h.right.left != null)
                    h.right = rotateRight(h.right);
            }
            else if (cmp2 > 0) {
                h.right.right = splay(h.right.right, key);
                h = rotateLeft(h);
            }
            
            if (h.right == null) return h;
            else                 return rotateLeft(h);
        }

        else return h;
    }


    public int height() { return height(root); }
    private int height(SplayNodo<Key, Value> x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    
    public int size() {
        return size(root);
    }
    
    private int size(SplayNodo<Key, Value> x) {
        if (x == null) return 0;
        else return 1 + size(x.left) + size(x.right);
    }
    
    // Rotacion derecha
    private SplayNodo<Key, Value> rotateRight(SplayNodo<Key, Value> h) {
        SplayNodo<Key, Value> x = h.left;
        h.left = x.right;
        x.right = h;
        return x;
    }

    // Rotacion izquierda
    private SplayNodo<Key, Value> rotateLeft(SplayNodo<Key, Value> h) {
        SplayNodo<Key, Value> x = h.right;
        h.right = x.left;
        x.left = h;
        return x;
    }
}