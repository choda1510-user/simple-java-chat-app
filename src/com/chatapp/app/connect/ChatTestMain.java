package com.chatapp.app.connect;

import java.util.Scanner;

public class ChatTestMain {
    public static void clientLoop(Scanner scanner) {
        String input;
        ChatClient client = new ChatClient((msg) -> {
            System.out.println("chat: " + msg);
        });
        while (true) {
            if (client.isReady()) {
                String ip, port;
                System.out.print("server ip: ");
                ip = scanner.nextLine();
                if (!ip.isBlank()) {
                    if (ip.contentEquals(":exit")) {
                        if (!client.isReady()) {
                            client.disconnect();
                        }
                        break;
                    }
                    System.out.print("server port: ");
                    port = scanner.nextLine();
                    if (port.contentEquals(":exit")) {
                        if (!client.isReady()) {
                            client.disconnect();
                        }
                        break;
                    }
                    client.connect(ip, Integer.parseInt(port));
                }
            } else {
                System.out.print("> ");
                input = scanner.nextLine();
                if (!input.isBlank()) {
                    if (input.contentEquals(":exit")) {
                        if (!client.isReady()) {
                            client.disconnect();
                        }
                        break;
                    }
                    client.send(input);
                }
            }

        }
    }
    public static void serverLoop(Scanner scanner) {
        String input;
        ChatServer chatServer = new ChatServer((msg) -> {
            System.out.println("chat: " + msg);
        });
        chatServer.setReceivedListener((msg) -> {
            chatServer.send(msg);
            System.out.println("chat: " + msg);
        });
        while (true) {
            if (chatServer.isReady()) {
                System.out.print("port: ");
                input = scanner.nextLine();
                if (!input.isBlank()) {
                    if (input.contentEquals(":exit")) {
                        if (!chatServer.isReady()) {
                            chatServer.shutdown();
                        }
                        break;
                    }
                    chatServer.bind(Integer.parseInt(input));
                }
            } else {
                System.out.print("> ");
                input = scanner.nextLine();
                if (!input.isBlank()) {
                    if (input.contentEquals(":exit")) {
                        if (!chatServer.isReady()) {
                            chatServer.shutdown();
                        }
                        break;
                    }
                    chatServer.send(input);
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("[1]client, [2]server, [0]exit: ");
        switch (Integer.parseInt(scanner.nextLine())) {
            case 0:
                break;
            case 1:
                clientLoop(scanner);
                break;
            case 2:
                serverLoop(scanner);
                break;
        }
        try {
            Thread.sleep(1L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        scanner.close();
    }
}
