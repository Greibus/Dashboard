using System;
using System.Collections.Generic;
using System.Linq;

namespace org.tec.datastructures.General.Graph.AdjacencyList
{
    
    public class WeightedGraphVertex<T, W> where W : IComparable
    {
        public T Value { get; private set; }

        public Dictionary<WeightedGraphVertex<T, W>, W> Edges { get; set; }

        public WeightedGraphVertex(T value)
        {
            Value = value;
            Edges = new Dictionary<WeightedGraphVertex<T, W>, W>();

        }

    }

    
    public class WeightedGraph<T, W> where W : IComparable
    {
        public int VerticesCount => Vertices.Count;
        internal Dictionary<T, WeightedGraphVertex<T, W>> Vertices { get; set; }

        
        public WeightedGraph()
        {
            Vertices = new Dictionary<T, WeightedGraphVertex<T, W>>();
        }


        
        public WeightedGraphVertex<T, W> ReferenceVertex
        {
            get
            {
                using (var enumerator = Vertices.GetEnumerator())
                {
                    if (enumerator.MoveNext())
                    {
                        return enumerator.Current.Value;
                    }
                }

                return null;
            }
        }

      
        
        public WeightedGraphVertex<T, W> AddVertex(T value)
        {
            if (value == null)
            {
                throw new ArgumentNullException();
            }

            var newVertex = new WeightedGraphVertex<T, W>(value);

            Vertices.Add(value, newVertex);

            return newVertex;
        }

        
        public void RemoveVertex(T value)
        {
            if (value == null)
            {
                throw new ArgumentNullException();
            }

            if (!Vertices.ContainsKey(value))
            {
                throw new Exception("Vertex not in this graph.");
            }


            foreach (var vertex in Vertices[value].Edges)
            {
                vertex.Key.Edges.Remove(Vertices[value]);
            }

            Vertices.Remove(value);
        }

        
        public void AddEdge(T source, T dest, W weight)
        {
            if (source == null || dest == null)
            {
                throw new ArgumentException();
            }

            if (!Vertices.ContainsKey(source) || !Vertices.ContainsKey(dest))
            {
                throw new Exception("Source or Destination Vertex is not in this graph.");
            }


            Vertices[source].Edges.Add(Vertices[dest], weight);
            Vertices[dest].Edges.Add(Vertices[source], weight);
        }

        
        public void RemoveEdge(T source, T dest)
        {

            if (source == null || dest == null)
            {
                throw new ArgumentException();
            }

            if (!Vertices.ContainsKey(source) || !Vertices.ContainsKey(dest))
            {
                throw new Exception("Source or Destination Vertex is not in this graph.");
            }

            if (!Vertices[source].Edges.ContainsKey(Vertices[dest]) 
                || !Vertices[dest].Edges.ContainsKey(Vertices[source]))
            {
                throw new Exception("Edge do not exists.");
            }

            Vertices[source].Edges.Remove(Vertices[dest]);
            Vertices[dest].Edges.Remove(Vertices[source]);
        }

        
        public bool HasEdge(T source, T dest)
        {
            if(!Vertices.ContainsKey(source) || !Vertices.ContainsKey(dest))
            {
                throw new ArgumentException("source or destination is not in this graph.");
            }

            return Vertices[source].Edges.ContainsKey(Vertices[dest])
                   && Vertices[dest].Edges.ContainsKey(Vertices[source]);

        }


        public List<Tuple<T, W>> GetAllEdges(T vertex)
        {
            if (!Vertices.ContainsKey(vertex))
            {
                throw new ArgumentException("vertex is not in this graph.");
            }

            return Vertices[vertex].Edges.Select(x => new Tuple<T, W>(x.Key.Value, x.Value)).ToList();
        }

        
        public WeightedGraphVertex<T, W> FindVertex(T value)
        {
            if (Vertices.ContainsKey(value))
            {
                return Vertices[value];
            }

            return null;
        }
    }
}
