package ui.net.servers;

import ui.net.protobuf.Protocol.BenchmarkPacket;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Degtjarenko Ivan on 04.06.2016.
 */
public abstract class Server {
    public abstract void start(final int port) throws IOException;

    public abstract void stop() throws IOException;

    protected BenchmarkPacket sortAndWrap(BenchmarkPacket packet) {
        BenchmarkPacket.Builder builder = BenchmarkPacket.newBuilder();
        int count = packet.getCount();
        List<Integer> list = packet.getArrayList();

        Collections.sort(list);

        builder.setCount(count);
        builder.addAllArray(list);

        return builder.build();
    }
}
