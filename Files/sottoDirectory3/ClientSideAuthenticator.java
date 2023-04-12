package it.unibo.ds.lab.presentation.client;

import com.google.gson.Gson;
import it.unibo.ds.presentation.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

import static it.unibo.ds.presentation.Response.Status.*;

public class ClientSideAuthenticator implements Authenticator {

    private final InetSocketAddress server;

    public ClientSideAuthenticator(String host, int port) {
        this.server = new InetSocketAddress(host, port);
    }

    //La registrazione è una procedura che restituisce una risposta vuota
    @Override
    public void register(User user) throws BadContentException, ConflictException {
        try {
            //metodo che accetta un oggetto che accetta la richiesta e il tipo della risposta
            rpc(new RegisterRequest(user), EmptyResponse.class);
        } catch (WrongCredentialsException e) {
            throw new IllegalStateException("Inconsistent behaviour of server: unexpected WrongCredentialsException", e);
        }
    }

    @Override
    public Token authorize(Credentials credentials) throws BadContentException, WrongCredentialsException {
        try {
            return rpc(new AuthorizeRequest(credentials), AuthorizeResponse.class);
        } catch (ConflictException e) {
            throw new IllegalStateException("Inconsistent behaviour of server: unexpected ConflictException", e);
        }
    }

    private <T, R> R rpc(Request<T> request, Class<? extends Response<R>> responseType) throws BadContentException, ConflictException, WrongCredentialsException {
        try (var socket = new Socket()) {
            socket.connect(server);
            marshallRequest(socket, request);
            return unmarshallResponse(socket, responseType);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private <T> void marshallRequest(Socket socket, Request<T> request) throws IOException {
        try{
           Gson gson= GsonUtils.createGson();
           //String serializedReq=gson.toJson(request);
           //DataOutputStream writer= new DataOutputStream(socket.getOutputStream());
           //writer.writeUTF(serializedReq);//modo non ottimale poichè prima si serializza tutta la stringa in memoria e poi viene inviata
           OutputStreamWriter writer=new OutputStreamWriter(socket.getOutputStream());
           //ogni volta che c'è un carattere pronto da serializzare viene spedito sulla socket
           gson.toJson(request, writer);
           writer.flush();
        }finally{
            socket.shutdownOutput();
        }
    }

    private <T> T unmarshallResponse(Socket socket, Class<? extends Response<T>> responseType) throws IOException, BadContentException, ConflictException, WrongCredentialsException {
        try{
            Gson gson=GsonUtils.createGson();
            InputStreamReader reader= new InputStreamReader(socket.getInputStream());
            Response<T> response=responseType.cast(gson.fromJson(reader, responseType));
            switch(response.getStatus()){
                case OK:
                    return response.getResult();
                case CONFLICT:
                    throw new ConflictException();
                case BAD_CONTENT:
                    throw new BadContentException();
                case WRONG_CREDENTIALS:
                    throw new WrongCredentialsException();


            }
            return response.getResult();
        }finally{
            socket.shutdownInput();
        }
    }
}
