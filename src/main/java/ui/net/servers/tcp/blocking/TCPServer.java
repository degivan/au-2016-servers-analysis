package ui.net.servers.tcp.blocking;

import ui.net.protobuf.Protocol.BenchmarkPacket;
import ui.net.servers.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Degtjarenko Ivan on 23.05.2016.
 */
public abstract class TCPServer extends Server {
    protected ServerSocket serverSocket;

    @Override
    public void stop() throws IOException {
        serverSocket.close();
    }
    protected void handleRequest(Socket socket) {
        try(DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {
            while(!socket.isClosed()) {
                BenchmarkPacket clientPacket = BenchmarkPacket.parseFrom(dis);
                BenchmarkPacket result = sortAndWrap(clientPacket);
                sendPacket(dos, result);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void sendPacket(DataOutputStream dos, BenchmarkPacket packet) throws IOException {
        dos.writeInt(packet.getSerializedSize());
        dos.write(packet.toByteArray());
        dos.flush();
    }

}
