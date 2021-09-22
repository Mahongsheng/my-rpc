package com.mhsfire.myrpc.transport;

import com.mhsfire.myrpc.protocol.Peer;

import java.io.InputStream;

/**
 * 1、创建连接 2、发送数据，并且等待响应 3、关闭连接
 *
 * @PACKAGE_NAME: com.mhsfire.myrpc.transport
 * @NAME: TransportClient
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/9/22
 * @PROJECT_NAME: my-rpc
 */
public interface TransportClient {
    void connect(Peer peer);

    InputStream write(InputStream data);

    void close();
}
