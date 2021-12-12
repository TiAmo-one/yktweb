package com.team.yplus.common.socket.client;

import java.io.IOException;

public interface Client {
    void connect() throws IOException;
    void sendOnce(String payload) throws IOException;
    String receiveOnce() throws IOException;
    void close() throws IOException;
}
