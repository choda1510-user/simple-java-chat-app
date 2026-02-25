package com.chatapp.app.connect;

public interface Server extends Sendable, StatusCheckable {
    void bind(int port); // 논블록
    void setReceivedListener(ReceivedListener listener); // 블록
    void shutdown(); // 논블록
    void setBindExceptionHandler(BindExceptionHandler bindExceptionHandler); // 블록
}
