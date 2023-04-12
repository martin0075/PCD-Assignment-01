import java.io.PrintWriter;
import java.util.Scanner;

public class Sender implements Runnable{
    private PrintWriter toServer;// Stream su cui inviare stringhe
    public Sender(PrintWriter pw)
    {
        this.toServer= pw;
    }

    @Override
    public void run() {
        
        Scanner userInput=new Scanner(System.in);
        String userMessage="";
        while(!Thread.interrupted())//continua finche non viene interrotto dal thread principale
        {
            userMessage=userInput.nextLine(); // leggi da console (operazione bloccante in quanto aspetta inserimento)
            toServer.println(userMessage);
            toServer.flush();
        }
        userInput.close();
    }

    
}
