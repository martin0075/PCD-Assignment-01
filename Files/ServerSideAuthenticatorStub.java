package it.unibo.ds.lab.presentation.server;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import it.unibo.ds.presentation.*;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class ServerSideAuthenticatorStub extends Thread {

    private final Socket ephemeralSocket;
    private final Authenticator delegate;

    public ServerSideAuthenticatorStub(Socket socket, Authenticator delegate) {
        this.ephemeralSocket = Objects.requireNonNull(socket);
        this.delegate = Objects.requireNonNull(delegate);
    }

    @Override
    public void run() {
        try (ephemeralSocket) {
            var request = unmarshallRequest(ephemeralSocket);
            var response = computeResponse(request);
            marshallResponse(ephemeralSocket, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Request<?> unmarshallRequest(Socket socket) throws IOException {
        try{
            Gson gson=GsonUtils.createGson();
            InputStreamReader reader= new InputStreamReader(socket.getInputStream());
            Request<?> request=gson.fromJson(reader,Request.class);
            System.out.println(request);
            return request;
        }finally{
            socket.shutdownInput();
        }
    }

    private Response<?> computeResponse(Request<?> request) {

        switch (request.getMethod()){
            case "authorize":
                try{
                    Token result=delegate.authorize((Credentials) request.getArgument());
                    return new AuthorizeResponse(Response.Status.OK,"",result);
                } catch (BadContentException e) {
                    return new AuthorizeResponse(Response.Status.BAD_CONTENT,"",null);
                } catch (WrongCredentialsException e) {
                    return new AuthorizeResponse(Response.Status.WRONG_CREDENTIALS,"",null);
                }
            case "register":
                try{
                    delegate.register((User)request.getArgument());
                    return new EmptyResponse(Response.Status.OK,"ok");
                }
                catch (BadContentException e) {
                    return new EmptyResponse(Response.Status.BAD_CONTENT,"");
                } catch (ConflictException e) {
                    return new EmptyResponse(Response.Status.CONFLICT,"");
                }

            default:
                throw new Error("This should never happen");
        }
    }

    private void marshallResponse(Socket socket, Response<?> response) throws IOException {
        try{
            Gson gson= GsonUtils.createGson();
            //String serializedReq=gson.toJson(request);
            //DataOutputStream writer= new DataOutputStream(socket.getOutputStream());
            //writer.writeUTF(serializedReq);//modo non ottimale poichè prima si serializza tutta la stringa in memoria e poi viene inviata
            OutputStreamWriter writer=new OutputStreamWriter(socket.getOutputStream());

            //ogni volta che c'è un carattere pronto da serializzare viene spedito sulla socket
            gson.toJson(response, writer);
            writer.flush();
        }finally{
            socket.shutdownOutput();
        }
    }
}
