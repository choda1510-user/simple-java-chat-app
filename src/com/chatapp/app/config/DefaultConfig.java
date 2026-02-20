package com.chatapp.app.config;

import com.chatapp.app.gui.GuiFactory;
import com.chatapp.app.gui.GuiSingletonFactory;

import javax.swing.*;

public class DefaultConfig implements GuiConfig {
    private GuiFactory factory;
    public DefaultConfig() {
        init();
        factory.getServerPortButton().addActionListener((e) -> {
            System.out.println(factory.getServerPortTextField().getText());
        });
        factory.getServerSubmitButton().addActionListener((e) -> {
            String message = "server: " + factory.getServerSubmitTextField().getText() + "\n";
            factory.getServerSubmitTextField().setText("");
            factory.getServerChatTextArea().append(message);
        });
        factory.getClientSubmitButton().addActionListener((e) -> {
            String message = factory.getClientSubmitTextField().getText() + "\n";
            factory.getClientSubmitTextField().setText("");
            factory.getClientChatTextArea().append(message);
        });
    }
    private void init() {
        GuiSingletonFactory proxyFactory = new GuiSingletonFactory();
        GuiFactory factory = new GuiFactory(proxyFactory);
        proxyFactory.setFactory(factory);
        proxyFactory.getRootFrame().setContentPane(proxyFactory.getRootPanel());
        this.factory = proxyFactory;
    }
    @Override
    public void show() {
        factory.getRootFrame().setVisible(true);
    }
}
