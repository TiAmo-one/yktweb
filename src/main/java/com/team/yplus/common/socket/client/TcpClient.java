package com.team.yplus.common.socket.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TcpClient implements Closeable, Client {
    protected final Log logger = LogFactory.getLog(getClass());
    private final InetSocketAddress serverSocketAddress;
    private Socket socketConn;
    private InputStream inputStream;
    private OutputStream outputStream;

    public TcpClient(InetSocketAddress serverSocketAddress) {
        this.serverSocketAddress = serverSocketAddress;
    }

    public TcpClient(String serverHostName, int serverPort) {
        this(new InetSocketAddress(serverHostName, serverPort));
    }

    public void connect() throws IOException {
        // create socket connection.
        try {
            this.socketConn = new Socket(
                    serverSocketAddress.getAddress(),
                    serverSocketAddress.getPort()
            );
            logger.info("Successfully connected to TCP Server " + socketConn.getRemoteSocketAddress() + ".");
        } catch (IOException e) {
            logger.error("An IOException occurred while creating socket connection.", e);
            throw e;
        }

        // get Writer and Reader
        try {
            this.inputStream = socketConn.getInputStream();
            this.outputStream = socketConn.getOutputStream();
            logger.info("Successfully acquired I/O Stream.");
        } catch (IOException e) {
            logger.error("An IOException occurred while fetching I/O Stream.", e);
            throw e;
        }
    }

    public void sendOnce(String payload) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        try {
            writer.write(payload);
            writer.flush();
            socketConn.shutdownOutput();
            logger.info("Successfully sent payload \"" + payload + "\".");
        } catch (IOException e) {
            logger.error("An IOException occurred while sending payload \"" + payload + "\"}.", e);
            throw e;
        }
    }

    public String receiveOnce() throws IOException {
        String payload;
        try {
            payload = TcpClient.readInputStream(inputStream);
            socketConn.shutdownInput();
            logger.info("Successfully received payload \"" + payload + "\".");
        } catch (IOException e) {
            logger.error("An IOException occurred while receiving message.", e);
            throw e;
        }
        return payload;
    }

    @Override
    public void close() throws IOException {
        if (inputStream != null) inputStream.close();
        if (outputStream != null) outputStream.close();
        if (socketConn != null && !socketConn.isClosed()) {
            socketConn.close();
        }
        logger.info("Successfully closed TCP Client.");
    }

    private static String readInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] inputBuffer = new byte[1024];
        for (; ; ) {
            int len = is.read(inputBuffer);
            if (len == -1) break;
            byteArrayOutputStream.write(inputBuffer, 0, len);
        }

        String inputString = byteArrayOutputStream.toString();
        byteArrayOutputStream.close();

        return inputString;
    }
}