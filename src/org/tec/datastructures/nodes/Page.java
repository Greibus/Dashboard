package org.tec.datastructures.nodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Page<T extends Comparable<T>> {
	public List<T> keys;
	public int maxChildren;
	public int minChildren;
	public int maxKeys;
	public int minKeys;
	public int count;
	public boolean leaf;
	public List<Page<T>> children;
	public Page<T> parent;
	
	
	public Page(Page<T> parent, int maxChildren) {
		this.maxChildren = maxChildren;
		this.maxKeys = maxChildren - 1;
		this.minKeys = (maxChildren - 1) / 2;
		this.minChildren = ((maxChildren - 1) / 2) + 1;
		this.keys = new ArrayList<T>(maxKeys + 1);
		this.children = new ArrayList<Page<T>>(maxChildren + 1);
	}
	
	public T getValue(int index) {
		return keys.get(index);
	}
	
	public Page<T> getChild(int index) {
		return children.get(index);
	}

	
	public void addKey(T value) {
		if (this.count < maxKeys) {
			keys.add(value);
			this.count++;
			Collections.sort(keys);
		}
 
	 
	}
}
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class BTreePage <T extends Comparable<T>>{
//	
//	private List<T> keys;
//	private List<BTreePage<T>> branches;
//	private BTreePage<T> parent;
//	private Integer size = -1; 
//	
//	public BTreePage() {
//		this(new ArrayList<>());
//	}
//	
//	public BTreePage(Integer size) {
//		this(new ArrayList<>());
//		setSize(size);
//	}
//	
//	public BTreePage(List<T> Keys) {
//		keys = new ArrayList<>(Keys);
//		branches = new ArrayList<>();
//		parent = null;
//	}
//
//	public List<T> getKeys() {
//		return keys;
//	}
//
//	public void setKeys(List<T> keys) {
//		this.keys = keys;
//	}
//
//	public List<BTreePage<T>> getBranches() {
//		return branches;
//	}
//
//	public void setBranches(List<BTreePage<T>> branches) {
//		this.branches = branches;
//	}
//
//	public BTreePage<T> getParent() {
//		return parent;
//	}
//
//	public void setParent(BTreePage<T> Parent) {
//		parent = Parent;
//	}
//
//	public Integer getSize() {
//		return size;
//	}
//
//	public void setSize(Integer Size) {
//		size = Size;
//	}
//	
//	public boolean isLeaf() {
//		return (branches.size() == 0);
//	}
//
//	public int addKey(T value) {
//		int index = 0;
//		for (T key : keys) {
//			if (key.compareTo(value) > 0) {
//				break;
//			}
//			index++;
//		}
//		keys.add(index, value);
//		return index;
//	}
//
//	public void removeKey(T key) {
//		keys.remove(key);
//		
//	}
//
//	public List<BTreePage<T>> split(Integer separator, BTreePage<T> parent) {
//		int pageSize = this.getSize();
//		
//		BTreePage<T> leftBranch = new BTreePage<T>(keys.subList(0, separator));
//		leftBranch.setSize(pageSize);
//		leftBranch.setParent(parent);
//		
//		BTreePage<T> rightBranch = new BTreePage<T>(keys.subList(separator+1,keys.size()));
//		rightBranch.setSize(pageSize);
//		rightBranch.setParent(parent);
//		
//		List<BTreePage<T>> branches = new ArrayList<>();
//		branches.add(leftBranch);
//		branches.add(rightBranch);
//		return branches;
//	}
//
//	public void replaceBranch(BTreePage<T> Page, Integer separator, Integer index) {
//		List<BTreePage<T>> Branches = Page.split(separator,this);
//		branches.add(index, Branches.get(0));
//		branches.add(index+1, Branches.get(1));
//		branches.remove(Page);
//	}
//	
//}