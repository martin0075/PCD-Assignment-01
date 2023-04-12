/**
 * Implementation of BFS (Breadth First Search) algorithm.
 *
 * The input file must be formatted as follows: an initial line
 * contains two integers, the number n of nodes and the number m of
 * edges, respectively.  Then m lines follow: each line contains three
 * numbers, two integers between 0 and n-1 (the nodes connected by an
 * edge) and a double (the corresponding weight, that is ignored here).
 *
 * To compile: javac BFS.java 
 *
 * To execute: java -ea BFS <fileIn>
 *
 * (C) 2021 Moreno Marzolla (https://www.moreno.marzolla.name/)
 *
 * Distributed under the CC-zero 1.0 license
 * https://creativecommons.org/publicdomain/zero/1.0/
 * 
 */

import java.io.*;
import java.util.*;

public class Topologico {
    
    int n;      // number of nodes in the input graph
    int m;      // number of edges in the input graph
    Vector< LinkedList<Edge> > adjList; // the input graph is stored using adjacency lists
                                        //array di liste per ogni nodo che al loro interno contengono gli archi del nodo
    int p[];    // p[v] is the predecessor of node v in the BFS tree
    int d[];    // d[v] is the distance (number of edges) of node v from the source
    int nedges; // Number of edges of the BFS tree
    
    /**
     * Edge of a weighted, directed graph
     */
    private class Edge {
        final int src;
        final int dst;
        final double w;

        public Edge(int src, int dst, double w)
        {
            this.src = src;
            this.dst = dst;
            this.w = w;
        }
    }

    /* The constructor reads the graph from the input file */
    public Topologico(String inputf)
    {
        readGraph(inputf);
    }

    public void dump( )
    {
        System.out.printf("%d nodi, %d archi BFS tree\n", n, nedges);
        System.out.printf("src\tdst\td[dst]\n");
        for (int v=0; v<n; v++)  {
            if (p[v] >= 0) 
                System.out.printf("%d\t%d\t%d\n", p[v], v, d[v]);
        }
    }
    
    
    private void readGraph(String inputf)
    {                
        Locale.setDefault(Locale.US);
        
        try {
	     Scanner f = new Scanner(new FileReader(inputf));
	     n = f.nextInt();
	     m = f.nextInt();
             
             adjList = new Vector< LinkedList<Edge> >(n);

             for (int i=0; i<n; i++) {
                 adjList.add(i, new LinkedList<Edge>());
             }
             
	     for (int i=0; i<m; i++) {
                 final int src = f.nextInt();
                 final int dst = f.nextInt();
                 final double weight = f.nextDouble();
                 final Edge newEdge = new Edge(src, dst, weight);
                 adjList.get(src).add( newEdge );
	     }
        } catch (IOException ex) {
	     System.err.println(ex);
	     System.exit(1);
        }
    }

    public void visit( int s )
    {
        /* Array of parents */
        p = new int[n];

        /* Array of distances */
        d = new int[n];

        nedges = 0;
        /* COMPLETARE IL CODICE QUI */
        
        for(int i=0; i<p.length;i++) 
        {
        	p[i]=-1;
        	d[i]=-1;
        }
        
        Queue<Integer> coda=new LinkedList<>();
        d[s]=0;
        coda.add(s);
        while(!coda.isEmpty()) 
        {
        	int u=coda.poll();
        	for(Edge e: adjList.get(u)) 
        	{
        		int v=e.dst;
        		if(d[v]==-1) 
             	{
             		d[v]=d[u]+1;
             		p[v]=u;
             		coda.add(v);
             	}
        	}
        	/*for(int i=0; i<p.length;i++) 
            {
             	if(d[i]==-1) 
             	{
             		d[i]=d[u]+1;
             		p[i]=u;
             		coda.add(i);
             	}
            }*/
        }
    }

    /**
     * The main procedure creates a BSF object, invokes the BFS
     * algorithm, and dumps the BFS tree.
     */    
    public static void main( String args[])
    {
        if (args.length != 1) {
            final int n = 6;
            System.out.printf("%d %d\n", n, n*(n-1)/2);
            for (int i=0; i<n-1; i++) {
                for (int j=i+1; j<n; j++) {
                    final double weight = Math.random() * 100;
                    System.out.printf("%d %d %f\n", i, j, weight);
                }
            }
            return;
        }
	
        Topologico bfs = new Topologico(args[0]);
        final long tstart = System.currentTimeMillis();
        bfs.visit(0);
        final long tend = System.currentTimeMillis();
        final double elapsed = (tend - tstart) / 1000.0;
        System.err.println("Elapsed time " + elapsed + " sec");
        bfs.dump();
    }    
}    
