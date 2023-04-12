import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


public class Client1 {
    public static void main(String[] args){
        try{
            Socket socket= new Socket ("Localhost", 1555);
            System.out.print("Connected");
            //Otteniamo gli stream, flusso di dati in ingresso
            InputStream socketInput=socket.getInputStream();
            //flusso di dati in uscita
            OutputStream socketOutput= socket.getOutputStream();

            //funzione di codifica e decodifica dei caratteri in maniera automatica, 
            //legge e scrive caratteri al posto di Byte
            InputStreamReader socketReader= new InputStreamReader(socketInput);
            OutputStreamWriter socketWriter = new OutputStreamWriter(socketOutput);

            //leggere e scrivere intere righe dal server come se lo stessimo facedno da console
            BufferedReader fromServer= new BufferedReader(socketReader);
            PrintWriter toServer = new PrintWriter(socketWriter);
            
            //crea thread di invio
            Sender clientSender=new Sender(toServer);
            Thread senderThread=new Thread(clientSender);
            senderThread.start();
            
            //LOGICA APPLICATIVA
            
            String message="";
            while(!message.equals("quit") && message!=null){//continua finchè il server non chiude la connessione 
                message=fromServer.readLine();
                if(message!=null){
                    System.out.println("Server: "+message);
                }

            }
            
            senderThread.interrupt();
            socket.close();
            
            

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
}
