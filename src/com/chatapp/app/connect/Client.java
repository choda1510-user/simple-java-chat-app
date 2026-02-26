package com.chatapp.app.connect;

public interface Client extends Sendable, StatusCheckable, Disconnectable {
    void connect(String serverIP, int serverPort); // 논블록
    void setReceivedListener(ReceivedListener listener); // 블록
    void setConnectExceptionHandler(ConnectExceptionHandler connectExceptionHandler); // 블록
    void setConnectSuccessHandler(ConnectSuccessHandler connectSuccessHandler); // 블록
    void setCloseListener(CloseListener closeListener); // 블록
}
