package ui.net.servers.tcp.blocking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Degtjarenko Ivan on 04.06.2016.
 */
public class TCPNewThread extends TCPServer {
    @Override
    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);

        while (!serverSocket.isClosed()) {
            try (Socket client = serverSocket.accept()) {
                Runnable task = () -> {
                    while (!client.isClosed()) {
                        handleRequest(client);
                    }
                };
                new Thread(task).start();
            }
        }
    }
}
