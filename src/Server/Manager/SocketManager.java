package Manager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketManager implements Closeable {
    private  Socket socket;
    private final BufferedReader reader;
    private final BufferedWriter writer;
    public SocketManager(String IpAddress,int port)
    {
        socket=initializeSocket(IpAddress,port);
        writer = createBufferedWriter();
        reader=createBufferedReader();
    }

    public SocketManager(ServerSocket serverSocket)
    {
        initializeSocket(serverSocket);
        writer = createBufferedWriter();
        reader=createBufferedReader();
    }

    private BufferedWriter createBufferedWriter()
    {
        try {
            return new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private BufferedReader createBufferedReader()
    {
        try {
            return new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Socket initializeSocket(String Ip,int port)
    {
        try {
            return new Socket(Ip,port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void initializeSocket(ServerSocket ss)
    {
        try {
            socket=ss.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readLine()  {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void writeLine(String line)  {
        try {
            writer.write(line);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        writer.close();
        reader.close();
        socket.close();
    }
}
