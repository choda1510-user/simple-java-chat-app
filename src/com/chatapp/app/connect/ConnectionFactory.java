package com.chatapp.app.connect;

public class ConnectionFactory {
    public Client getClient() {
        return new SimpleClient();
    }
    public Server getServer() {
        return new SimpleServer();
    }
}
