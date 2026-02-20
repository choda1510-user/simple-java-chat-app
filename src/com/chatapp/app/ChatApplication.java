package com.chatapp.app;

import com.chatapp.app.config.GuiConfig;
import com.chatapp.app.config.SwingConfig;

/**
 * 사용자는 두 채팅창을 볼 수 있다.
 * 왼쪽은 자신이 호스트하는 채팅방을 본다.
 * 오른쪽은 다른 채팅방에 접속했을 때 채팅방을 보여준다.
 */
public class ChatApplication {
    private final GuiConfig guiConfig;
    public ChatApplication(String[] args) {
        this.guiConfig = new SwingConfig();
        this.guiConfig.show();
    }
}
