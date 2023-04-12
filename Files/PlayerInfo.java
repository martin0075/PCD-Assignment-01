package shared;

import java.util.UUID;

public class PlayerInfo {
    private UUID uuid;
    private String ip;
    private String username;
    private int punteggio;
    private int port;
    private GameCommand gameCommand;
    private int trashRowCount;

    //Lasciare costruttore vuoto per dare la possibilita' a jackson di deserializzare i json a questa classe
    public PlayerInfo() {
    }

    public PlayerInfo(String username, int port) {
        this.uuid = UUID.randomUUID();
        this.username = username;
        this.punteggio = 0;
        this.port = port;
        this.gameCommand = GameCommand.none;
    }

    public PlayerInfo(String txtUsername, String ip_player) {
        username = txtUsername;
        ip = ip_player;
        punteggio = 0;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void update_punteggio(int punteggio_player) {
        punteggio += punteggio_player;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public GameCommand getGameCommand() {
        return gameCommand;
    }

    public void setGameCommand(GameCommand gameCommand) {
        this.gameCommand = gameCommand;
    }

    public int getTrashRowCount() {
        return trashRowCount;
    }

    public void setTrashRowCount(int trashRowCount) {
        this.trashRowCount = trashRowCount;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "ip='" + ip + '\'' +
                ", username='" + username + '\'' +
                ", punteggio=" + punteggio +
                ", port=" + port +
                '}';
    }
}
