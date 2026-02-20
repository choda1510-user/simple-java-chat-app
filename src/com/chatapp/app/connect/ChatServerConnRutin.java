package com.chatapp.app.connect;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class ChatServerConnRutin implements Runnable, Sendable {
    private final Socket clientSocket;
    private final List<Socket> clientSockets;
    private final List<Sendable> senders;
    private BufferedWriter bw;
    private final ReceivedListener listener;

    public ChatServerConnRutin(Socket clientSocket, List<Socket> clientSockets, List<Sendable> senders, ReceivedListener listener) {
        this.clientSocket = clientSocket;
        this.clientSockets = clientSockets;
        this.senders = senders;
        this.listener = listener;
    }

    @Override
    public void run() {
        try (
                InputStream is = clientSocket.getInputStream();
                OutputStream os = clientSocket.getOutputStream();
                InputStreamReader isr = new InputStreamReader(is);
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedReader br = new BufferedReader(isr);
                BufferedWriter bw = new BufferedWriter(osw)
        ) {
            synchronized (clientSocket) {
                this.bw = bw;
            }
            while (true) {
                try {
                    String message = br.readLine();
                    listener.received(message);
                } catch (SocketException e) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        clientSockets.remove(clientSocket);
        senders.remove(this);
    }

    @Override
    public void send(String message) {
        synchronized (clientSocket) {
            if (this.bw != null) {
                ChatServerEventQueue.invoke(() -> {
                    try {
                        bw.write(message + "\n");
                        bw.flush();
                    } catch (IOException e) {
                        if (!clientSocket.isClosed()) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        }
    }
}
