package com.chatapp.app.gui;

import javax.swing.*;
import java.awt.*;

public class GuiFactory {
    private GuiFactory factory = this;
    public GuiFactory() {

    }
    public GuiFactory(GuiFactory proxy) {
        this.factory = proxy;
    }
    public JFrame getRootFrame() {
        JFrame jFrame = new JFrame();
        jFrame.setTitle("Chat App");
        jFrame.setSize(800, 600);
        jFrame.setLocation(100, 100);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        return jFrame;
    }
    public JPanel getRootPanel() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(1, 2));
        JPanel left, right;
        left = factory.getServerChatPanel();
        right = factory.getClientChatPanel();
        jPanel.add(left);
        jPanel.add(right);
        return jPanel;
    }
    public JPanel getServerChatPanel() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.add(factory.getServerHeaderPanel(), BorderLayout.NORTH);
        jPanel.add(factory.getServerChatTextArea(), BorderLayout.CENTER);
        jPanel.add(factory.getServerSubmitPanel(), BorderLayout.SOUTH);
        return jPanel;
    }
    public JTextArea getServerChatTextArea() {
        return new JTextArea();
    }
    public JPanel getServerHeaderPanel() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.add(new JLabel("server"), BorderLayout.NORTH);
        jPanel.add(factory.getServerPortPanel(), BorderLayout.CENTER);
        return jPanel;
    }
    public JPanel getServerPortPanel() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        jPanel.add(new JLabel("server port: "));
        jPanel.add(factory.getServerPortTextField());
        jPanel.add(factory.getServerPortButton());
        return jPanel;
    }
    public JTextField getServerPortTextField() {
        return new JTextField(5);
    }
    public JButton getServerPortButton() {
        return new JButton("bind");
    }
    public JPanel getServerSubmitPanel() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.add(factory.getServerSubmitTextField(), BorderLayout.CENTER);
        jPanel.add(factory.getServerSubmitButton(), BorderLayout.SOUTH);
        return jPanel;
    }
    public JTextField getServerSubmitTextField() {
        return new JTextField();
    }
    public JButton getServerSubmitButton() {
        return new JButton("submit");
    }
    public JPanel getClientSubmitPanel() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.add(factory.getClientSubmitTextField(), BorderLayout.CENTER);
        jPanel.add(factory.getClientSubmitButton(), BorderLayout.SOUTH);
        return jPanel;
    }
    public JTextField getClientSubmitTextField() {
        return new JTextField();
    }
    public JButton getClientSubmitButton() {
        return new JButton("submit");
    }
    public JPanel getClientChatPanel() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.add(factory.getConnectPanel(), BorderLayout.NORTH);
        jPanel.add(factory.getClientChatTextArea(), BorderLayout.CENTER);
        jPanel.add(factory.getClientSubmitPanel(), BorderLayout.SOUTH);
        return jPanel;
    }
    public JTextArea getClientChatTextArea() {
        return new JTextArea();
    }
    public JPanel getConnectPanel() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        jPanel.add(new JLabel("server ip and port: "));
        jPanel.add(factory.getConnectIPTextField());
        jPanel.add(factory.getConnectPortTextField());
        jPanel.add(factory.getConnectButton());
        return jPanel;
    }
    public JTextField getConnectIPTextField() {
        return new JTextField(10);
    }
    public JTextField getConnectPortTextField() {
        return new JTextField(5);
    }
    public JButton getConnectButton() {
        return new JButton("connect");
    }
}
