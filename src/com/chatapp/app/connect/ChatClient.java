package com.chatapp.app.connect;

import java.io.IOException;
import java.net.Socket;

public class ChatClient implements Client, Sendable {
    private Socket clientSocket;
    private ReceivedListener listener;
    private Sendable sender;
    public ChatClient(ReceivedListener listener) {
        this.listener = listener;
        if (listener == null) throw new NullPointerException();
    }
    @Override
    public void connect(String serverIP, int serverPort) {
        synchronized (this) {
            if (clientSocket != null && !clientSocket.isClosed()) {
                return;
            }
        }
        ChatServerEventQueue.invoke(() -> {
            synchronized (this) {
                try {
                    clientSocket = new Socket(serverIP, serverPort);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                ChatClientRutin rutin = new ChatClientRutin(this, clientSocket, listener);
                Thread clientThread = new Thread(rutin);
                clientThread.start();
                this.sender = rutin;
            }
        });
    }

    @Override
    public void setReceivedListener(ReceivedListener listener) {
        this.listener = listener != null ? listener : this.listener;
    }

    @Override
    public void disconnect() {
        ChatServerEventQueue.invoke(() -> {
            try {
                synchronized (this) {
                    if (clientSocket != null && !clientSocket.isClosed()) {
                        clientSocket.close();
                        clientSocket = null;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public boolean isReady() {
        synchronized (this) {
            return clientSocket == null || clientSocket.isClosed();
        }
    }

    @Override
    public void send(String message) {
        ChatServerEventQueue.invoke(() -> {
            sender.send(message);
        });
    }
}
