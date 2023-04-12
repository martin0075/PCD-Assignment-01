package Client;

import shared.Enumz.GameCommand;
import shared.Model.BaseInitArgument;
import shared.PlayerInfo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

public class GameHandler {
    private GameScreen gameScreen;
    //socket
    private ClientSocketSender clientSocketSender;
    private ClientSocketHandler socketHandler;
    private PlayerInfo playerInfo;

    public GameHandler(String username) {
        istanceClientSocket();
        //registrare il giocatore al server
        playerInfo = new PlayerInfo(username, BaseInitArgument.port);
        if (!clientSocketSender.SendMessage(playerInfo)) return;
        //partire il gameHandlerInit per far partire il gioco
        GameHandlerInit(username);
    }

    public void GameHandlerInit(String username) {
        //riguardare se questo serve / da spostare in un altro punto
        istanceSockets();

        //qui accetti il messaggio
        List<PlayerInfo> lista_player = socketHandler.ResetSocket();
        playerInfo = lista_player.stream().filter(playerInfo1 -> playerInfo1.getUuid().equals(playerInfo.getUuid())).findFirst().get();

        switch (playerInfo.getGameCommand()) {
            case pause:
                gameScreen.setGameRunning(!gameScreen.isGameRunning());
                break;
            case win:
            case gameOver:
                gameScreen.interrupt();
                closeSockets();
                playerInfo = null;
                break;
            case quit:
                resetUI();
                closeSockets();
                break;
            case notStart:
                closeSockets();
                playerInfo = null;
                break;
            case restart:
                resetUI();
                playerInfo.setPunteggio(0);
                playerInfo.setGameCommand(GameCommand.start);

                lista_player = lista_player.stream().filter(playerInfo1 -> !playerInfo1.getUuid().equals(playerInfo.getUuid())).collect(Collectors.toList());
                for (PlayerInfo playerInfo : lista_player) {
                    playerInfo.setPunteggio(0);
                    playerInfo.setGameCommand(GameCommand.start);
                }
                StartGame(username, lista_player, playerInfo);
                break;
            default:
                //tutti i player tranne me
                lista_player = lista_player.stream().filter(playerInfo1 -> !playerInfo1.getUuid().equals(playerInfo.getUuid())).collect(Collectors.toList());
                if (gameScreen == null && playerInfo.getGameCommand() == GameCommand.start) {
                    //qui inizializzi il gioco GameScreen
                    StartGame(username, lista_player, playerInfo);
                } else {
                    try {
                        String methodName = playerInfo.getGameCommand().toString();
                        System.out.println("Comando arrivato " + methodName);
                        Method m1 = gameScreen.getClass().getMethod(methodName, PlayerInfo.class, List.class);
                        m1.invoke(gameScreen, playerInfo, lista_player);
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
        if (playerInfo != null &&
                (playerInfo.getGameCommand() != GameCommand.gameOver
                        && playerInfo.getGameCommand() != GameCommand.notStart
                        && playerInfo.getGameCommand() != GameCommand.win
                        && playerInfo.getGameCommand() != GameCommand.quit))
            GameHandlerInit(username);
    }

    private void resetUI() {
        gameScreen.setGameRunning(true);
        gameScreen.setGameOver(true);
        try {
            Thread.sleep(1100); // messo thread sleep per dare il tempo di poter uscire dal while del gameloop
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameScreen.InterruptThreadAndCloseUI();
        gameScreen.interrupt();
    }

    private void StartGame(String username, List<PlayerInfo> playerInfos, PlayerInfo playerInfo) {
        gameScreen = new GameScreen(clientSocketSender, playerInfos, playerInfo);
        gameScreen.start();
    }

    private void istanceSockets() {
        istanceSocketHandllerr();
        istanceClientSocket();
    }

    private void istanceSocketHandllerr() {
        if (socketHandler == null)
            socketHandler = new ClientSocketHandler(BaseInitArgument.ip, BaseInitArgument.port);
    }

    private void istanceClientSocket() {
        if (clientSocketSender == null)
            clientSocketSender = new ClientSocketSender(BaseInitArgument.ip, BaseInitArgument.port + 1);
    }

    private void closeSockets() {
        this.socketHandler.CloseConnection();
        this.clientSocketSender.CloseSocket();
        this.socketHandler = null;
        this.clientSocketSender = null;
    }
}
