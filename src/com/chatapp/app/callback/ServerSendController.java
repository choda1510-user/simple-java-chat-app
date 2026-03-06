package com.chatapp.app.callback;

import com.chatapp.app.connect.ChatServerEventQueue;
import com.chatapp.app.connect.Sendable;
import com.chatapp.app.connect.StatusCheckable;
import com.chatapp.app.gui.SwingSubmitPanel;
import com.chatapp.app.gui.SwingTextAreaPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerSendController implements ActionListener {
    private final StatusCheckable status;
    private final Sendable sender;
    private final SwingTextAreaPanel textAreaPanel;
    private final SwingSubmitPanel submitPanel;

    public ServerSendController(StatusCheckable status, Sendable sender, SwingTextAreaPanel textAreaPanel, SwingSubmitPanel submitPanel) {
        this.status = status;
        this.sender = sender;
        this.textAreaPanel = textAreaPanel;
        this.submitPanel = submitPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!status.canRunning()) {
            String message = "[server]: " + submitPanel.getTextFieldText();
            submitPanel.setTextFieldText("");
            textAreaPanel.appendText(message);
            ChatServerEventQueue.invoke(() -> {
                sender.send(message);
            });
        }
    }
}
