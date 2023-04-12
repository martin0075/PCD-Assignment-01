package shared;

import Utils.JsonObjectUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class BaseSocketSender {
    //protected byte[] data;
    public Socket socket;
    private String hostName;
    private int port;

    public BaseSocketSender(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

//    private void ConnectIfIsClosed(){
//        try{
//            if(socket.isClosed()){
//                SocketAddress socketAddress = new InetSocketAddress(port);
//                socket.connect(socketAddress);
//                System.out.println("Connected");
//            }
//            else{
//                System.out.println("Already connected");
//            }
//        }
//        catch (IOException err){
//            System.out.println(err.getMessage());
//        }
//    }

    public void SendMessage(Object objToSend) {
        try {
            //https://stackoverflow.com/questions/8465394/how-to-correctly-close-a-socket-and-then-reopen-it
            socket = new Socket(hostName, port);// riapro la porta perchè è l'unico modo che ho per ricomunicare su
            socket.setSoTimeout(Integer.MAX_VALUE);
            //di una socket precedentemente chiusa (post try catch riga 48)
            //socket.setSoTimeout(5000);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            CloseSocket();
        }

        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            System.out.println("Invio dati socket");
            out.write(JsonObjectUtil.ObjectToJson(objToSend));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseSocket();
        }
    }

    protected void CloseSocket() {
        try {
            if (socket != null)
                socket.close();//Chiudi la connessione
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
