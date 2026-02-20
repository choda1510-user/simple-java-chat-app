package com.chatapp.app.callback;

import com.chatapp.app.connect.ReceivedListener;
import com.chatapp.app.gui.SwingTextAreaPanel;

import javax.swing.*;

public class ReceivedClientController implements ReceivedListener {
    private final SwingTextAreaPanel textAreaPanel;

    public ReceivedClientController(SwingTextAreaPanel textAreaPanel) {
        this.textAreaPanel = textAreaPanel;
    }

    @Override
    public void received(String message) {
        SwingUtilities.invokeLater(() -> {
            textAreaPanel.appendText(message);
        });
    }
}
