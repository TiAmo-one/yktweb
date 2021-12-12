package com.team.yplus.common.socket.service;

public interface Requester<T> {
    T request(Class<T> clazz);
}
