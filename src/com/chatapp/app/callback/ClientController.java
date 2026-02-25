package com.chatapp.app.callback;

import com.chatapp.app.connect.Client;
import com.chatapp.app.gui.SwingClientHeaderPanel;
import com.chatapp.app.gui.SwingTextAreaPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientController implements ActionListener {
    private final Client client;
    private final SwingClientHeaderPanel clientHeaderPanel;
    private final SwingTextAreaPanel textAreaPanel;
    private int state; // 0: disconnect, 1: connecting, 2: connect

    public ClientController(Client client, SwingClientHeaderPanel clientHeaderPanel, SwingTextAreaPanel textAreaPanel) {
        this.client = client;
        this.clientHeaderPanel = clientHeaderPanel;
        this.textAreaPanel = textAreaPanel;
        this.client.setConnectExceptionHandler((e) -> {
            textAreaPanel.appendText(e.getMessage());
            clientHeaderPanel.setButtonText("connect");
            state = 0;
        });
        this.client.setConnectSuccessHandler(() -> {
            clientHeaderPanel.setButtonText("disconnect");
            state = 2;
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (state == 0 && client.canRunning()) {
            String ip = clientHeaderPanel.getIpTextFieldText();
            int port = Integer.parseInt(clientHeaderPanel.getPortTextFieldText().trim());
            client.connect(ip, port);
            clientHeaderPanel.setButtonText("connecting");
            state = 1;
        } else if (state == 2){
            client.disconnect();
            clientHeaderPanel.setButtonText("connect");
            state = 0;
        }
    }
}
