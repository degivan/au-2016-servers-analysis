package ui.net.servers.tcp.blocking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Degtjarenko Ivan on 04.06.2016.
 */
public class TCPCachedPool extends TCPServer {
    private ExecutorService threadPool;

    @Override
    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        threadPool = Executors.newCachedThreadPool();

        while (!serverSocket.isClosed()) {
            try (Socket client = serverSocket.accept()) {
                Runnable task = () -> {
                    while (!client.isClosed()) {
                        handleRequest(client);
                    }
                };
                threadPool.execute(task);
            }
        }
    }

    @Override
    public void stop() throws IOException {
        super.stop();
        threadPool.shutdown();
    }
}
