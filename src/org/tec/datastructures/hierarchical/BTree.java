package org.tec.datastructures.hierarchical;

import java.util.ArrayList;
import java.util.List;

import org.tec.datastructures.nodes.Page;

import java.util.Collection;
import java.util.Collections;

public class BTree<T extends Comparable<T>> {
	private Page<T> root;
	int order;
	
	public BTree(int order) {
		this.order = order;
		root = new Page<T>(null, order);

	}
	
	public boolean isEmpty() {
		return this.root == null;
		
	}
	
	public Page<T> search(T key) {
		return search(root, key);
	}
	
	private Page<T> search(Page<T> root, T key) {
		int index = 0;
		while( index < root.count && key.compareTo(root.keys.get(index)) > 0) index++;
		if(index <= root.count && key == root.keys.get(index)) return root;
		// QUITE LO DE LA HOJA
		else {
			return search(root.getChild(index), key);
		}
	}
	
	public void insert(T key) {
		this.root = insert(root, key);
	}
	
	private Page<T> insert(Page<T> root, T value) {
		if (root.keys.isEmpty()) {
			root = new Page<T>(null, order);
			root.addKey(value);
		} else {
			if (root.count < root.maxKeys) {
					root.addKey(value);
			} else {
				
			}
			
		}
		return root;
		
	}
	
	
}

	
//	import org.tec.datastructures.nodes.BTreePage;
//
//	public class BTree<T extends Comparable<T>> {
//		
//		private BTreePage<T> root;
//		
//		public BTree(Integer size) {
//			BTreePage<T> Root = new BTreePage<>();
//			Root.setSize(3);
//			setRoot(Root);
//		}
//		
//		public BTree(BTreePage<T> Root) {
//			setRoot(Root);
//		}
//
//		public BTreePage<T> getRoot() {
//			return root;
//		}
//
//		public void setRoot(BTreePage<T> root) {
//			this.root = root;
//		}
//		
//		public void append(T value) {
//			if (root == null) {
//				return;
//			}
//			else if (root.getSize() == -1){
//				System.err.println("Must Set Tree's Size");
//				return;
//			}
//			appendRecursive(value,root);
//		}
//		
//		private void appendRecursive(T value, BTreePage<T> page){
//			Integer pos = 0;
//			for( T key : page.getKeys()) {
//				if (value.compareTo(key) < 0) {
//					break;
//				}
//				pos++;
//			}
//			if (page.isLeaf()) {
//				page.addKey(value);
//			}
//			else {
//				appendRecursive(value, page.getBranches().get(pos));
//			}
//			
//			balance(page);
//			
//		}
//
//		private void balance(BTreePage<T> page) {
//			if (page.getKeys().size() > page.getSize()-1) {
//				Double pos = Math.ceil((page.getSize()-1)/2);
//				T movingElement = page.getKeys().get(pos.intValue());
//				
//				if (page.getParent() == null) {
//					BTreePage<T> newParent = new BTreePage<>(root.getSize());
//					setRoot(newParent);
//					newParent.addKey(movingElement);
//					newParent.setBranches(page.split(pos.intValue(),newParent));
//				}
//				else {
//					BTreePage<T> parent = page.getParent();
//					Integer index = parent.addKey(movingElement);
//					parent.replaceBranch(page,pos.intValue(), index);
//				}
//			}
//		}
//		
//		public T search(T value) {
//			if (root == null){
//				return null;
//			}
//			return searchRecursive(value, root);
//		}
//		
//		private T searchRecursive(T value, BTreePage<T> page) {
//			Integer pos = 0;
//			for( T key : page.getKeys()) {
//				if (value.compareTo(key) == 0) {
//					return key;
//				}
//				else if (value.compareTo(key) < 0) {
//					break;
//				}
//				pos++;
//			}
//			if (page.isLeaf()) {
//				return null;
//			}
//			else {
//				return searchRecursive(value, page.getBranches().get(pos));
//			}
//			
//		}
//		
//		public void delete(T value) {
//			if (root == null) {
//				return;
//			}
//			deleteRecursive(value,root);
//		}
//
//		private void deleteRecursive(T value, BTreePage<T> page) {}
//	  
//	}
//	 
//}