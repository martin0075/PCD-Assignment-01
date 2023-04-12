import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
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

            String request="";
            while(!request.equals("quit")){
                request=fromClient.readLine();
                System.out.println("Client requested: " + request);

                String response;
                if(request.equals("date")){
                    Date now=new Date();
                    response= new SimpleDateFormat("dd/MM/yyyy").format(now);
                }else if(request.equals("time")){
                    Date now= new Date();
                    response =new SimpleDateFormat("hh::mm").format(now);
                }else{
                    response="Unknown request";
                }

                toClient.println(response);
                toClient.flush();
            }

            listener.close();
            socket.close();

        }catch(Exception e){
            e.printStackTrace();
 
        }
    }
}
