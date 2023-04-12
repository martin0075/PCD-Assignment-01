import java.util.Scanner;

class RefrigeratorManager {
    public static void main(String[] args) {
        // preparo qualche item a mano
        Item[] currentItems = new Item[4];
        currentItems[0] = new Item("pollo", 20200314, true);
        currentItems[1] = new Item("minestrone", 20200312, true);
        currentItems[2] = new Item("insalata", 20200316, false);
        currentItems[3] = new Item("mozzarella", 20200310, false);
        // creo un frigorifero come da consegna
        Refrigerator refrigerator = new Refrigerator(3, 4, 1, 5);

        // ora li metto nel frigo
        for (int i = 0; i < currentItems.length; i++) {
            refrigerator.putItem(currentItems[i]);
        }
        // butto via gli item scaduti decidendo una data
        System.out.println("\ninserisci la data minima di scadenza che devono avere gli alimenti");
        Scanner scan = new Scanner(System.in);
        int cleaningDateLimit = scan.nextInt();
        scan.nextLine();
        refrigerator.clean(cleaningDateLimit);

        // stampo cosa è rimasto nel frigo
        refrigerator.printNormalShelfs();
        refrigerator.printFreezingShelfs();
 
        // faccio mettere una lista di item creata da tastiera
        while (true) {
        
            System.out.println("\nAggiungere un nuovo elemento?");
            String answer = scan.nextLine();
            if (answer.equals("no")) {
                break;
            }

            System.out.println("Inserisci un nome: ");
            String name = scan.nextLine();
            System.out.println("Inserisci la data di scadenza ( aaaammgg ): ");
            int date = scan.nextInt();
            scan.nextLine();
            System.out.println("deve andare in congelatore?: si/no ");
            String isFreezeAnswer = scan.nextLine();
            boolean freeze = false;
            if (isFreezeAnswer.equals("si")) {
                freeze = true;
            }
            Item item = new Item(name, date, freeze);
            refrigerator.putItem(item);
            System.out.println("\nelemento aggiunto: " + item.toString());
            
        }
        scan.close();
        // stampo cosa è rimasto nel frigo
        refrigerator.printNormalShelfs();
        refrigerator.printFreezingShelfs();

    }
}