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
import java.util.Scanner;


class Client{
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

            Scanner userInput= new Scanner(System.in);
            String command="";
            while(!command.equals("quit")){
                command=userInput.nextLine();
                toServer.println(command);
                toServer.flush();
                String response=fromServer.readLine();
                System.out.println(response);
            }

            socket.close();
            userInput.close();


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
