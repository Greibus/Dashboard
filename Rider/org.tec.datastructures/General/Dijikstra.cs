using System;
using System.Collections.Generic;
using org.tec.datastructures.General.Graph.AdjacencyList;
using org.tec.datastructures.General.Heap;
using org.tec.datastructures.General.Heap.Min;

namespace org.tec.datastructures.General
{
    public interface IShortestPathOperators<W> where W : IComparable
    {
        W DefaultValue { get; }
        W MaxValue { get; }
        W Sum(W a, W b);
    }

    internal class MinHeapWrap<T, W> : IComparable where W : IComparable
    {
        internal T Target { get; set; }
        internal W Distance { get; set; }

        public int CompareTo(object obj)
        {
            return Distance.CompareTo((obj as MinHeapWrap<T, W>).Distance);
        }
    }

    public class ShortestPathResult<T, W> where W : IComparable
    {
        public ShortestPathResult(List<T> path, W length)
        {
            Length = length;
            Path = path;
        }
        public W Length { get; internal set; }
        public List<T> Path { get; private set; }
    }
    public class DijikstraShortestPath<T, W> where W : IComparable
    {
        IShortestPathOperators<W> operators;
        public DijikstraShortestPath(IShortestPathOperators<W> operators)
        {
            this.operators = operators;
        }

        public ShortestPathResult<T, W> GetShortestPath(WeightedDiGraph<T, W> graph, T source, T destination)
        {
            if (graph == null || graph.FindVertex(source) == null
                || graph.FindVertex(destination) == null)
            {
                throw new ArgumentException();
            }

            var progress = new Dictionary<T, W>();

            var parentMap = new Dictionary<T, T>();

            var minHeap = new FibornacciMinHeap<MinHeapWrap<T, W>>();
            var heapMapping = new Dictionary<T, FibornacciHeapNode<MinHeapWrap<T, W>>>();

            foreach (var vertex in graph.Vertices)
            {
                parentMap.Add(vertex.Key, default(T));

                progress.Add(vertex.Key, operators.MaxValue);

                if (vertex.Key.Equals(source))
                {
                    continue;
                }

                var wrap = new MinHeapWrap<T, W>()
                {
                    Distance = operators.MaxValue,
                    Target = vertex.Key
                };

                var heapNode = minHeap.Insert(wrap);
                heapMapping.Add(vertex.Key, heapNode);
            }

            var sourceVertex = graph.Vertices[source];
            var current = new MinHeapWrap<T, W>()
            {
                Distance = operators.DefaultValue,
                Target = source
            };

            while (minHeap.Count > 0)
            {
                if(current.Distance.Equals(operators.MaxValue))
                {
                    return new ShortestPathResult<T, W>(null, operators.MaxValue);
                }

                foreach (var neighbour in graph.Vertices[current.Target].OutEdges)
                {
                    var newDistance = operators.Sum(current.Distance,
                        graph.Vertices[current.Target].OutEdges[neighbour.Key]);

                    var existingDistance = progress[neighbour.Key.Value];

                    if (newDistance.CompareTo(existingDistance) < 0)
                    {
                        progress[neighbour.Key.Value] = newDistance;
                        heapMapping[neighbour.Key.Value].Value.Distance = newDistance;

                        minHeap.DecrementKey(heapMapping[neighbour.Key.Value]);

                        parentMap[neighbour.Key.Value] = current.Target;
                    }
                }

                current = minHeap.ExtractMin();
            }

            return tracePath(graph, parentMap, source, destination);
         
        }
        private ShortestPathResult<T, W> tracePath(WeightedDiGraph<T, W> graph,
            Dictionary<T, T> parentMap, T source, T destination)
        {
            var pathStack = new Stack<T>();

            pathStack.Push(destination);

            var currentV = destination;
            while (!currentV.Equals(default(T)) && !parentMap[currentV].Equals(default(T)))
            {
                pathStack.Push(parentMap[currentV]);
                currentV = parentMap[currentV];
            }

            var resultPath = new List<T>();
            var resultLength = operators.DefaultValue;
            while (pathStack.Count > 0)
            {
                resultPath.Add(pathStack.Pop());
            }

            for (int i = 0; i < resultPath.Count - 1; i++)
            {
                resultLength = operators.Sum(resultLength,
                    graph.Vertices[resultPath[i]].OutEdges[graph.Vertices[resultPath[i + 1]]]);
            }

            return new ShortestPathResult<T, W>(resultPath, resultLength);
        }
    }
}
