package com.datastructures.data.graphs;

import java.util.Iterator;

/**
 * Interface to specify a Graph ADT. A graph is a set
 * of vertices and a set of edges. Vertices are represented by
 * integers from 0 - n-1. Edges are ordered pairs of vertices. Each
 * Implementation of the Graph interface should provide a constructor
 * that specifies the number of vertices and whether or not the graph
 * is directed.
 */
public interface Graph
{
    //Accessors

    /**
     * Returns the number of vertices
      * @return The number of vertices
     */
    int gerNumV();

    /**
     * Determines whether this is a directed graph or not.
     * @return true if this is a directed graph
     */
    boolean isDirected();

    /**
     *
     * @param edge
     */
    void insert( Edge edge);

    /**
     * Determine whether an edge exists.
     * @param source source vertex
     * @param dest destination vertex
     * @return true is the edge exists
     */
    boolean isEdge (int source, int dest);

    /**
     * Get the edge between two vertices.
     * @param source source vertex
     * @param dest destination vertex
     * @return The Edge between these two vertices
     *          or an Edge with a weight of
     *          Double.POSITIVE_INFINITY if there is no edge
     */
    Edge getEdge(int source, int dest );

    /**
     * Return an iterator to the edges connected to a
     * given vertex.
     * @param source The source vertex
     * @return An Iterator<Edge> to the vertices connected to source
     */
     Iterator<Edge> edgeIterator(int source);
}
