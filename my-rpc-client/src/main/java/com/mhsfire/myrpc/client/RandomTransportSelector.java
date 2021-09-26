package com.mhsfire.myrpc.client;

import com.mhsfire.myrpc.common.ReflectionUtils;
import com.mhsfire.myrpc.protocol.Peer;
import com.mhsfire.myrpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 随机网络模块选择器
 *
 * @PACKAGE_NAME: com.mhsfire.myrpc.client
 * @NAME: RandomTransportSelector
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/9/22
 * @PROJECT_NAME: my-rpc
 */
@Slf4j
public class RandomTransportSelector implements TransportSelector {

    /**
     * 已经连接好的client
     */
    private List<TransportClient> clients;

    public RandomTransportSelector() {
        // 为防止多线程时同时使用相同client，使用线程安全的列表
        clients = Collections.synchronizedList(new ArrayList<>());
    }

    @Override
    public void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz) {
        count = Math.max(count, 1);
        for (Peer peer : peers) {
            for (int i = 0; i < count; i++) {
                TransportClient client = ReflectionUtils.newInstance(clazz);
                client.connect(peer);
                clients.add(client);
            }
            log.info("connect server: {}", peer);
        }
    }

    @Override
    public TransportClient select() {
        int i = new Random().nextInt(clients.size());
        return clients.remove(i);
    }

    @Override
    public void release(TransportClient client) {
        clients.add(client);
    }

    @Override
    public void close() {
        for (TransportClient client : clients) {
            client.close();
        }
        clients.clear();
    }
}
