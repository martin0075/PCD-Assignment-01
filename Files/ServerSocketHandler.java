package Server;

import shared.GameCommand;
import shared.PlayerInfo;
import shared.TESTING.ENV;
import shared.TESTING.ENVIRONMENT;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

//TODO Testare bene, per quanto possibile, gli switch dei vari comandi, quindi trashrow, gameover, none, update score e start

public class ServerSocketHandler {
    //public static Queue<PlayerInfo> players;
    public static List<PlayerInfo> players;
    private ServerSocketReceiver socketReceiver;
    private List<ServerSocketSender> connectedCLients;

    public ServerSocketHandler(String host, int port) {
        Init(host, port);

        while (true) {
            //Inizio ascolto del messaggio da parte della socket
            socketReceiver.SetSocket();

            PlayerInfo playerInfo = socketReceiver.GetSocketData(PlayerInfo.class);
            Optional<PlayerInfo> actualPlayerInList = players.stream().filter(playerInfo1 -> playerInfo1.getUuid().equals(playerInfo.getUuid())).findFirst();

//            if (playerInfo != null) {
            switch (playerInfo.getGameCommand()) {
                case gameOver:
                    DisconnectPlayer(playerInfo);
                    break;
                case trashRow:
                    //In caso di debug il client connesso e' sempre 1 quindi vado a prendere direttamente il 1
                    //In caso contrario faccio un il filtro e prendo il giocatore interessato
                    ServerSocketSender trash_to_send = (ENVIRONMENT.env == ENV.DEBUG)
                            ? connectedCLients.get(0)
                            : connectedCLients.stream().filter(connectedClient -> connectedClient.getUuid().equals(playerInfo.getUuid())).findFirst().get();

                    UpdatePlayer(actualPlayerInList.get(), playerInfo, playerInfo.getGameCommand());
                    trash_to_send.SendMessage(players);
                    break;
                default:
                    playerInfo.setIp(socketReceiver.GetCLientIp());
                    SetPlayer(playerInfo, actualPlayerInList);
                    break;
            }
//            }
        }
    }

    private void UpdatePlayer(PlayerInfo playerToUpdate, PlayerInfo playerUpdater, GameCommand gameCommand) {
        playerToUpdate.setPunteggio(playerUpdater.getPunteggio());
        playerToUpdate.setTrashRowCount(playerUpdater.getTrashRowCount());

        if (gameCommand != null)
            playerToUpdate.setGameCommand(gameCommand);
    }

    public void SetPlayer(PlayerInfo playerInfo, Optional<PlayerInfo> playerInfo1) {
        if (playerInfo1.isPresent()) {
            //Aggiorno il punteggio
            UpdatePlayer(playerInfo1.get(), playerInfo, null);
            setPlayerCommand(GameCommand.updateScore);
            //Invio i punteggi a tutti i giocatori
            SendToAll();
        } else {
            //aggiungo il giocatore
            playerInfo.setGameCommand(GameCommand.none);
            players.add(playerInfo);
//            setPlayerCommand(GameCommand.none);

            if ((ENVIRONMENT.env == ENV.DEBUG && connectedCLients.size() == 0) || ENVIRONMENT.env == ENV.PRODUCTION)
                connectedCLients.add(new ServerSocketSender(playerInfo.getIp(), playerInfo.getPort(), playerInfo.getUuid()));
        }

        if (socketReceiver.IsReadyToStart(players.size()) && !socketReceiver.isGameStarted()) {
            //avvio il gioco
            setPlayerCommand(GameCommand.start);
            SendToAll();
            socketReceiver.setIsStared(true);
        }
//
//        try(DataOutputStream dOut = new DataOutputStream(socketReceiver.getSocket().getOutputStream())){
//            dOut.writeUTF("asd");
//            dOut.flush();
//        }
//        catch (IOException err){
//
//        }
        //Le socket dovrebbero essere bidirezionali, forse cosi' puoi inviare un ritorno al client
        //In alternativa aprire una server socket sul client e una socket qui sul server
//        try(PrintWriter out = new PrintWriter(socketReceiver.getSocket().getOutputStream(), true)) {
//            System.out.println("Invio dati socket");
//            out.write(JsonObjectUtil.ObjectToJson(objToSend));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //fare controlli sui player andando a controllare che dimensione della lista sia maggiore di 2 e minore di 4
    }

    private void Init(String host, int port) {
        players = Collections.synchronizedList(new LinkedList<>());
        connectedCLients = Collections.synchronizedList(new LinkedList<>());
        socketReceiver = new ServerSocketReceiver(host, port);
    }

    private void setPlayerCommand(GameCommand gameCommand) {
        players.forEach(playerInfo2 -> playerInfo2.setGameCommand(gameCommand));
    }

    private void SendToAll() {
        for (ServerSocketSender socketSender : connectedCLients) {
            socketSender.SendMessage(players);

            if (ENVIRONMENT.env == ENV.DEBUG)
                break;
        }
    }

    private void DisconnectPlayer(PlayerInfo playerInfo) {
        if (ENVIRONMENT.env == ENV.DEBUG) {
            connectedCLients.clear();
            players.clear();
            socketReceiver.setIsStared(false);
        } else {
            connectedCLients.removeIf(serverSocketSender -> serverSocketSender.getUuid().equals(playerInfo.getUuid()));
            players.removeIf(playerInfo1 -> playerInfo1.getUuid().equals(playerInfo.getUuid()));

            if (connectedCLients.size() == 0 && players.size() == 0)
                socketReceiver.setIsStared(false);
        }
    }
}
