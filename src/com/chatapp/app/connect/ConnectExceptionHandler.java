package com.chatapp.app.connect;

import java.net.ConnectException;

public interface ConnectExceptionHandler {
    void handle(ConnectException connectException);
}
