package com.chatapp.app.gui;

import javax.swing.*;

public class GuiSingletonFactory extends GuiFactory {
    private GuiFactory factory;
    private JFrame rootFrame;
    private JPanel rootPanel,
            serverChatPanel,
            serverHeaderPanel,
            serverPortPanel,
            serverSubmitPanel,
            clientSubmitPanel,
            clientChatPanel,
            connectPanel;
    private JTextArea serverChatTextArea, clientChatTextArea;
    private JTextField serverPortTextField,
            serverSubmitTextField,
            clientSubmitTextField,
            connectIPTextField,
            connectPortTextField;
    private JButton serverPortButton,
            serverSubmitButton,
            clientSubmitButton,
            connectButton;
    public void setFactory(GuiFactory factory) {
        this.factory = factory;
    }
    @Override
    public JFrame getRootFrame() {
        if (rootFrame == null) {
            rootFrame = factory.getRootFrame();
        }
        return rootFrame;
    }

    @Override
    public JPanel getRootPanel() {
        if (rootPanel == null) {
            rootPanel = factory.getRootPanel();
        }
        return rootPanel;
    }

    @Override
    public JPanel getServerChatPanel() {
        if (serverChatPanel == null) {
            serverChatPanel = factory.getServerChatPanel();
        }
        return serverChatPanel;
    }

    @Override
    public JTextArea getServerChatTextArea() {
        if (serverChatTextArea == null) {
            serverChatTextArea = factory.getServerChatTextArea();
        }
        return serverChatTextArea;
    }

    @Override
    public JPanel getServerHeaderPanel() {
        if (serverHeaderPanel == null) {
            serverHeaderPanel = factory.getServerHeaderPanel();
        }
        return serverHeaderPanel;
    }

    @Override
    public JPanel getServerPortPanel() {
        if (serverPortPanel == null) {
            serverPortPanel = factory.getServerPortPanel();
        }
        return serverPortPanel;
    }

    @Override
    public JTextField getServerPortTextField() {
        if (serverPortTextField == null) {
            serverPortTextField = factory.getServerPortTextField();
        }
        return serverPortTextField;
    }

    @Override
    public JButton getServerPortButton() {
        if (serverPortButton == null) {
            serverPortButton = factory.getServerPortButton();
        }
        return serverPortButton;
    }

    @Override
    public JPanel getServerSubmitPanel() {
        if (serverSubmitPanel == null) {
            serverSubmitPanel = factory.getServerSubmitPanel();
        }
        return serverSubmitPanel;
    }

    @Override
    public JTextField getServerSubmitTextField() {
        if (serverSubmitTextField == null) {
            serverSubmitTextField = factory.getServerSubmitTextField();
        }
        return serverSubmitTextField;
    }

    @Override
    public JButton getServerSubmitButton() {
        if (serverSubmitButton == null) {
            serverSubmitButton = factory.getServerSubmitButton();
        }
        return serverSubmitButton;
    }

    @Override
    public JPanel getClientSubmitPanel() {
        if (clientSubmitPanel == null) {
            clientSubmitPanel = factory.getClientSubmitPanel();
        }
        return clientSubmitPanel;
    }

    @Override
    public JTextField getClientSubmitTextField() {
        if (clientSubmitTextField == null) {
            clientSubmitTextField = factory.getClientSubmitTextField();
        }
        return clientSubmitTextField;
    }

    @Override
    public JButton getClientSubmitButton() {
        if (clientSubmitButton == null) {
            clientSubmitButton = factory.getClientSubmitButton();
        }
        return clientSubmitButton;
    }

    @Override
    public JPanel getClientChatPanel() {
        if (clientChatPanel == null) {
            clientChatPanel = factory.getClientChatPanel();
        }
        return clientChatPanel;
    }

    @Override
    public JTextArea getClientChatTextArea() {
        if (clientChatTextArea == null) {
            clientChatTextArea = factory.getClientChatTextArea();
        }
        return clientChatTextArea;
    }

    @Override
    public JPanel getConnectPanel() {
        if (connectPanel == null) {
            connectPanel = factory.getConnectPanel();
        }
        return connectPanel;
    }

    @Override
    public JTextField getConnectIPTextField() {
        if (connectIPTextField == null) {
            connectIPTextField = factory.getConnectIPTextField();
        }
        return connectIPTextField;
    }

    @Override
    public JTextField getConnectPortTextField() {
        if (connectPortTextField == null) {
            connectPortTextField = factory.getConnectPortTextField();
        }
        return connectPortTextField;
    }

    @Override
    public JButton getConnectButton() {
        if (connectButton == null) {
            connectButton = factory.getConnectButton();
        }
        return connectButton;
    }
}
