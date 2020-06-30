package com.datastructures.data.graphs;

public class Edge {

    private int dest; // The destination vertex for an edge
    private int source; //The source vertex for an edge
    private double weight;


    public Edge (int source, int dest, double weight)
    {
        this.dest = dest;
        this.source = source;
        this.weight = weight;
    }

    public Edge (int source, int dest)
    {
        this(source,dest,1.0f);
    }

    /**
     *  Tests if two Edge objects are equal
     *  Two Equal if the destinations and the sources are the same
     *  weight is not considered
     *  pre: o is an instance of type Edge
     * @param o object for comparison
     * @return true if they are the same, false otherwise
     */
    public boolean equals (Object o)
    {
        if (o instanceof Edge) {
            if (((Edge) o).dest == this.dest &&
                    ((Edge)o).source == this.source)
            {
                return true;
            }
        }
        return false;
    }

    public int getDest() {
        return dest;
    }

    public int getSource() {
        return source;
    }

    public double getWeight() {
        return weight;
    }

    public int getHashCode(){
        return source + dest;
    }

    public String toString()
    {
        String str = source + " -> " + dest;

        return str;
    }
}

