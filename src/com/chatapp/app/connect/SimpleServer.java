package com.chatapp.app.connect;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SimpleServer implements Sendable, Server, StatusCheckable, Closeable {
    private ServerSocket serverSocket;
    private ReceivedListener receivedListener;
    private Thread serverThread;
    private List<Client> clients;
    private static class Client implements Closeable {
        public Socket socket;
        public Thread thread;
        public BufferedReader reader;
        public BufferedWriter writer;
        public Client() {

        }
        public Client(Socket socket, Thread thread) {
            this.socket = socket;
            this.thread = thread;
            try {
                this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                this.writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        @Override
        public void close() throws IOException {
            this.reader.close();
            this.writer.close();
            this.socket.close();
        }
    }
    @Override
    public void setReceivedListener(ReceivedListener listener) {
        this.receivedListener = listener;
        this.clients = new ArrayList<>();
    }

    @Override
    public void send(String message) {
        for (Client client : clients) {
            try {
                BufferedWriter writer = client.writer;
                writer.write(message);
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void bind(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            Thread serverThread = new Thread(() -> {
               while (!serverSocket.isClosed()) {
                   try {
                       Socket clientSocket = this.serverSocket.accept();
                       if (serverSocket.isClosed()) {
                           break;
                       }
                       Client client = new Client();
                       BufferedReader clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                       BufferedWriter clientWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                       client.socket = clientSocket;
                       client.reader = clientReader;
                       client.writer = clientWriter;
                       Thread clientThread = new Thread(() -> {
                           try {
                               while (true) {
                                   String message = client.reader.readLine();
                                   if (message.contentEquals(":exit")) {
                                       client.close();
                                       clients.remove(client);
                                       // 쓰레드 종료 전 정리
                                       break;
                                   } else {
                                       receivedListener.received(message);
                                   }
                               }
                           } catch (IOException ioe) {
                               ioe.printStackTrace();
                           }
                       });
                       clientThread.start();
                       clients.add(client);
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
            });
            serverThread.start();
            this.serverThread = serverThread;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void shutdown() {
        send(":exit");
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isReady() {
        return serverSocket.isClosed();
    }

    @Override
    public void close() throws IOException {
        shutdown();
    }
}
