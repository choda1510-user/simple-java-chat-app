package com.chatapp.app.config;

import com.chatapp.app.callback.*;
import com.chatapp.app.connect.ChatClient;
import com.chatapp.app.connect.ChatServer;
import com.chatapp.app.connect.Client;
import com.chatapp.app.connect.Server;
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
        ReceivedServerController receivedServerController = new ReceivedServerController(frame.getServerPanel().getTextAreaPanel(), null);
        Server server = new ChatServer(receivedServerController);
        receivedServerController.setSender(server);
        ServerController serverController = new ServerController(server, frame.getServerPanel().getHeaderPanel());
        frame.getServerPanel().getHeaderPanel().addButtonActionListener(serverController);
        ServerSendController serverClientSendController = new ServerSendController(server, server, frame.getServerPanel().getTextAreaPanel(), frame.getServerPanel().getSubmitPanel());
        frame.getServerPanel().getSubmitPanel().addButtonActionListener(serverClientSendController);

        ReceivedClientController receivedClientController = new ReceivedClientController(frame.getClientPanel().getTextAreaPanel());
        Client client = new ChatClient(receivedClientController);
        ClientController clientController = new ClientController(client, frame.getClientPanel().getHeaderPanel());
        frame.getClientPanel().getHeaderPanel().addButtonActionListener(clientController);
        ClientSendController clientSendController = new ClientSendController(client, client, frame.getClientPanel().getSubmitPanel());
        frame.getClientPanel().getSubmitPanel().addButtonActionListener(clientSendController);
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
