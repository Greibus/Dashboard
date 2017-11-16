using System;
using System.Collections.Generic;
using System.Linq;

namespace org.tec.datastructures.General.Graph.AdjacencyList
{
    
    public class GraphVertex<T>
    {
        public T Value { get; set; }

        public HashSet<GraphVertex<T>> Edges { get; set; }

        public GraphVertex(T value)
        {
            Value = value;
            Edges = new HashSet<GraphVertex<T>>();
        }

    }

    
    public class Graph<T>
    {
        public int VerticesCount => Vertices.Count;
        internal Dictionary<T, GraphVertex<T>> Vertices { get; set; }

        
        public Graph()
        {
            Vertices = new Dictionary<T, GraphVertex<T>>();
        }


       
        public GraphVertex<T> ReferenceVertex
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


        
        public GraphVertex<T> AddVertex(T value)
        {
            if (value == null)
            {
                throw new ArgumentNullException();
            }

            var newVertex = new GraphVertex<T>(value);

            Vertices.Add(value, newVertex);

            return newVertex;
        }

        
        public void RemoveVertex(T vertex)
        {
            if (vertex == null)
            {
                throw new ArgumentNullException();
            }

            if (!Vertices.ContainsKey(vertex))
            {
                throw new Exception("Vertex not in this graph.");
            }

            foreach (var v in Vertices[vertex].Edges)
            {
                v.Edges.Remove(Vertices[vertex]);
            }

            Vertices.Remove(vertex);
        }

        
        public void AddEdge(T source, T dest)
        {
            if (source == null || dest == null)
            {
                throw new ArgumentException();
            }

            if (!Vertices.ContainsKey(source) || !Vertices.ContainsKey(dest))
            {
                throw new Exception("Source or Destination Vertex is not in this graph.");
            }

            if (Vertices[source].Edges.Contains(Vertices[dest]) 
                || Vertices[dest].Edges.Contains(Vertices[source]))
            {
                throw new Exception("Edge already exists.");
            }

            Vertices[source].Edges.Add(Vertices[dest]);
            Vertices[dest].Edges.Add(Vertices[source]);
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

            if (!Vertices[source].Edges.Contains(Vertices[dest]) 
                || !Vertices[dest].Edges.Contains(Vertices[source]))
            {
                throw new Exception("Edge do not exists.");
            }

            Vertices[source].Edges.Remove(Vertices[dest]);
            Vertices[dest].Edges.Remove(Vertices[source]);
        }

        
        public bool HasEdge(T source, T dest)
        {
            if (!Vertices.ContainsKey(source) || !Vertices.ContainsKey(dest))
            {
                throw new ArgumentException("source or destination is not in this graph.");
            }

            return Vertices[source].Edges.Contains(Vertices[dest]) 
                && Vertices[dest].Edges.Contains(Vertices[source]);
        }

        public List<T> GetAllEdges(T vertex)
        {
            if (!Vertices.ContainsKey(vertex))
            {
                throw new ArgumentException("vertex is not in this graph.");
            }

            return Vertices[vertex].Edges.Select(x => x.Value).ToList();
        }

        
        public GraphVertex<T> FindVertex(T value)
        {
            if (Vertices.ContainsKey(value))
            {
                return Vertices[value];
            }

            return null;
        }

    }
}
