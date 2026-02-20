package com.chatapp.app.config;

import com.chatapp.app.gui.*;

import javax.swing.*;

public class SwingConfig implements GuiConfig {
    private SwingFrame frame;
    public SwingConfig() {
        frame = frame(
                serverPanel(
                        serverHeaderPanel(),
                        textAreaPanel(),
                        submitPanel())
                , clientPanel(
                        clientHeaderPanel(),
                        textAreaPanel(),
                        submitPanel()
                )
        );
    }
    public SwingFrame frame(SwingServerPanel serverPanel, SwingClientPanel clientPanel) {
        return SwingFrame.builder()
                .size(800, 600)
                .title("chat app")
                .defaultCloseOperation(JFrame.DISPOSE_ON_CLOSE)
                .serverPanel(serverPanel)
                .clientPanel(clientPanel)
                .build();
    }
    public SwingSubmitPanel submitPanel() {
        return SwingSubmitPanel.builder()
                .textFieldColumns(20)
                .buttonText("send")
                .build();
    }
    public SwingTextAreaPanel textAreaPanel() {
        return SwingTextAreaPanel.builder()
                .enabled(false)
                .size(8, 30)
                .build();
    }
    public SwingServerHeaderPanel serverHeaderPanel() {
        return SwingServerHeaderPanel.builder()
                .labelText("port")
                .textFieldText("9190")
                .textFieldColumns(6)
                .buttonText("bind")
                .build();
    }
    public SwingClientHeaderPanel clientHeaderPanel() {
        return SwingClientHeaderPanel.builder()
                .ipLabelText("ip")
                .portLabelText("port")
                .ipTextFieldColumns(12)
                .portTextFieldColumns(6)
                .buttonText("connect")
                .build();
    }
    public SwingServerPanel serverPanel(
            SwingServerHeaderPanel headerPanel,
            SwingTextAreaPanel textAreaPanel,
            SwingSubmitPanel submitPanel
    ) {
        return SwingServerPanel.builder()
                .headerPanel(headerPanel)
                .textAreaPanel(textAreaPanel)
                .submitPanel(submitPanel)
                .build();
    }
    public SwingClientPanel clientPanel(
            SwingClientHeaderPanel headerPanel,
            SwingTextAreaPanel textAreaPanel,
            SwingSubmitPanel submitPanel
    ) {
        return SwingClientPanel.builder()
                .headerPanel(headerPanel)
                .textAreaPanel(textAreaPanel)
                .submitPanel(submitPanel)
                .build();
    }

    @Override
    public void show() {
        frame.setVisible(true);
    }
}
