/*
    Marcolini Martin
    0000921984
    martin.marcolini@studio.unibo.it
 */

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;

public class Esercizio4 {

    LinkedList<Nodo> albero=new LinkedList<Nodo>();//lista contenente tutti i nodi dell'albero

    public Esercizio4(String input){
        readGraph(input);
    }

    public static void main(String[]args){
        Esercizio4 es =new Esercizio4(args[0]);
        es.risolviABR();


    }

    /*
        si verifica se l'albero in questione è un albero binario di ricerca e nel caso in cui
        il metodo che si occupa di effettuare questo controllo restituisce true, si stampa
        il nodo più piccolo dell'albero, altrimenti si stampa NON BST
    */
    public void risolviABR(){
        if(verificaAlberoBinarioRicerca(albero.get(1),Integer.MIN_VALUE, Integer.MAX_VALUE)){
            int min=Integer.MAX_VALUE;
            for(int i=0;i<albero.size();i++){
                if(min>albero.get(i).getChiave())
                    min=albero.get(i).getChiave();
            }
            System.out.print(min);
        }else
            System.out.print("NON BST");
    }

    /*
    metodo che verifica se l'albero in questione è un albero binario di ricerca
     */
    public boolean verificaAlberoBinarioRicerca(Nodo t, int a, int b){
        /*
        caso base: si verifica se l'albero è vuoto, oppure se
        i nodi da analizzare sono terminati
         */
        if(t==null)
            return true;
        else
            /*
            si verifica che la chiave del nodo t sia compresa nell'intervallo [a,b]
            e vengono effettuate due chiamate ricorsive, che analizzano
            la parte destra e sinistra dell'albero per esaminare
            se si tratta di un albero binario di ricerca
             */
            return (t.getChiave()>=a && t.getChiave()<=b &&
                    verificaAlberoBinarioRicerca(t.getFiglioSx(),a,t.getChiave()) &&
                    verificaAlberoBinarioRicerca(t.getFiglioDx(),t.getChiave(),b));
    }

    /*
    metodo che si occupa di leggere il grafo passato in input
    e inizializzare la lista albero con tutti i nodi
     */
    public void readGraph(String input){
        Locale.setDefault(Locale.US);
        String[]valori;

        try{
            Scanner scan=new Scanner(new FileReader(input));
            String riga=scan.nextLine();
            valori=riga.split(" ");
            int radice=Integer.parseInt(valori[1]);//perchè in valori[0] è presente la '('

            Nodo root=new Nodo(radice);
            albero.add(root);
            Nodo padreTmp=root;

            /*
                il for parte con i=2 perchè nel primo elemento dell'array valori
                è presente la prima "(", mentre nel secondo elemento è presente
                la radice dell'albero
             */
            for(int i=2; i<valori.length;i++){
                /*
                Se dopo la "(" è presente un valore non nullo(-)
                viene aggiunto un figlio sinistro e il nodo padre viene settato
                al nodo appena aggiunto nella lista
                 */
                if(valori[i].equals("(")){
                    if(!valori[i+1].equals("-")){
                        int valNodoSx=Integer.parseInt(valori[i+1]);
                        Nodo nodoSx=new Nodo(valNodoSx);
                        padreTmp.setFiglioSx(nodoSx);
                        nodoSx.setPadre(padreTmp);
                        padreTmp=nodoSx;
                        albero.add(nodoSx);
                    }
                }
                else if(valori[i].equals(",")){
                    /*
                    se l'elemento successivo alla virgola è un '-', significa che
                    il figlio/fratello destro non esiste per cui non viene aggiunto
                    nella lista dei nodi
                     */
                    if(!valori[i+1].equals("-")){
                        //aggiunge un figlio destro
                        if(valori[i-1].equals("-") ){
                            int valNodoDx=Integer.parseInt(valori[i+1]);
                            Nodo nodoDx=new Nodo(valNodoDx);
                            padreTmp.setFiglioDx(nodoDx);
                            nodoDx.setPadre(padreTmp);
                            padreTmp=nodoDx;
                            albero.add(nodoDx);
                        }
                        /*
                        aggiunge un fratello destro, in questo caso prima di leggere
                        il valore del nodo bisogna modificare il nodo padre che fa riferimento
                        al fratello sinistro precendemente inserito, per cui viene considerato come nodo padre
                        il padre del figlio sinistro
                         */
                        else if(!valori[i-1].equals("-")){
                            padreTmp=padreTmp.getPadre();
                            int valNodoDx=Integer.parseInt(valori[i+1]);
                            Nodo nodoDx=new Nodo(valNodoDx);
                            padreTmp.setFiglioDx(nodoDx);
                            nodoDx.setPadre(padreTmp);
                            padreTmp=nodoDx;
                            albero.add(nodoDx);
                        }

                    }
                }
                /*
                se è presente la ")" significa che per il relativo nodo padre
                non ci sono più figli destri/sinistri da aggiungere, di conseguenza
                il nodo padre viene settato al suo padre
                 */
                else if(valori[i].equals(")")){
                    padreTmp=padreTmp.getPadre();
                }
            }
        }catch(IOException e){
            System.out.print(e);
        }
    }

    private class Nodo{
        private int chiave;
        private Nodo figlioSx;
        private Nodo figlioDx;
        private Nodo padre;

        public Nodo(int chiave){
            this.chiave=chiave;
            this.figlioSx=null;
            this.figlioDx=null;
            this.padre=null;

        }

        public void setFiglioSx(Nodo figlioSx){
            this.figlioSx=figlioSx;
        }

        public void setFiglioDx(Nodo figlioDx){
            this.figlioDx=figlioDx;
        }

        public void setPadre(Nodo padre){
            this.padre=padre;
        }

        public int getChiave(){
            return chiave;
        }

        public Nodo getFiglioSx(){
            return figlioSx;
        }

        public Nodo getFiglioDx(){
            return figlioDx;
        }

        public Nodo getPadre(){
            return padre;
        }
    }

}








