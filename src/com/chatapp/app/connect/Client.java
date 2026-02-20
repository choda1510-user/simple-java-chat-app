package com.chatapp.app.connect;

public interface Client extends StatusCheckable, Disconnectable {
    void connect(String serverIP, int serverPort); // 논블록
    void setReceivedListener(ReceivedListener listener); // 블록
}
