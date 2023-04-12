package Client;

import shared.PlayerInfo;

import java.util.Arrays;
import java.util.List;

public class ClientSocketHandler extends Thread {

    private ClientSocketReceiver socketReceiver;
    //private PlayerInfo playerInfo;
    private List<PlayerInfo> lista_player;

    public ClientSocketHandler(String host, int port) {
        socketReceiver = new ClientSocketReceiver(host, port);
    }

    public List<PlayerInfo> ResetSocket() {
        socketReceiver.SetSocket();
        //riceve i dati dal server
        lista_player = Arrays.asList(socketReceiver.GetSocketData(PlayerInfo[].class));
        return lista_player;
    }
}
