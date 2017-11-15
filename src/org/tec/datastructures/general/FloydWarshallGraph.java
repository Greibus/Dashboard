package org.tec.datastructures.general;

import java.util.Map;

import org.tec.datastructures.nodes.Arc;


public class FloydWarshallGraph {
    private final Map<String, Integer> idToIndex;
    private final Arc[] arcs;

    public FloydWarshallGraph(Map<String, Integer> idToIndex, Arc[] arcs) {
        this.idToIndex = idToIndex;
        this.arcs = arcs;
    }

    public Integer lookupId(String id) {
        return idToIndex.getOrDefault(id, null);
    }

    public FloydWarshallOutput floydWarshall() {
        final int size = idToIndex.size();
        final int elementCount = size * size;
        long startTimeMs = System.currentTimeMillis();
        System.out.printf("Calculating Floyd - Warshall with %,d elements in bestDistances array\n", elementCount);

        final String[] indexToId = new String[size];
        for (String id : idToIndex.keySet()) {
            int index = idToIndex.get(id);
            indexToId[index] = id;
        }

        final double[][] bestDistances = new double[size][size];
        final int[][] previousNodeIndex = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                bestDistances[i][j] = Double.POSITIVE_INFINITY;
                previousNodeIndex[i][j] = FloydWarshallOutput.nullNodeIndex;
            }
        }

        for (Arc arc : arcs) {
            bestDistances[arc.originIndex][arc.destinationIndex] = arc.length;
        }

        for (int k = 0; k < size; k++) {
            if ((k % 100) == 0) {
                long progressMs = System.currentTimeMillis() - startTimeMs;
                //System.out.printf("%,8d[ms] : Processing Intermediate node %,d (%s)\n", progressMs, k, indexToId[k]);
            }

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    double distIToK = bestDistances[i][k];
                    double distKToJ = bestDistances[k][j];
                    if ((distIToK != Double.POSITIVE_INFINITY) && (distKToJ != Double.POSITIVE_INFINITY)) {
                        double distIToJViaK = distIToK + distKToJ;
                        if (distIToJViaK < bestDistances[i][j]) {
                            bestDistances[i][j] = distIToJViaK;
                            previousNodeIndex[i][j] = k;
                        }
                    }
                }
            }
        }

        long takenMs = System.currentTimeMillis() - startTimeMs;
        System.out.printf("Took %,d[ms] to calculate Floyd Warshall\n", takenMs);
        return new FloydWarshallOutput(indexToId, previousNodeIndex, bestDistances);
    }
}



class FloydWarshallOutput {
    public static final int nullNodeIndex = -1;
    public final String[] indexToId;

    public final int[][] previousNodeIndex;
    public final double[][] bestDistances;
    public FloydWarshallOutput(
            String[] indexToId,
            int[][] previousNodeIndex,
            double[][] bestDistances) {
        this.indexToId = indexToId;
        this.previousNodeIndex = previousNodeIndex;
        this.bestDistances = bestDistances;
    }
}



//
//import java.util.Deque;
//import java.util.HashMap;
//import java.util.LinkedList;
//import java.util.Map;
//public class FloydWarshallAllPairShortestPath {
//
//    class NegativeWeightCycleException extends RuntimeException {
//
//    }
//
//    private static final int INF = 1000000;
//
//    public int[][] allPairShortestPath(int[][] distanceMatrix) {
//        
//        int distance[][] = new int[distanceMatrix.length][distanceMatrix.length];
//        int path[][] = new int[distanceMatrix.length][distanceMatrix.length];
//
//        for (int i=0; i < distanceMatrix.length; i++) {
//            for (int j=0; j< distanceMatrix[i].length; j++){
//                distance[i][j] = distanceMatrix[i][j];
//                if (distanceMatrix[i][j] != INF && i != j) {
//                    path[i][j] = i;
//                } else {
//                    path[i][j] = -1;
//                }
//            }
//        }
//
//        for(int k=0; k < distanceMatrix.length; k++){
//            for(int i=0; i < distanceMatrix.length; i++){
//                for(int j=0; j < distanceMatrix.length; j++){
//                    if(distance[i][k] == INF || distance[k][j] == INF) {
//                        continue;
//                    }
//                    if(distance[i][j] > distance[i][k] + distance[k][j]){
//                        distance[i][j] = distance[i][k] + distance[k][j];
//                        path[i][j] = path[k][j];
//                    }
//                }
//            }
//        }
//
//        //look for negative weight cycle in the graph
//        //if values on diagonal of distance matrix is negative
//        //then there is negative weight cycle in the graph.
//        for(int i = 0; i < distance.length; i++) {
//            if(distance[i][i] < 0) {
//                throw new NegativeWeightCycleException();
//            }
//        }
//
//        printPath(path, 3, 2);
//        return distance;
//    }
//
//    public void printPath(int[][] path, int start, int end) {
//        if(start < 0 || end < 0 || start >= path.length || end >= path.length) {
//            throw new IllegalArgumentException();
//        }
//
//        System.out.println("Actual path - between " + start + " " + end);
//        Deque<Integer> stack = new LinkedList<>();
//        stack.addFirst(end);
//        while (true) {
//            end = path[start][end];
//            if(end == -1) {
//                return;
//            }
//            stack.addFirst(end);
//            if(end == start) {
//                break;
//            }
//        }
//
//        while (!stack.isEmpty()) {
//            System.out.print(stack.pollFirst() + " ");
//        }
//
//        System.out.println();
//    }
//
//    public static void main(String args[]){
//        int[][] graph = {
//                {0,   3,   6,   15},
//                {INF, 0,  -2,   INF},
//                {INF, INF, 0,   2},
//                {1,   INF, INF, 0}
//        };
//
//        FloydWarshallAllPairShortestPath shortestPath = new FloydWarshallAllPairShortestPath();
//        int[][] distance = shortestPath.allPairShortestPath(graph);
//        System.out.println("Minimum Distance matrix");
//        for(int i=0; i < distance.length; i++){
//            for(int j=0; j < distance.length; j++){
//                System.out.print(distance[i][j] + " ");
//            }
//            System.out.println("");
//        }
//    }
//}
