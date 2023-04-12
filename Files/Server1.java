import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {
    public static void main (String [] args){
        //al suo avvio si mette in attesa di connessioni all'ingresso
        try{
            ServerSocket listener=new ServerSocket(1555);
            System.out.println("Listening");
            //blocca il server finche non arriva una richiesta di connessione 
            Socket socket =listener.accept();
            System.out.println("Connected");

            InputStream socketInput=socket.getInputStream();
            //flusso di dati in uscita
            OutputStream socketOutput= socket.getOutputStream();

            //funzione di codifica e decodifica dei caratteri in maniera automatica, 
            //legge e scrive caratteri al posto di Byte
            InputStreamReader socketReader= new InputStreamReader(socketInput);
            OutputStreamWriter socketWriter = new OutputStreamWriter(socketOutput);

            //leggere e scrivere intere righe dal server come se lo stessimo facedno da console
            BufferedReader fromClient= new BufferedReader(socketReader);
            PrintWriter toClient = new PrintWriter(socketWriter);

            //crea thread di invio
            Sender clientSender=new Sender(toClient);
            Thread senderThread=new Thread(clientSender);
            senderThread.start();

            String message="";
            while(!message.equals("quit")){
                message=fromClient.readLine();
                System.out.println("Server: " + message);
                toClient.flush();
               
            }

            listener.close();
            socket.close();

        }catch(Exception e){
            e.printStackTrace();
 
        }
    }
    
}
