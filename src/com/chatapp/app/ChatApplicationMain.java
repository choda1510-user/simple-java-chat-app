package com.chatapp.app;

import javax.swing.*;

public class ChatApplicationMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ChatApplication(args);
        });
    }
}
