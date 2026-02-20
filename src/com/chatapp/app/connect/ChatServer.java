package com.chatapp.app.connect;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ChatServer implements Server, Sendable {
    private ServerSocket serverSocket;
    private List<Socket> clientSockets;
    private List<Sendable> senders;
    private ReceivedListener listener;
    public ChatServer(ReceivedListener listener) {
        this.clientSockets = Collections.synchronizedList(new LinkedList<>());
        this.senders = Collections.synchronizedList(new LinkedList<>());
        this.listener = listener;
        if (listener == null) throw new NullPointerException();
    }

    @Override
    public void bind(int port) {
        synchronized (this) {
            if (serverSocket != null && !serverSocket.isClosed()) {
                // 서버가 켜저 있어서 동작 거부
                return;
            }
        }
        ChatServerEventQueue.invoke(() -> {
            Thread serverThread;
            try {
                synchronized (this) {
                    serverSocket = new ServerSocket(port);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            serverThread = new Thread(new ChatServerRutin(this, serverSocket, clientSockets, listener, senders));
            serverThread.start();
        });
    }

    @Override
    public void setReceivedListener(ReceivedListener listener) {
        this.listener = listener != null ? listener : this.listener;
    }

    @Override
    public void shutdown() {
        ChatServerEventQueue.invoke(() -> {
            try {
                synchronized (this) {
                    if (serverSocket != null && !serverSocket.isClosed()) {
                        serverSocket.close();
                        serverSocket = null;
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
            return serverSocket == null || serverSocket.isClosed();
        }
    }

    @Override
    public void send(String message) {
        senders.forEach((sender) -> sender.send(message));
    }
}
