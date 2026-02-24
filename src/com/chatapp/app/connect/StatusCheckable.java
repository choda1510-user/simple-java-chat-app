package com.chatapp.app.connect;

public interface StatusCheckable {
    boolean canRunning(); // 블록 이미 실행중일 때 false
}
