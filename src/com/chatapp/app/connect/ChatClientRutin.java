package com.chatapp.app.connect;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ChatClientRutin implements Runnable, Sendable {
    private final Object lock;
    private final Socket socket;
    private ReceivedListener listener;
    private BufferedWriter bw;
    private CloseListener closeListener;

    public ChatClientRutin(Object lock, Socket socket, ReceivedListener listener) {
        this.lock = lock;
        this.socket = socket;
        this.listener = listener;
    }
    public ChatClientRutin(Object lock, Socket socket, ReceivedListener listener, CloseListener closeListener) {
        this.lock = lock;
        this.socket = socket;
        this.listener = listener;
        this.closeListener = closeListener;
    }

    @Override
    public void run() {
        try (
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();
                InputStreamReader isr = new InputStreamReader(is);
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedReader br = new BufferedReader(isr);
                BufferedWriter bw = new BufferedWriter(osw)
        ) {
            synchronized (lock) {
                this.bw = bw;
            }
            while (true) {
                try {
                    String message;
                    message = br.readLine();
                    if (message == null) {
                        throw new SocketException();
                    }
                    listener.received(message);
                } catch (SocketException e) { // 소켓을 닫으면 이 소켓과 관련된 모든 블록킹된 io 객체 메소드에서 SocketException이 던져진다. 다만 연결된 상대 소켓까지 예외가 던져진다는 보장은 없다.
                    if (closeListener != null) {
                        closeListener.closed(this);
                    }
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send(String message) {
        synchronized (lock) {
            if (this.bw != null) {
                ChatServerEventQueue.invoke(() -> {
                    try {
                        bw.write(message + "\n");
                        bw.flush();
                    } catch (IOException e) {
                        if (!socket.isClosed()) {

                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        }
    }

    public CloseListener getCloseListener() {
        return closeListener;
    }

    public void setCloseListener(CloseListener closeListener) {
        this.closeListener = closeListener;
    }

}
