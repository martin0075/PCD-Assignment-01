package Server;

import shared.BaseSocketReceiver;

public class ServerSocketReceiver extends BaseSocketReceiver {
    private boolean isReadyToStart; //implements IServerSocket{
    private boolean isGameStarted;

    public ServerSocketReceiver(String hostName, int port) {
        super(hostName, port);
    }

    @Override
    public void do_action() {
    }

    public void setIsStared(boolean value) {
        isGameStarted = value;
    }

    public boolean isGameStarted() {
        return isGameStarted;
    }

    public boolean IsReadyToStart(int playerSize) {
        return isReadyToStart = playerSize >= 2 && playerSize <= 4;

        //SocketServerReceiver.connesso=false;
       /* if(players.size()>2&&players.size()<4)
        {
            SocketServerReceiver.connesso=true;
        }
        return SocketServerReceiver.connesso;*/
    }
}
