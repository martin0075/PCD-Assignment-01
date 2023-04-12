package Server;

import shared.BaseSocketSender;

import java.util.UUID;

public class ServerSocketSender extends BaseSocketSender {
    private UUID uuid;

    public ServerSocketSender(String hostName, int port, UUID uuid) {
        super(hostName, port);
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

}
