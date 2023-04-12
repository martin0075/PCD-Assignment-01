/*
    Marcolini Martin
    0000921984
    martin.marcolini@studio.unibo.it
 */

import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Esercizio3 {
    public static void main(String [] args){
        Esercizio3 es=new Esercizio3();
        es.printMaxProd(args[0]);

    }

    /*
    metodo che si occupa di leggere il numero da file
    e stampa il massimo prodotto ottenibile
     */
    public void printMaxProd(String input){
        int nVal;
        Locale.setDefault(Locale.US);
        try{
            Scanner scanner=new Scanner(new FileReader(input));
            nVal=scanner.nextInt();
            for(int i=0; i<nVal; i++){
                int numero=scanner.nextInt();
                System.out.println(MaxProdotto(numero));

            }
        }catch(IOException ex){
            System.out.println(ex);
        }
    }

    /*
    metodo che permette di calcolare il massimo prodotto ottenibile tra gli m elementi
    la cui somma è pari al numero n passato come parametro del metodo.
    Si considera il numero n formato solamente dalla somma tra elementi pari a 2 o 3, il cui
    prodotto poi è il massimo possibile
     */
    public long MaxProdotto(int n){
        if(n==0 || n==1)
            return n;
        if(n==2 || n==3)
            return n-1;

        long prod=1;
        while(n>0){
            if(n%3==0){
                prod*=3;
                n-=3;
            }else{
                prod*=2;
                n-=2;
            }
        }
        return prod;

    }
}
