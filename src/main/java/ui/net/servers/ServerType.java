package ui.net.servers;

/**
 * Created by Degtjarenko Ivan on 04.06.2016.
 */
public enum ServerType {
    TCP_CACHED_POOL(0),
    TCP_NEW_PROCESS(1),
    TCP_NEW_THREAD(2),
    TCP_SINGLE_THREAD(3),
    TCP_NON_BLOCKING_FIXED_POOL(4),
    TCP_NON_BLOCKING_NEW_THREAD(5),
    TCP_NON_BLOCKING_SINGLE_THREAD(6),
    UDP_FIXED_POOL(7),
    UDP_NEW_THREAD(8);

    private final int id;

    ServerType(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static ServerType get(final int id) {
        return values()[id];
    }
}
