package org.tec.datastructures.general;

import java.util.List;
import java.util.Queue;

import org.tec.datastructures.nodes.NodeDi;

import java.util.PriorityQueue;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;

public class DijkstraGraph<T extends Comparable<T>> {
	public enum State { UNVISITED, VISITED, COMPLETE };

	private ArrayList<NodeDi<T>> vertices;
	private ArrayList<Edge> edges;

	public DijkstraGraph() {
		vertices = new ArrayList<>();
		edges = new ArrayList<>();
	}
	
	public void insert(T from, T to, int cost) {
		Edge temp = findEdge(from, to);
		if (temp != null) {
			temp.cost = cost;
		}
		else {
			Edge edge = new Edge(from, to, cost);
			edges.add(edge);
		}
	}
	
	private NodeDi<T> findNodeDi(T v) {
		for (NodeDi<T> each : vertices) {
			if (each.value.compareTo(v)==0)
				return each;
		}
		return null;
	}
	
	private Edge findEdge(NodeDi<T> v1, NodeDi<T> v2) {
		for (Edge each : edges) {
			if (each.from.equals(v1) && each.to.equals(v2)) {
				return each;
			}
		}
		return null;
	}

	private Edge findEdge(T from, T to) {
		for (Edge each : edges){
			if (each.from.value.equals(from) && each.to.value.equals(to)) {
				return each;
			}
		}
		return null;
	}

	private void clearStates() {
		for (NodeDi<T> each : vertices) {
			each.state = State.UNVISITED;
		}
	}

	public boolean isConnected() {
		for (NodeDi<T> each : vertices){
			if (each.state != State.COMPLETE)
				return false;
		}
		return true;
	}

	public boolean DepthFirstSearch() {
		if (vertices.isEmpty()) return false;
		clearStates();
		NodeDi<T> root = vertices.get(0);
		if (root==null) return false;
		DepthFirstSearch(root);
		return isConnected();
	}


	private void DepthFirstSearch(NodeDi<T> visited){
		visited.state = State.VISITED;
		for (NodeDi<T> each : visited.outgoing) {
			if (each.state ==State.UNVISITED) {
				DepthFirstSearch(each);
			}
		}
		visited.state = State.COMPLETE;
	}

	public boolean BreadthFirstSearch() {
		if (vertices.isEmpty()) return false;
		clearStates();
		NodeDi<T> root = vertices.get(0);
		if (root==null) return false;
		Queue<NodeDi<T>> queue = new LinkedList<>();
		queue.add(root);
		root.state = State.COMPLETE;

		while (!queue.isEmpty()) {
			root = queue.peek();
			for (NodeDi<T> each : root.outgoing) {
				if (each.state==State.UNVISITED) {
					each.state = State.COMPLETE;
					queue.add(each);
				}
			}
			queue.remove();
		}
		return isConnected();
	}

	public boolean BreadthFirstSearch(T visited1) {
		if (vertices.isEmpty()) return false;
		clearStates();
		NodeDi<T> root = findNodeDi(visited1);
		if (root==null) return false;
		Queue<NodeDi<T>> queue = new LinkedList<>();
		queue.add(root);
		root.state = State.COMPLETE;
		while (!queue.isEmpty()) {
			root = queue.peek();
			for (NodeDi<T> each : root.outgoing) {

				if (each.state==State.UNVISITED) {
					each.state = State.COMPLETE;
					queue.add(each);
				}
			}
			queue.remove();
		}
		return isConnected();
	}

	private boolean Dijkstra(T visited1) {
		if (vertices.isEmpty()) return false;
		resetDistances();
		NodeDi<T> source = findNodeDi(visited1);
		if (source==null) return false;
		source.minDistance = 0;
		PriorityQueue<NodeDi<T>> priorety = new PriorityQueue<>();
		priorety.add(source);
		while (!priorety.isEmpty()){
			NodeDi<T> unvisited = priorety.poll();
			for (NodeDi<T> visited : unvisited.outgoing) {
				Edge e = findEdge(unvisited, visited);
				if (e==null) return false;
				int totalDistance = unvisited.minDistance + e.cost;
				if (totalDistance < visited.minDistance) {
					priorety.remove(visited);
					visited.minDistance = totalDistance;
					visited.previous = unvisited;
					priorety.add(visited);
				}
			}
		}
		return true;
	}

	private List<String> getShor(NodeDi<T> target) {
		List<String> path = new ArrayList<String>();
		if (target.minDistance==Integer.MAX_VALUE){
			path.add("No path found");
			return path;
		}
		for (NodeDi<T> v = target; v !=null; v = v.previous){
			path.add(v.value + " : cost : " + v.minDistance);
		}
		Collections.reverse(path);
		return path;
	}

	private void resetDistances() {
		for (NodeDi<T> each : vertices){
			each.minDistance = Integer.MAX_VALUE;
			each.previous = null;
		}
	}

	public List<String> getPath(T from, T to) {
		boolean test = Dijkstra(from);
		if (test==false) return null;
		List<String> path = getShor(findNodeDi(to));
		return path;
	}

	class Edge {
		NodeDi<T> from;
		NodeDi<T> to;
		int cost;
		public Edge(T v1, T v2, int cost) {
			from = findNodeDi(v1);
			if (from == null) {
				from = new NodeDi<T>(v1);
				vertices.add(from);
			}
			to = findNodeDi(v2);
			if (to == null) {
				to = new NodeDi<T>(v2);
				vertices.add(to);
			}
			this.cost = cost;

			from.addOutgoing(to);
			to.addIncoming(from);
		}

	}
}