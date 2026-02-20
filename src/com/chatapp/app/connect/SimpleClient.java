package com.chatapp.app.connect;

import java.io.*;
import java.net.Socket;

public class SimpleClient implements Client, Sendable, Receivable, Closeable {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    @Override
    public void connect(String serverIP, int serverPort) {
        try {
            this.socket = new Socket(serverIP, serverPort);
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    @Override
    public void setReceivedListener(ReceivedListener listener) {

    }

    @Override
    public String receive() {
        try {
            String message = this.reader.readLine();
            if (message.contentEquals(":exit")) {
                disconnect();
            }
            return message;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void send(String message) {
        try {
            this.writer.write(message);
            this.writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void disconnect() {
        this.send(":exit");
        try {
            this.close();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws IOException {
        this.reader.close();
        this.reader = null;
        this.writer.close();
        this.writer = null;
        this.socket.close();
        this.socket = null;
    }

    @Override
    public boolean isReady() {
        return this.socket != null;
    }
}
