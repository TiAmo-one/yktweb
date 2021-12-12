package com.team.yplus.common.socket.service;

import com.github.rholder.retry.*;
import com.google.gson.Gson;
import com.team.yplus.common.socket.annotation.RequestType;
import com.team.yplus.common.socket.client.TcpClient;
import org.apache.commons.logging.Log;

import java.net.InetSocketAddress;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("UnstableApiUsage")
public class TcpRequester<T> extends TcpClient implements Requester<T> {

    public TcpRequester(InetSocketAddress serverSocketAddress) {
        super(serverSocketAddress);
    }

    public TcpRequester(String serverHostName, int serverPort) {
        super(serverHostName, serverPort);
    }

    @Override
    public T request(Class<T> clazz) {
        String response = null;
        T dto = null;

        Callable<String> doRequest = () -> {
            connect();
            sendOnce(clazz.getAnnotation(RequestType.class).value());
            return receiveOnce();
        };

        Retryer<String> retryer = RetryerBuilder.<String>newBuilder()
                .retryIfResult(Objects::isNull)
                .retryIfException()
                .retryIfRuntimeException()
                .withWaitStrategy(WaitStrategies.fixedWait(10, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .withRetryListener(new TCPRequesterRetryListener(logger))
                .build();

        try {
            response = retryer.call(doRequest);
        } catch (ExecutionException | RetryException e) {
            logger.error("Can not do request via socket.");
        }

        if (response != null) {
            Gson gson = new Gson();
            dto = gson.fromJson(response, clazz);
        }

        return dto;
    }
}

@SuppressWarnings("UnstableApiUsage")
class TCPRequesterRetryListener implements RetryListener {
    Log logger;
    public TCPRequesterRetryListener(Log logger) {
        this.logger = logger;
    }

    @Override
    public <V> void onRetry(Attempt<V> attempt) {
        String log = "[Retry " + attempt.getAttemptNumber() + " times] ";
        if (attempt.hasException()) {
            log += "Due to Exception, request failed. we will retry after 10s.";
        } else if (!attempt.hasResult()) {
            log += "Due to Result is null, request failed. we will retry after 10s.";
        } else {
            if (attempt.getAttemptNumber() == 1) return;
            log += "Successfully do request via socket.";
        }
        logger.info(log);
    }
}