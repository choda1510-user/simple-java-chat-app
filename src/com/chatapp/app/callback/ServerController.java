package com.chatapp.app.callback;

import com.chatapp.app.connect.Server;
import com.chatapp.app.gui.SwingServerHeaderPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerController implements ActionListener {
    private Server server;
    private SwingServerHeaderPanel serverHeaderPanel;

    public ServerController(Server server, SwingServerHeaderPanel serverHeaderPanel) {
        this.server = server;
        this.serverHeaderPanel = serverHeaderPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int port;
        try {
            port = Integer.parseInt(serverHeaderPanel.getTextFieldText().trim());
        } catch (NumberFormatException ex) {
            return;
        }
        if (server.canRunning()) {
            server.bind(port);
            serverHeaderPanel.setButtonText("shutdown");
        } else {
            server.shutdown();
            serverHeaderPanel.setButtonText("bind");
        }
    }
}
