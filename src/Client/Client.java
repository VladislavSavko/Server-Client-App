package Client;

import Manager.SocketManager;

import java.io.IOException;

public class Client {
    public static void main(String[] args) {
        try(SocketManager phone=new SocketManager("127.0.0.1",8000)) {
            System.out.println("Connected to server!");
            String request="Hrodna";
            phone.writeLine(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
