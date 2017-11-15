package org.tec.datastructures.nodes;

import java.util.ArrayList;
import java.util.List;

import org.tec.datastructures.general.DijkstraGraph.State;

public class NodeDi<T extends Comparable<T>> implements Comparable<NodeDi<T>>{
	public T value;
	public NodeDi<T> previous = null;
	public int minDistance = Integer.MAX_VALUE;

	public List<NodeDi<T>> incoming;
	public List<NodeDi<T>> outgoing;
	public State state;

	public NodeDi(T value) {
		this.value = value;
		incoming = new ArrayList<>();
		outgoing = new ArrayList<>();
		state = State.UNVISITED;
	}

	public int compareTo(NodeDi<T> other) {
		return Integer.compare(minDistance, other.minDistance);
	}

	public void addIncoming(NodeDi<T> vert) {
		incoming.add(vert);
	}
	public void addOutgoing(NodeDi<T> vert) {
		outgoing.add(vert);
	}
	
	@Override
	public String toString() {
		String retval = "";
		retval += "NodeDi<T>: " + value + " : ";
		retval += " In: ";
		for (NodeDi<T> each : incoming) retval+= each.value + " ";
		retval += "Out: ";
		for (NodeDi<T> each : outgoing) retval += each.value + " ";
		return retval;
	}
}
