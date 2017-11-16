package org.tec.datastructures.hierarchical;

import org.tec.datastructures.nodes.Page;

 

public class BTree<T extends Comparable<T>> {
    private int minKeySize = 1;
    private int minChildrenSize = minKeySize + 1;
    private int maxKeySize = 2 * minKeySize; 
    private int maxChildrenSize = maxKeySize + 1; 
    private Page<T> root = null;
    private int size = 0;

    public BTree() { 
    	
    }

    public BTree(int order) {
        this.minKeySize = order;
        this.minChildrenSize = minKeySize + 1;
        this.maxKeySize = 2 * minKeySize;
        this.maxChildrenSize = maxKeySize + 1;
    }
    
    public Page<T> getRoot() {
		return root;
	}

	public void setRoot(Page<T> root) {
		this.root = root;
	}


    public boolean append(T value) {
        if (root == null) {
            root = new Page<T>(null, maxKeySize, maxChildrenSize);
            root.appendKey(value);
        } else {
            Page<T> Page = root;
            while (Page != null) {
                if (Page.SizeChildren() == 0) {
                    Page.appendKey(value);
                    if (Page.SizeKeys() <= maxKeySize) {
                        break;
                    }                         
                    split(Page);
                    break;
                }
                T lesser = Page.getKey(0);
                if (value.compareTo(lesser) <= 0) {
                    Page = Page.getChild(0);
                    continue;
                }

                int SizeKeys = Page.SizeKeys();
                int last = SizeKeys - 1;
                T greater = Page.getKey(last);
                if (value.compareTo(greater) > 0) {
                    Page = Page.getChild(SizeKeys);
                    continue;
                }

                for (int i = 1; i < Page.SizeKeys(); i++) {
                    T prev = Page.getKey(i - 1);
                    T next = Page.getKey(i);
                    if (value.compareTo(prev) > 0 && value.compareTo(next) <= 0) {
                        Page = Page.getChild(i);
                        break;
                    }
                }
            }
        }

        size++;

        return true;
    }

    private void split(Page<T> PageToSplit) {
        Page<T> Page = PageToSplit;
        int SizeKeys = Page.SizeKeys();
        int medianIndex = SizeKeys / 2;
        T medianValue = Page.getKey(medianIndex);

        Page<T> left = new Page<T>(null, maxKeySize, maxChildrenSize);
        for (int i = 0; i < medianIndex; i++) {
            left.appendKey(Page.getKey(i));
        }
        if (Page.SizeChildren() > 0) {
            for (int j = 0; j <= medianIndex; j++) {
                Page<T> c = Page.getChild(j);
                left.appendChild(c);
            }
        }

        Page<T> right = new Page<T>(null, maxKeySize, maxChildrenSize);
        for (int i = medianIndex + 1; i < SizeKeys; i++) {
            right.appendKey(Page.getKey(i));
        }
        if (Page.SizeChildren() > 0) {
            for (int j = medianIndex + 1; j < Page.SizeChildren(); j++) {
                Page<T> c = Page.getChild(j);
                right.appendChild(c);
            }
        }

        if (Page.parent == null) {
            Page<T> newRoot = new Page<T>(null, maxKeySize, maxChildrenSize);
            newRoot.appendKey(medianValue);
            Page.parent = newRoot;
            root = newRoot;
            Page = root;
            Page.appendChild(left);
            Page.appendChild(right);
        } else {
            Page<T> parent = Page.parent;
            parent.appendKey(medianValue);
            parent.deleteChild(Page);
            parent.appendChild(left);
            parent.appendChild(right);
            if (parent.SizeKeys() > maxKeySize) split(parent);
        }
    }

    public T remove(T value) {
        T removed = null;
        Page<T> Page = this.getPage(value);
        removed = remove(value,Page);
        return removed;
    }
        
    private T removeGreatestValue(Page<T> Page) {
        T value = null;
        if (Page.SizeKeys() > 0) {
            value = Page.deleteKey(Page.SizeKeys() - 1);
        }
        return value;
    }
    private boolean validatePage(Page<T> Page) {
        int keySize = Page.SizeKeys();
        if (keySize > 1) {
            for (int i = 1; i < keySize; i++) {
                T p = Page.getKey(i - 1);
                T n = Page.getKey(i);
                if (p.compareTo(n) > 0)
                	return false;
            }
        }
        int childrenSize = Page.SizeChildren();
        if (Page.parent == null) {
            if (keySize > maxKeySize) {
                return false;
            } else if (childrenSize == 0) {
                return true;
            } else if (childrenSize < 2) {
                return false;
            } else if (childrenSize > maxChildrenSize) {
                return false;
            }
        } else {
            if (keySize < minKeySize) {
                return false;
            } else if (keySize > maxKeySize) {
                return false;
            } else if (childrenSize == 0) {
                return true;
            } else if (keySize != (childrenSize - 1)) {
                return false;
            } else if (childrenSize < minChildrenSize) {
                return false;
            } else if (childrenSize > maxChildrenSize) {
                return false;
            }
        }
        Page<T> first = Page.getChild(0);
        if (first.getKey(first.SizeKeys() - 1).compareTo(Page.getKey(0)) > 0)
            return false;
        Page<T> last = Page.getChild(Page.SizeChildren() - 1);
        if (last.getKey(0).compareTo(Page.getKey(Page.SizeKeys() - 1)) < 0)
            return false;
       for (int i = 1; i < Page.SizeKeys(); i++) {
            T p = Page.getKey(i - 1);
            T n = Page.getKey(i);
            Page<T> c = Page.getChild(i);
            if (p.compareTo(c.getKey(0)) > 0)
                return false;
            if (n.compareTo(c.getKey(c.SizeKeys() - 1)) < 0)
                return false;
        }
        for (int i = 0; i < Page.childrenSize; i++) {
            Page<T> c = Page.getChild(i);
            boolean valid = this.validatePage(c);
            if (!valid)
                return false;
        }
        return true;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public boolean contains(T value) {
        Page<T> Page = getPage(value);
        return (Page != null);
    }
    
    private T remove(T value, Page<T> Page) {
        if (Page == null) return null;
        T removed = null;
        int index = Page.indeKey(value);
        removed = Page.deleteKey(value);
        if (Page.SizeChildren() == 0) {
            if (Page.parent != null && Page.SizeKeys() < minKeySize) {
                this.combined(Page);
            } else if (Page.parent == null && Page.SizeKeys() == 0) {
                root = null;
            }
        } else {
            Page<T> lesser = Page.getChild(index);
            Page<T> greatest = this.getGreatestPage(lesser);
            T replaceValue = this.removeGreatestValue(greatest);
            Page.appendKey(replaceValue);
            if (greatest.parent != null && greatest.SizeKeys() < minKeySize) {
                this.combined(greatest);
            }
            if (greatest.SizeChildren() > maxChildrenSize) {
                this.split(greatest);
            }
        }

        size--;

        return removed;
    }


    private Page<T> getPage(T value) {
        Page<T> Page = root;
        while (Page != null) {
            T lesser = Page.getKey(0);
            if (value.compareTo(lesser) < 0) {
                if (Page.SizeChildren() > 0)
                    Page = Page.getChild(0);
                else
                    Page = null;
                continue;
            }
            int SizeKeys = Page.SizeKeys();
            int last = SizeKeys - 1;
            T greater = Page.getKey(last);
            if (value.compareTo(greater) > 0) {
                if (Page.SizeChildren() > SizeKeys)
                    Page = Page.getChild(SizeKeys);
                else
                    Page = null;
                continue;
            }
            for (int i = 0; i < SizeKeys; i++) {
                T currentValue = Page.getKey(i);
                if (currentValue.compareTo(value) == 0) {
                    return Page;
                }
                int next = i + 1;
                if (next <= last) {
                    T nextValue = Page.getKey(next);
                    if (currentValue.compareTo(value) < 0 && nextValue.compareTo(value) > 0) {
                        if (next < Page.SizeChildren()) {
                            Page = Page.getChild(next);
                            break;
                        }
                        return null;
                    }
                }
            }
        }
        return null;
    }

    private boolean combined(Page<T> Page) {
        Page<T> parent = Page.parent;
        int index = parent.indexChild(Page);
        int indexOfLeftNeighbor = index - 1;
        int indexOfRightNeighbor = index + 1;
        Page<T> rightNeighbor = null;
        int rightNeighborSize = -minChildrenSize;
        if (indexOfRightNeighbor < parent.SizeChildren()) {
            rightNeighbor = parent.getChild(indexOfRightNeighbor);
            rightNeighborSize = rightNeighbor.SizeKeys();
        } 
        if (rightNeighbor != null && rightNeighborSize > minKeySize) {
            T removeValue = rightNeighbor.getKey(0);
            int prev = getIndexOfPreviousValue(parent, removeValue);
            T parentValue = parent.deleteKey(prev);
            T neighborValue = rightNeighbor.deleteKey(0);
            Page.appendKey(parentValue);
            parent.appendKey(neighborValue);
            if (rightNeighbor.SizeChildren() > 0) {
                Page.appendChild(rightNeighbor.deleteChild(0));
            }
        } else {
            Page<T> leftNeighbor = null;
            int leftNeighborSize = -minChildrenSize;
            if (indexOfLeftNeighbor >= 0) {
                leftNeighbor = parent.getChild(indexOfLeftNeighbor);
                leftNeighborSize = leftNeighbor.SizeKeys();
            }
            if (leftNeighbor != null && leftNeighborSize > minKeySize) {
                T removeValue = leftNeighbor.getKey(leftNeighbor.SizeKeys() - 1);
                int prev = getIndexOfNextValue(parent, removeValue);
                T parentValue = parent.deleteKey(prev);
                T neighborValue = leftNeighbor.deleteKey(leftNeighbor.SizeKeys() - 1);
                Page.appendKey(parentValue);
                parent.appendKey(neighborValue);
                if (leftNeighbor.SizeChildren() > 0) {
                    Page.appendChild(leftNeighbor.deleteChild(leftNeighbor.SizeChildren() - 1));
                }
            } else if (rightNeighbor != null && parent.SizeKeys() > 0) {
                T removeValue = rightNeighbor.getKey(0);
                int prev = getIndexOfPreviousValue(parent, removeValue);
                T parentValue = parent.deleteKey(prev);
                parent.deleteChild(rightNeighbor);
                Page.appendKey(parentValue);
                for (int i = 0; i < rightNeighbor.keysSize; i++) {
                    T v = rightNeighbor.getKey(i);
                    Page.appendKey(v);
                }
                for (int i = 0; i < rightNeighbor.childrenSize; i++) {
                    Page<T> c = rightNeighbor.getChild(i);
                    Page.appendChild(c);
                }

                if (parent.parent != null && parent.SizeKeys() < minKeySize) {
                    this.combined(parent);
                } else if (parent.SizeKeys() == 0) {
                    Page.parent = null;
                    root = Page;
                }
            } else if (leftNeighbor != null && parent.SizeKeys() > 0) {
                T removeValue = leftNeighbor.getKey(leftNeighbor.SizeKeys() - 1);
                int prev = getIndexOfNextValue(parent, removeValue);
                T parentValue = parent.deleteKey(prev);
                parent.deleteChild(leftNeighbor);
                Page.appendKey(parentValue);
                for (int i = 0; i < leftNeighbor.keysSize; i++) {
                    T v = leftNeighbor.getKey(i);
                    Page.appendKey(v);
                }
                for (int i = 0; i < leftNeighbor.childrenSize; i++) {
                    Page<T> c = leftNeighbor.getChild(i);
                    Page.appendChild(c);
                }
                if (parent.parent != null && parent.SizeKeys() < minKeySize) {
                    this.combined(parent);
                } else if (parent.SizeKeys() == 0) {
                  Page.parent = null;
                    root = Page;
                }
            }
        }

        return true;
    }

    private int getIndexOfPreviousValue(Page<T> Page, T value) {
        for (int i = 1; i < Page.SizeKeys(); i++) {
            T t = Page.getKey(i);
            if (t.compareTo(value) >= 0)
                return i - 1;
        }
        return Page.SizeKeys() - 1;
    }
    
    private Page<T> getGreatestPage(Page<T> PageToGet) {
        Page<T> Page = PageToGet;
        while (Page.SizeChildren() > 0) {
            Page = Page.getChild(Page.SizeChildren() - 1);
        }
        return Page;
    }

    private int getIndexOfNextValue(Page<T> Page, T value) {
        for (int i = 0; i < Page.SizeKeys(); i++) {
            T t = Page.getKey(i);
            if (t.compareTo(value) >= 0)
                return i;
        }
        return Page.SizeKeys() - 1;
    }

    public int size() {
        return size;
    }

    public boolean validate() {
        if (root == null) return true;
        return validatePage(root);
    }    
}