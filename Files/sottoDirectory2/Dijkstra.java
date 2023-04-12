/**
 * Dijkstra.java
 *
 * Implementation of Dijkstra's algorithm to compute the shortest
 * paths on a directed, weighted graph with nonnegative edge weights.
 *
 * The input file must be formatted as follows: the first line
 * contains two integers: the number n of nodes and the number m of
 * edges. Then m lines follow: each line contains three numbers, two
 * integers between 0 and n-1 (the nodes connected by an edge) and a
 * double (the edge weight).
 *
 * The initial node s is assumed to be node 0. The tree containing the
 * shortest paths are described in an output file.
 *
 * To compile: javac Dijkstra.java MinHeap.java
 *
 * To execute: java Dijkstra <fileIn>
 *
 * (C) 2017 Gianluigi Zavattaro (https://www.unibo.it/sitoweb/gianluigi.zavattaro)
 * (C) 2020, 2021 Moreno Marzolla (https://www.moreno.marzolla.name/)
 *
 * Distributed under the CC-zero 1.0 license
 * https://creativecommons.org/publicdomain/zero/1.0/
 */

import java.io.*;
import java.util.*;

public class Dijkstra {

    int n;      // number of nodes in the graph
    int m;      // number of edges in the graph    
    Vector< LinkedList<Edge> > adjList; // adjacency list
    int source; // the source node
    int[] p;    // array of parents
    double[] d; // array of distances from the source
    LinkedList<Edge> sp;  // Edges belonging to the shortest path tree
    
    /**
     * Edge of a weighted, directed graph
     */
    private class Edge {
        final int src;
        final int dst;
        final double w;

        /**
         * Build a directed edge (src, dst) with weight w
         */
        public Edge(int src, int dst, double w)
        {
            /* Dijkstra's algorithm requires that weights are
               non-negative */
            assert(w >= 0.0);
            
            this.src = src;
            this.dst = dst;
            this.w = w;
        }
    }   
    
    /**
     * Constructs a Dijkstra object; read input from inputf
     *
     * @param inputf the input file name
     */
    public Dijkstra(String inputf)
    {
        this.source = source;
        this.sp = new LinkedList<Edge>();
        readGraph(inputf);
    }

    /**
     * Dump the shortest path tree to standard output.
     */
    public void dump( )
    {
        System.out.println("Source = " + source);
        System.out.println();
        System.out.println("   v p[v]    d[v]");
        System.out.println("---- ---- -------");
        for (int i=0; i<n; i++) {
            System.out.printf("%4d %4d %7.2f\n", i, p[i], d[i]);
        }
    }

    /**
     * Read the input graph from file inputf.
     *
     * @param inputf file name
     */
    private void readGraph(String inputf)
    {
        Locale.setDefault(Locale.US);
        
        try {
            Scanner f = new Scanner(new FileReader(inputf));
            n = f.nextInt();
            m = f.nextInt();
            
            adjList = new Vector< LinkedList<Edge> >();
            
            for (int i=0; i<n; i++) {
                adjList.add(i, new LinkedList<Edge>() );
            }
            
            for (int i=0; i<m; i++) {
                final int src = f.nextInt();
                final int dst = f.nextInt();
                final double weight = f.nextDouble();
                assert( weight >= 0.0 );
                adjList.get(src).add( new Edge(src, dst, weight) );
            }
        } catch (IOException ex) {
            System.err.println(ex);
            System.exit(1);
        }   
    }

    /**
     * Execute Dijkstra's shortest paths algorithm starting from node s
     *
     * @param s source node
     */
    public void shortestPaths( int s )
    {
        /* sp_edges[v] is the edge connecting v with its parent in the
           shortest path tree */
        Edge[] sp_edges = new Edge[n];

        boolean[] visited = new boolean[n];

        MinHeap q = new MinHeap(n);
        
	d = new double[n];
        p = new int[n];
        
        Arrays.fill(d, Double.POSITIVE_INFINITY);
        Arrays.fill(p, -1);
        Arrays.fill(visited, false);
	d[s] = 0.0;
        for (int v=0; v<n; v++) {
            q.insert(v, d[v]);
        }

        while (!q.isEmpty()) {
            final int u = q.min();
            q.deleteMin();
	    visited[u] = true;
            if (sp_edges[u] != null) {
                sp.add(sp_edges[u]);
            }
            for (Edge e : adjList.get(u)) {
		final int v = e.dst;
	        if (!visited[v] && (d[u] + e.w < d[v])) {
                    d[v] = d[u] + e.w;
                    q.changePrio(v, d[v]);
                    p[v] = u;
                    sp_edges[v] = e;
		}
	    }
	}
    }

    /**
     * The main procedure creates a Dijkstra object, invokes
     * Dijkstra's algorithm, and prints the shortest paths tree.
     */
    public static void main( String args[])
    {
        if (args.length != 1) {
            final int n = 100;
            System.out.printf("%d %d\n", n, n*(n-1));
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    if (i != j) {
                        final double weight = 0.1 + Math.random() * 100;
                        System.out.printf("%d %d %f\n", i, j, weight);
                    }
                }
            }
            return;
        }
	
        Dijkstra sp = new Dijkstra(args[0]);
        sp.shortestPaths(0);
        sp.dump();
    }
}    
