package com.chatapp.app.gui;

import javax.swing.*;
import java.awt.*;

import static com.chatapp.app.util.NullChecker.nullCheck;

public class SwingFrame {
    private final JFrame frame;
    private final SwingServerPanel serverPanel;
    private final SwingClientPanel clientPanel;
    private SwingFrame(JFrame frame, SwingServerPanel serverPanel, SwingClientPanel clientPanel) {
        this.frame = frame;
        this.serverPanel = serverPanel;
        this.clientPanel = clientPanel;
    }
    public static class Builder {
        private final JFrame frame;
        private SwingServerPanel serverPanel;
        private SwingClientPanel clientPanel;

        private Builder() {
            frame = new JFrame();
        }
        public Builder title(String title) {
            frame.setTitle(title);
            return this;
        }
        public Builder size(int width, int height) {
            frame.setSize(width, height);
            return this;
        }
        public Builder defaultCloseOperation(int op) {
            frame.setDefaultCloseOperation(op);
            return this;
        }
        public Builder serverPanel(SwingServerPanel serverPanel) {
            if (serverPanel == null) {
                throw new NullPointerException();
            }
            this.serverPanel = serverPanel;
            return this;
        }
        public Builder clientPanel(SwingClientPanel clientPanel) {
            if (clientPanel == null) {
                throw new NullPointerException();
            }
            this.clientPanel = clientPanel;
            return this;
        }
        public SwingFrame build() {
            nullCheck(frame, clientPanel, serverPanel);
            frame.setLayout(new GridLayout(1, 2));
            JPanel contentPane = new JPanel();
            frame.setContentPane(contentPane);
            contentPane.add(serverPanel.getPanel());
            contentPane.add(clientPanel.getPanel());
            return new SwingFrame(frame, serverPanel, clientPanel);
        }
    }
    public static Builder builder() {
        return new Builder();
    }
    public JFrame getFrame() {
        return frame;
    }
    public SwingServerPanel getServerPanel() {
        return serverPanel;
    }
    public SwingClientPanel getClientPanel() {
        return clientPanel;
    }
    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
    public static void main(String[] args) {
        SwingFrame frame = SwingFrame.builder()
                .title("test")
                .size(800, 600)
                .defaultCloseOperation(JFrame.DISPOSE_ON_CLOSE)
                .clientPanel(SwingClientPanel.builder()
                        .headerPanel(SwingClientHeaderPanel.builder()
                                .ipLabelText("ip")
                                .ipTextFieldColumns(12)
                                .portLabelText("port")
                                .portTextFieldColumns(6)
                                .buttonText("connect")
                                .build())
                        .textAreaPanel(SwingTextAreaPanel.builder()
                                .enabled(false)
                                .size(6, 30)
                                .build())
                        .submitPanel(SwingSubmitPanel.builder()
                                .textFieldColumns(10)
                                .buttonText("send")
                                .build())
                        .build())
                .serverPanel(SwingServerPanel.builder()
                        .headerPanel(SwingServerHeaderPanel.builder()
                                .labelText("server port: ")
                                .buttonText("bind")
                                .textFieldColumns(10)
                                .addButtonActionListener((e) -> {
                                    System.out.println("clicked");
                                })
                                .build())
                        .textAreaPanel(SwingTextAreaPanel.builder()
                                .size(6, 30)
                                .build())
                        .submitPanel(SwingSubmitPanel.builder()
                                .buttonText("send")
                                .textFieldColumns(10)
                                .build())
                        .build())
                .build();
        frame.getFrame().setVisible(true);
    }
}
