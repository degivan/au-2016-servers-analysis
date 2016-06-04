package ui.net.servers;

import ui.net.servers.tcp.blocking.TCPCachedPool;
import ui.net.servers.tcp.blocking.TCPNewProcess;
import ui.net.servers.tcp.blocking.TCPNewThread;
import ui.net.servers.tcp.blocking.TCPSingleThread;
import ui.net.servers.tcp.nonblocking.TCPNonBlockingFixedPool;
import ui.net.servers.tcp.nonblocking.TCPNonBlockingNewThread;
import ui.net.servers.tcp.nonblocking.TCPNonBlockingSingleThread;
import ui.net.servers.udp.UDPFixedPool;
import ui.net.servers.udp.UDPNewThread;

/**
 * Created by Degtjarenko Ivan on 04.06.2016.
 */
public class ServerFactory {
    private static final String SERVER_TYPE_ERROR = "There is no servers of this type.";
    private ServerFactory() {}

    public static Server createServer(ServerType serverType) {
        Server server;
        switch (serverType) {
            case TCP_CACHED_POOL:
                server = new TCPCachedPool();
                break;
            case TCP_NEW_PROCESS:
                server = new TCPNewProcess();
                break;
            case TCP_NEW_THREAD:
                server = new TCPNewThread();
                break;
            case TCP_SINGLE_THREAD:
                server = new TCPSingleThread();
                break;
            case TCP_NON_BLOCKING_FIXED_POOL:
                server = new TCPNonBlockingFixedPool();
                break;
            case TCP_NON_BLOCKING_NEW_THREAD:
                server = new TCPNonBlockingNewThread();
                break;
            case TCP_NON_BLOCKING_SINGLE_THREAD:
                server = new TCPNonBlockingSingleThread();
                break;
            case UDP_FIXED_POOL:
                server = new UDPFixedPool();
                break;
            case UDP_NEW_THREAD:
                server = new UDPNewThread();
                break;
            default:
                throw new IllegalArgumentException(SERVER_TYPE_ERROR);
        }
        return server;
    }
}
