/*
    Marcolini Martin
    0000921984
    martin.marcolini@studio.unibo.it
 */


import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;
import java.io.IOException;

public class Esercizio1 {
    
    public static void main(String[]args){
        Esercizio1 es=new Esercizio1();
        es.maxVetPalindromo(args[0]);
    }

    /*
    metodo che si occupa di leggere da file dei vettori di interi
    e stampa per ognuno di essi la dimensione del più grande
    sottovettore palindromo
     */
    public void maxVetPalindromo(String input){
        Locale.setDefault(Locale.US);
        char [] vettorePalindromo;
        int nVet;

        try{
            //lettura array da file
            Scanner scan=new Scanner(new FileReader(input));

            nVet = scan.nextInt();
            String riga=scan.nextLine();

            for(int i=0;i<nVet;i++)
            {
                riga=scan.nextLine();
                riga=riga.replaceAll(" ","");
                vettorePalindromo=riga.toCharArray();



                int lengthMaxPalindromo=0;
                int lengthPalindromo=0;
                /*
                i due for servono per individuare il sottovettore
                 */
                for(int j=0;j<vettorePalindromo.length;j++){
                    for(int p=vettorePalindromo.length-1;p>j;p--){
                        /*
                        si verifica se il sottovettore di estremi j e i è palindromo,
                        in caso affermativo la variabile lengthPalindromo prende la lunghezza
                        del sottovettore;
                        successivamente si verifica se il sottovettore palindromo trovato è il
                        più grande possibile
                         */
                        if(Palindromo(vettorePalindromo,j,p)){
                            lengthPalindromo=p-j+1;
                            if(lengthMaxPalindromo<lengthPalindromo)
                                lengthMaxPalindromo=lengthPalindromo;
                        }
                    }
                }
                /*
                se lengthMaxPalindromo è uguale a 0 significa il vettore di n elementi
                è formato da n sottovettori palindromi di un elemento
                 */
                if(lengthMaxPalindromo==0)
                    lengthMaxPalindromo=1;
                System.out.println(lengthMaxPalindromo);

            }

        }catch(IOException ex){
            System.err.println(ex);
        }
    }

   /*
   metodo che verifica se il sottovettore passato è palindromo o meno,
   nel ciclo for vengono settati i due indici i e j al primo e ultimo elemento del sottovettore
   ad ogni passo si verifca se i due elemtenti v[i] e v[j] corrispondono,
   in caso negativo viene restituito false e il sottovettore non è palindromo,
   altrimenti non viene eseguito l'if e viene incrementata la variabile i e decrementata la
   variabile j
    */
    public boolean Palindromo(char[] vet, int s, int e){
        for(int i=s,j=e;i<=(s+e-1)/2;i++,j--){
            if(vet[i]!=vet[j])
                return false;
        }
        return true;
    }

   
}
