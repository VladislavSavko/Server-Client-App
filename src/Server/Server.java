package Server;

import Manager.SocketManager;

import java.io.*;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8000)) {
            System.out.println("Server is up!");
            while (true) {
                SocketManager phone = new SocketManager(server);
                new Thread(() -> {
                    System.out.println("Client connected");
                    String request = phone.readLine();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Request: " + request);
                    String response = Integer.toString((int) (Math.random() * 30 - 10));
                    System.out.println("Response: " + response);
                    phone.writeLine(response);
                }).start();
            }
        } catch (IOException | NullPointerException ex) {
            ex.printStackTrace();
        }
    }
}
