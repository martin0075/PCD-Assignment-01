/*
*********************************
NOME - Filippo
COGNOME - Brajucha
NUM MATRICOLA - 0000920975
*********************************
*/

import java.util.*;



public class POS{

    static Scanner scan;
    static ArrayList<Conto> Conti;
    static Conto c;
    private static boolean b;

    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);

        Conti = new ArrayList<Conto>();
        Conto conto1 = new Conto("00000001", 123456, 10000);
        Conto conto2 = new Conto("00000002", 123456, 10000);
        Conto conto3 = new Conto("00000003", 123456, 10000);
        Conto conto4 = new Conto("00000004", 123456, 10000);
        Conto conto5 = new Conto("00000005", 123456, 10000);
        Conti.add(conto1);
        Conti.add(conto2);
        Conti.add(conto3);
        Conti.add(conto4);
        Conti.add(conto5);

        //try{
            boolean continua = true;
            int posConto, possConto;
            boolean b;

            while(continua){
                System.out.println("-------------------------------------------");
                System.out.println("Ciao,");
                System.out.println("Cosa vuoi fare:");
                System.out.println("*****STAMPA SALDO >> s");
                System.out.println("*****PRELIEVO >> p");
                System.out.println("*****RICARICA CONTO >> r");
                System.out.println("*****ESCI >> q");                
                System.out.println("-------------------------------------------");
                String v = scan.next();

                switch(v){

                    case "s":
                        posConto = autenticazione();
                        if(posConto>=0){
                            c = Conti.get(posConto);
                            System.out.println(c.getSaldo()); 
                        }
                        break;


                    case "p":
                        posConto = autenticazione();

                        if(posConto>=0){
                        System.out.print("Quanto vuoi prelevare? ");
                        double sommaPrelievo = scan.nextDouble();

                        Conti.get(posConto).Prelievo(sommaPrelievo);
                        }
                        break;


                    case "r":
                        posConto = autenticazione();

                        if(posConto>=0){
                            System.out.print("Quanto vuoi ricaricare? ");
                            double sommaRicarica = scan.nextDouble();
                            System.out.print("Che conto vuoi ricaricare? ");
                            String IBAN = scan.next();
                            IBAN = compilaZeri(IBAN);

                            b = Conti.get(posConto).ricaricaConto(sommaRicarica);
                            if(b==true){
                                possConto = trovaConto(IBAN);
                                if(possConto >= 0){
                                    Conti.get(possConto).aggiungiConto(sommaRicarica);
                                }
                            }
                            
                        }
                        break;


                    case "q":
                        continua = false;
                        break;


                    default:
                        //break;
                        System.out.println("Errore di inserimento");
                }
            }

        /*} 
        catch(InputMismatchException e){
            System.out.println("Errore inserisci correttamente i dati");
        }

        catch(Exception e){
            System.out.print(e.getMessage());
        }*/
    }



    public static String compilaZeri(String IBAN){
		int g = IBAN.length();
        for(int i=0; i<(8-g); i++){
            IBAN = "0" + IBAN;
        }
        return IBAN;
    }


    public static int autenticazione(){
		Scanner scan = new Scanner(System.in);
        System.out.print("Inserisci IBAN - ");
        String IBAN = scan.next();
        System.out.println();
        
        System.out.print("Inserisci PIN - ");
        int pin = scan.nextInt();
        System.out.println();
		
		IBAN = compilaZeri(IBAN);
        int indice = 0;
        boolean n = false;

        for(int i = 0; i<Conti.size(); i++){
            c = Conti.get(i);
            
            while(IBAN.equals(c.getIBAN()) && pin==c.getPin()){
                n = true;
                indice = i;
                break;
            };
        }

        if(n == false){
            System.out.println("*Errore nell'inserimento dell'IBAN o del pin*");
            indice = -1;
        }

        return indice;
    }

    public static int trovaConto(String IBAN){
        int indice = 0;
        boolean n = false;

        for(int p = 0; p<Conti.size(); p++){
            c = Conti.get(p);
            
            while(IBAN.equals(c.getIBAN())){
                n = true;
                indice = p;
                break;
            };
        }

        if(n == false){
            System.out.println("*Errore nell'inserimento dell'IBAN*");
            indice = -1;
        }
        return indice;

    }
}