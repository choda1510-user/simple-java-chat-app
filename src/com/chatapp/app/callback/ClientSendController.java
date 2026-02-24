package com.chatapp.app.callback;

import com.chatapp.app.connect.ChatServerEventQueue;
import com.chatapp.app.connect.Sendable;
import com.chatapp.app.connect.StatusCheckable;
import com.chatapp.app.gui.SwingSubmitPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientSendController implements ActionListener {
    private final StatusCheckable status;
    private final Sendable sender;
    private final SwingSubmitPanel submitPanel;

    public ClientSendController(StatusCheckable status, Sendable sender, SwingSubmitPanel submitPanel) {
        this.status = status;
        this.sender = sender;
        this.submitPanel = submitPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!status.canRunning()) {
            String message = submitPanel.getTextFieldText();
            submitPanel.setTextFieldText("");
            ChatServerEventQueue.invoke(() -> {
                sender.send(message);
            });
        }
    }
}
