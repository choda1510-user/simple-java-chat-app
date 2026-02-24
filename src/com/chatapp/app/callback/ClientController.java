package com.chatapp.app.callback;

import com.chatapp.app.connect.Client;
import com.chatapp.app.gui.SwingClientHeaderPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientController implements ActionListener {
    private final Client client;
    private final SwingClientHeaderPanel clientHeaderPanel;

    public ClientController(Client client, SwingClientHeaderPanel clientHeaderPanel) {
        this.client = client;
        this.clientHeaderPanel = clientHeaderPanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (client.canRunning()) {
            String ip = clientHeaderPanel.getIpTextFieldText();
            int port = Integer.parseInt(clientHeaderPanel.getPortTextFieldText().trim());
            client.connect(ip, port);
            clientHeaderPanel.setButtonText("disconnect");
        } else {
            client.disconnect();
            clientHeaderPanel.setButtonText("connect");
        }
    }
}
