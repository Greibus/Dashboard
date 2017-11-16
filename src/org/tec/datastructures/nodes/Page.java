package org.tec.datastructures.nodes;

import java.util.Arrays;
import java.util.Comparator;

public class Page<T extends Comparable<T>> {

    public T[] keys;
    public int keysSize;
    public Page<T>[] children;
    public int childrenSize;
    
    public Comparator<Page<T>> equals = new Comparator<Page<T>>() {
        @Override
        public int compare(Page<T> arg0, Page<T> arg1) {
            return arg0.getKey(0).compareTo(arg1.getKey(0));
        }
    };

    public Page<T> parent = null;

    @SuppressWarnings("unchecked")
	public Page(Page<T> parent, int maxKeySize, int maxChildrenSize) {
        this.parent = parent;
        this.keys = (T[]) new Comparable[maxKeySize + 1];
        this.keysSize = 0;
        this.children = new Page[maxChildrenSize + 1];
        this.childrenSize = 0;
    }

    public T getKey(int index) {
        return keys[index];
    }

    public int indeKey(T value) {
        for (int i = 0; i < keysSize; i++) {
            if (keys[i].equals(value)) return i;
        }
        return -1;
    }
    
    public boolean appendChild(Page<T> child) {
        child.parent = this;
        children[childrenSize++] = child;
        Arrays.sort(children, 0, childrenSize, equals);
        return true;
    }

    public boolean deleteChild(Page<T> child) {
        boolean found = false;
        if (childrenSize == 0)
            return found;
        for (int i = 0; i < childrenSize; i++) {
            if (children[i].equals(child)) {
                found = true;
            } else if (found) {
                children[i - 1] = children[i];
            }
        }
        if (found) {
            childrenSize--;
            children[childrenSize] = null;
        }
        return found;
    }

    public Page<T> deleteChild(int index) {
        if (index >= childrenSize)
            return null;
        Page<T> value = children[index];
        children[index] = null;
        for (int i = index + 1; i < childrenSize; i++) {
            children[i - 1] = children[i];
        }
        childrenSize--;
        children[childrenSize] = null;
        return value;
    }

    public int SizeChildren() {
        return childrenSize;
    }
    
    public void appendKey(T value) {
        keys[keysSize++] = value;
        Arrays.sort(keys, 0, keysSize);
    }

    public T deleteKey(T value) {
        T removed = null;
        boolean found = false;
        if (keysSize == 0) return null;
        for (int i = 0; i < keysSize; i++) {
            if (keys[i].equals(value)) {
                found = true;
                removed = keys[i];
            } else if (found) {
                keys[i - 1] = keys[i];
            }
        }
        if (found) {
            keysSize--;
            keys[keysSize] = null;
        }
        return removed;
    }

    public T deleteKey(int index) {
        if (index >= keysSize)
            return null;
        T value = keys[index];
        for (int i = index + 1; i < keysSize; i++) {
            keys[i - 1] = keys[i];
        }
        keysSize--;
        keys[keysSize] = null;
        return value;
    }

    public int SizeKeys() {
        return keysSize;
    }

    public Page<T> getChild(int index) {
        if (index >= childrenSize)
            return null;
        return children[index];
    }

    public int indexChild(Page<T> child) {
        for (int i = 0; i < childrenSize; i++) {
            if (children[i].equals(child))
                return i;
        }
        return -1;
    }


}
