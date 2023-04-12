/*
    Marcolini Martin
    0000921984
    martin.marcolini@studio.unibo.it
 */

import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Esercizio5 {
    int nMonete;//numero di monete totali
    int [] monete;//vettore con tutti i tagli di monete disponibili

    /*
    matrice popolata con il numero minimo di monete che è necessario utilizzare
    per erogare un resto pari a j, se l'importo j non è erogabile la corrispondente casella
    della matrice viene inizializzata a integer.Max_Value
     */
    int[][] N;

    public Esercizio5(String input){
        ReadMonete(input);
    }

    public static void main(String []args){
        Esercizio5 es=new Esercizio5(args[0]);
        es.MinRestoNonErogabile();

    }

    /*
       metodo che si occupa di calcolare il minimo resto non erogabile,
       ogni volta si verifica se il resto passato
       come parametro del metodo è erogabile in caso affermativo viene incrementata la varibile
       resto ed effettuato nuovamente il controllo, altrimenti viene stampato il resto non erogabile
    */
    public void MinRestoNonErogabile(){
        int resto=1;
        while(restoErogabile(resto)){
            resto++;
        }
        System.out.print(resto);
    }

    /*
    metodo che si occupa di leggere dal file input passato come parametro,
    i tagli di moneta disponibili per erogare il resto inizializzando il vettore monete
     */
    public void ReadMonete(String input){
        Locale.setDefault(Locale.US);
        try{
            Scanner scan=new Scanner(new FileReader(input));
            nMonete=scan.nextInt();
            monete=new int[nMonete];
            for(int i=0; i<nMonete;i++){
                monete[i]=scan.nextInt();
            }
        }catch(IOException e){
            System.out.println(e);
        }

    }

    /*
    metodo che si occupa di verificare se il resto passato come parametro del metodo
    è erogabile
     */
    public boolean restoErogabile(int resto)
    {
        int i,j;

        N = new int[nMonete][resto+1];

        /*
        Definizione dei casi base: viene popolata la prima riga della matrice
         */
        N[0][0] = 0;
        for ( j=1; j<=resto; j++ ) {
            if ( j == monete[0] ) {
                N[0][j] = 1;
            } else {
                N[0][j] = Integer.MAX_VALUE;
            }
        }
        /*
        r=6
        m1=4
        m2=3
        m3=3
        m4=1
         */

        /*
         i due cicli for sono necessari per riempire la matrice N, sfruttando
         la programmazione dinamica
        */
        for ( i=1; i<nMonete; i++ ) {
            for ( j=0; j<=resto; j++ ) {
                /*
                 * si verifica se il resto j è maggiore della moneta i-esima, poi
                 * si verifica se il resto è erogabile utilizzando la moneta i-esima,
                 * poi se il resto è erogabile sia utilizzando che omettendo la moneta i-esima
                 * allora si opta per l'alternativa che minimizza il numero di monete.
                 * Se almeno una di queste condizioni non vengono rispettate si sceglie la moneta
                 * precendente (monete[i-1]) per erogare il resto j
                 */
                if ( j >= monete[i] &&
                        N[i-1][j-monete[i]] != Integer.MAX_VALUE &&
                        N[i-1][j] > N[i-1][j-monete[i]] + 1 ) {
                    N[i][j] = N[i-1][j-monete[i]] + 1;
                } else {
                    N[i][j] = N[i-1][j];
                }
            }
        }

        /*
         se la soluzione del problema del resto che si trova nella casella
         in corrispondenza dell'ultima riga ed ultima colonna è uguale ad integer.Max_Value
         significa che il resto, passato come parametro del metodo, non è erogabile con i tagli
         di monete a disposizione
        */
        if(N[nMonete-1][resto]==Integer.MAX_VALUE)
            return false;
        return true;
    }






}
