package com.chatapp.app.callback;

import com.chatapp.app.connect.Client;
import com.chatapp.app.gui.SwingClientHeaderPanel;
import com.chatapp.app.gui.SwingTextAreaPanel;

import javax.swing.*;
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
            textAreaPanel.setText("");
            clientHeaderPanel.setButtonText("disconnect");
            state = 2;
        });
        this.client.setCloseListener((sender) -> {
            SwingUtilities.invokeLater(() -> {
                textAreaPanel.appendText("disconnected from the server");
                clientHeaderPanel.setButtonText("connect");
            });
            state = 0;
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (state == 0 && client.canRunning()) {
            String ip = clientHeaderPanel.getIpTextFieldText();
            int port;
            try {
                port = Integer.parseInt(clientHeaderPanel.getPortTextFieldText().trim());
            } catch (NumberFormatException ex) {
                textAreaPanel.appendText(ex.getMessage());
                return;
            }
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
