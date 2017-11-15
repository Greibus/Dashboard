package org.tec.datastructures.nodes;

public class Arc {
    public final String origin;
    public final int originIndex;
    public final String destination;
    public final int destinationIndex;
    public final double length;

    public Arc(String origin, int originIndex,
               String destination, int destinationIndex,
               double length) {
        this.origin = origin;
        this.originIndex = originIndex;

        this.destination = destination;
        this.destinationIndex = destinationIndex;

        this.length = length;
    }
}