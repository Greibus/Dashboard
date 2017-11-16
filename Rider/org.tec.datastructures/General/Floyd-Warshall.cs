using System;
using System.Collections.Generic;
using org.tec.datastructures.General.Graph.AdjacencyList;

namespace org.tec.datastructures.General{
    
    public class AllPairShortestPathResult<T, W> where W : IComparable
    {
        public T Source { get; }
        public T Destination { get; }

        public W Distance { get; }

        public List<T> Path { get; }

        public AllPairShortestPathResult(T source, T destination,
            W distance, List<T> path)
        {
            Source = source;
            Destination = destination;
            Distance = distance;
            Path = path;
        }

    }
    public class FloydWarshallShortestPath<T, W> where W : IComparable
    {
        IShortestPathOperators<W> operators;
        public FloydWarshallShortestPath(IShortestPathOperators<W> operators)
        {
            this.operators = operators;
        }

        public List<AllPairShortestPathResult<T, W>> GetAllPairShortestPaths(WeightedGraph<T, W> graph)
        {
            var vertexIndex = new Dictionary<int, T>();
            var reverseVertexIndex = new Dictionary<T, int>();
            int i = 0;
            foreach (var vertex in graph.Vertices)
            {
                vertexIndex.Add(i, vertex.Key);
                reverseVertexIndex.Add(vertex.Key, i);
                i++;
            }

            var result = new W[graph.Vertices.Count, graph.Vertices.Count];
            var parent = new T[graph.Vertices.Count, graph.Vertices.Count];
            for (i = 0; i < graph.VerticesCount; i++)
            {
                for (int j = 0; j < graph.VerticesCount; j++)
                {
                    result[i, j] = operators.MaxValue;
                }
            }

            for (i = 0; i < graph.VerticesCount; i++)
            {
                result[i, i] = operators.DefaultValue;
            }
            for (i = 0; i < graph.VerticesCount; i++)
            {
                foreach (var edge in graph.Vertices[vertexIndex[i]].Edges)
                {
                    result[i, reverseVertexIndex[edge.Key.Value]] = edge.Value;
                    parent[i, reverseVertexIndex[edge.Key.Value]] = graph.Vertices[vertexIndex[i]].Value;

                    result[reverseVertexIndex[edge.Key.Value], i] = edge.Value;
                    parent[reverseVertexIndex[edge.Key.Value], i] = edge.Key.Value;
                }
            }

            for (int k = 0; k < graph.VerticesCount; k++)
            {
                for (i = 0; i < graph.VerticesCount; i++)
                {
                    for (int j = 0; j < graph.VerticesCount; j++)
                    {
                        if (result[i, k].Equals(operators.MaxValue) 
                            || result[k, j].Equals(operators.MaxValue))
                        {
                            continue;
                        }

                        var sum = operators.Sum(result[i, k], result[k, j]);

                        if (sum.CompareTo(result[i, j]) < 0)
                        {
                            result[i, j] = sum;
                            parent[i, j] = parent[k, j];
                        }
                    }
                }
            }

            var finalResult = new List<AllPairShortestPathResult<T, W>>();
            for (i = 0; i < graph.VerticesCount; i++)
            {
                for (int j = 0; j < graph.VerticesCount; j++)
                {
                    var source = vertexIndex[i];
                    var dest = vertexIndex[j];
                    var distance = result[i, j];
                    var path = tracePath(result, parent, i, j, vertexIndex, reverseVertexIndex);

                    finalResult.Add(new AllPairShortestPathResult<T, W>(source, dest, distance, path));

                }
            }

            return finalResult;
        }

        
        private List<T> tracePath(W[,] result, T[,] parent, int i, int j,
            Dictionary<int, T> vertexIndex, Dictionary<T, int> reverseVertexIndex)
        {
            var pathStack = new Stack<T>();

            pathStack.Push(vertexIndex[j]);

            var current = parent[i, j];
            while (i != j)
            {
                pathStack.Push(parent[i, j]);
                j = reverseVertexIndex[parent[i, j]];
            }

            var path = new List<T>();

            while (pathStack.Count > 0)
            {
                path.Add(pathStack.Pop());
            }

            return path;
        }


    }
}
