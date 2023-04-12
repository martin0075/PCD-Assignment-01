package shared;

import Utils.JsonObjectUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
//import java.net.ServerSocket;


public abstract class BaseSocketReceiver {
    protected byte[] data;
    protected ServerSocket server;
    private String hostName;
    private int port;
    private Socket socket;

    public BaseSocketReceiver(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
        try {
            server = new ServerSocket(port);
            server.setSoTimeout(Integer.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> T GetSocketData(Class<T> type) {
        try (InputStream stream = socket.getInputStream()) {
            data = stream.readAllBytes();
            //stream.close();
            if (data == null)
                return null;

            String msg = new String(data, StandardCharsets.UTF_8);
            //TODO Serve per il debug. CONTROLLARE PRIMA DI CONSEGNARE
            System.out.println("Dati arrivati");
            System.out.println(msg);
            return (T) JsonObjectUtil.JsonToObject(type, msg);
        } catch (IOException e) {
            e.printStackTrace();
            // exception handling code
        }
        return null;
    }

    public String GetCLientIp() {
        String ip = (((InetSocketAddress) socket.getRemoteSocketAddress()).getAddress()).toString().replace("/", "");
        return ip;
    }

//    public void send_message(String json) {
//        try {
//            //SETUP
//            //hostName="locahost";
//            //port=5555;
//            //Creazione socket, connessione a localhost:6789
//             // stabilisce la connessione
//
//            s= socket.accept();
//            InetAddress ind = InetAddress.getByName(hostName);
//            String hostAddress = ind.getHostAddress();
//
//            System.out.println("Connected");
//            //Otteniamo gli stream
//            DataInputStream dis=new DataInputStream(s.getInputStream());
//            InputStream socketInput = s.getInputStream();
//            OutputStream socketOutput = s.getOutputStream();
//
//            InputStreamReader socketReader = new InputStreamReader(socketInput);
//            OutputStreamWriter socketWriter = new OutputStreamWriter(socketOutput);
//
//            BufferedReader fromServer = new BufferedReader(socketReader); //Legge stringhe dal socket
//            PrintWriter toServer = new PrintWriter(socketWriter); //Scrive stringhe sul socket
//
//
//            toServer.write(json);
//
//
//            //Creazione del thread di invio messaggi
//            Sender clientSender = new Sender(toServer);
//            Thread senderThread = new Thread(clientSender);
//            senderThread.start();
//            /*BufferedInputStream input = new BufferedInputStream(socket.getInputStream());
//            boolean done=false;
//            while (!done) {
//                // TODO: Rename btrar to something more meaningful
//                int bytesRead = input.read(btrar);
//                // Do something with the data...
//            }*/
//            String line = fromServer.lines().collect(Collectors.joining());
//            data = line.getBytes(StandardCharsets.UTF_8);// memorizza i dati
//            do_action();
//
//            //LOGICA APPLICATIVA - RICEZIONE MESSAGGI
////            String message = "";
////            while(message != null && !message.equals("quit")) { //Finché il server non chiude la connessione o non ricevi un messaggio "quit"...
////                message = fromServer.readLine(); //Leggi un messaggio inviato dal server (bloccante!)
////                System.out.println( "Host address: " + hostAddress );
////
////                if (message != null) {
////                    System.out.println("Server.Server: " + message);
////                }
////            }
//
//            senderThread.interrupt(); //Chiedi al senderThread di fermarsi
//            socket.close(); //Chiudi la connessione
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    public abstract void do_action();


    public void SetSocket() {
        try {
            socket = server.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerSocket getServer() {
        return server;
    }

    public Socket getSocket() {
        return socket;
    }

    //Chiude solo la connessione
    //Se il client vuole inviare di nuovo qualcosa deve riaprirla
    public void CloseSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Killa tutta la socket
    public void CloseServer() {
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}