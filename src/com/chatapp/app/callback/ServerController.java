package com.chatapp.app.callback;

import com.chatapp.app.connect.Server;
import com.chatapp.app.gui.SwingServerHeaderPanel;
import com.chatapp.app.gui.SwingTextAreaPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerController implements ActionListener {
    private Server server;
    private SwingServerHeaderPanel serverHeaderPanel;
    private SwingTextAreaPanel textAreaPanel;

    public ServerController(Server server, SwingServerHeaderPanel serverHeaderPanel, SwingTextAreaPanel textAreaPanel) {
        this.server = server;
        this.serverHeaderPanel = serverHeaderPanel;
        this.textAreaPanel = textAreaPanel;
        server.setBindExceptionHandler((e) -> {
            textAreaPanel.appendText(e.getMessage());
            serverHeaderPanel.setTextFieldText("");
            serverHeaderPanel.setButtonText("bind");
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int port;
        try {
            port = Integer.parseInt(serverHeaderPanel.getTextFieldText().trim());
        } catch (NumberFormatException ex) {
            textAreaPanel.appendText(ex.getMessage());
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
