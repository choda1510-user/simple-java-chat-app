package com.chatapp.app.connect;

public class ConnectionFactory {
    public Client getClient(ReceivedListener receivedListener) {
        return new ChatClient(receivedListener);
    }
    public Server getServer(ReceivedListener receivedListener) {
        return new ChatServer(receivedListener);
    }
}
