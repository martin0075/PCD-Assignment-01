/*
    Marcolini Martin
    0000921984
    martin.marcolini@studio.unibo.it
 */

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;

public class Esercizio2 {
    int nNodi;
    LinkedList< Edge > edges; // il grafo è rappresentato come una lista di archi
    int[] vetPadri;
    double[] vetDistanzeSorgente;


    public Esercizio2(String input){
        readGraph(input);
    }

    public static void main(String[] args){
        Esercizio2 es=new Esercizio2(args[0]);
        es.camminoDiCostoMassimo(0);
    }

    /*
    metodo che legge il grafo passato in input e crea la lista di archi
     */
    public void readGraph(String inputf)
    {
        Locale.setDefault(Locale.US);

        try {
            Scanner f = new Scanner(new FileReader(inputf));
            nNodi = f.nextInt();
            edges = new LinkedList< Edge >();

            for (int i=0; i<nNodi; i++) {
                final int nodoSorgente=i;
                final String tipoNodo=f.next();
                final double peso = f.nextDouble();
                final int numNodiDestinazione = f.nextInt();
                for(int j=0;j<numNodiDestinazione;j++){
                    final int nodoDestinazione=f.nextInt();
                    edges.add(new Edge (nodoSorgente, nodoDestinazione, peso));

                }
            }
        } catch (IOException ex) {
            System.err.println(ex);
            System.exit(1);
        }
    }

    /*
    metodo che implementa l'algoritmo di Bellman-Ford per ricavare il cammino,
    che parte da un magazzino e termina in un ospedale,
    che permette di massimizzare il carico complessivo
     */
    public void camminoDiCostoMassimo( int s )
    {
        vetDistanzeSorgente = new double[nNodi];
        vetPadri = new int[nNodi];


        Arrays.fill(vetDistanzeSorgente, Double.NEGATIVE_INFINITY);
        Arrays.fill(vetPadri, -1);

        vetDistanzeSorgente[s] = 0.0;
        for (int i=0; i<nNodi-1; i++) {
            for (Edge e: edges) {
                final int nodoSorgente = e.nodoSorgente;
                final int nodoDestinazione = e.nodoDestinazione;
                final double peso = e.peso;
                /*
                ad ogni passo si effettua la tecnica del rilassamento "al contrario", poichè
                al posto di trovare il cammino di costo minimo si vuole ricavare il cammino in grado
                di massimizzare il carico complessivo.
                 */
                if (vetDistanzeSorgente[nodoSorgente] + peso > vetDistanzeSorgente[nodoDestinazione]) {
                    vetDistanzeSorgente[nodoDestinazione] = vetDistanzeSorgente[nodoSorgente] + peso;
                    vetPadri[nodoDestinazione] = nodoSorgente;
                }
            }
        }


        /*
        si verifica se sono presenti dei cicli positivi,
        si crea una lista di nodi chiamata cammino che verrà inizializzata
        con gli elementi del vettore dei padri per ricavare il cammino di costo massimo
         */
        LinkedList<Integer> cammino=new LinkedList<Integer>();
        int dst=nNodi-1;
        int dimMax=nNodi;
        int i=0;
        /*
        il cammino in grado di massimizzare il carico complessivo potrà essere formato al
        massimo da un numero di nodi pari al numero di nodi grafo, altrimenti siamo in presenza
        di cicli positivi.
        Il while è necessario per verificare questa condizione.
         */
        while(dst>-1 && i<dimMax){
            cammino.add(dst);
            dst= vetPadri[dst];
            i++;
        }

        if(i==dimMax)
            System.out.print(-1);
        else{
            for(int j=cammino.size()-1;j>=0;j--){
                System.out.println(cammino.get(j));
            }
        }


    }

    private class Edge{
        final int nodoSorgente;
        final int nodoDestinazione;
        final double peso;

        public Edge(int nodoSorgente, int nodoDestinazione, double peso)
        {
            this.nodoSorgente = nodoSorgente;
            this.nodoDestinazione = nodoDestinazione;
            this.peso = peso;
        }
    }

}


