package com.chatapp.app.connect;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class ChatServerRutin implements Runnable {
    private final Object lock;
    private final ServerSocket serverSocket;
    private final List<Socket> clientSockets;
    private final ReceivedListener listener;
    private final List<Sendable> senders;
    private CloseListener closeListener;
    private static class DefaultCloseListener implements CloseListener {
        private final List<Socket> clientSockets;
        private final List<Sendable> senders;
        private final Socket clientSocket;

        private DefaultCloseListener(List<Socket> clientSockets, List<Sendable> senders, Socket clientSocket) {
            this.clientSockets = clientSockets;
            this.senders = senders;
            this.clientSocket = clientSocket;
        }

        @Override
        public void closed(Sendable sender) {
            clientSockets.remove(clientSocket);
            senders.remove(sender);
        }
    }

    public ChatServerRutin(Object lock, ServerSocket serverSocket, List<Socket> clientSockets, ReceivedListener listener, List<Sendable> senders) {
        this.lock = lock;
        this.serverSocket = serverSocket;
        this.clientSockets = clientSockets;
        this.listener = listener;
        this.senders = senders;
    }
    public ChatServerRutin(Object lock, ServerSocket serverSocket, List<Socket> clientSockets, ReceivedListener listener, CloseListener closeListener, List<Sendable> senders) {
        this.lock = lock;
        this.serverSocket = serverSocket;
        this.clientSockets = clientSockets;
        this.listener = listener;
        this.senders = senders;
        this.closeListener = closeListener;
    }
//
//    @Override
//    public void run() {
//        while (true) {
//            try {
//                Socket clientSocket = serverSocket.accept();
//                ChatServerConnRutin clientRutin = new ChatServerConnRutin(clientSocket, clientSockets, senders, listener);
//                Thread clientThread = new Thread(clientRutin);
//                clientSockets.add(clientSocket);
//                senders.add(clientRutin);
//                clientThread.start();
//            } catch (SocketException socketException) {
//                ChatServerEventQueue.invoke(this::shutdownAll);
//                break;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                ChatClientRutin clientRutin = new ChatClientRutin(lock, clientSocket, listener, new DefaultCloseListener(clientSockets, senders, clientSocket));
                Thread clientThread = new Thread(clientRutin);
                clientSockets.add(clientSocket);
                senders.add(clientRutin);
                clientThread.start();
            } catch (SocketException socketException) {
                ChatServerEventQueue.invoke(this::shutdownAll);
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void shutdownAll() {
        for (Socket clientSocket : clientSockets) {
            try {
                synchronized (lock) {
                    if (!clientSocket.isClosed()) {
                        clientSocket.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        clientSockets.clear();
        senders.clear();
    }

    public CloseListener getCloseListener() {
        return closeListener;
    }

    public void setCloseListener(CloseListener closeListener) {
        this.closeListener = closeListener;
    }
}
