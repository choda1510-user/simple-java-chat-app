package com.chatapp.app.callback;

import com.chatapp.app.connect.ReceivedListener;
import com.chatapp.app.connect.Sendable;
import com.chatapp.app.gui.SwingTextAreaPanel;

import javax.swing.*;

public class ReceivedServerController implements ReceivedListener {
    private final SwingTextAreaPanel textAreaPanel;
    private final Sendable sender;

    public ReceivedServerController(SwingTextAreaPanel textAreaPanel, Sendable sender) {
        this.textAreaPanel = textAreaPanel;
        this.sender = sender;
    }

    @Override
    public void received(String message) {
        sender.send(message);
        SwingUtilities.invokeLater(() -> {
            textAreaPanel.appendText(message);
        });
    }
}
