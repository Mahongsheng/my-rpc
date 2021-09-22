package com.mhsfire.myrpc.transport;

/**
 * 1、启动、监听 2、接收请求 3、关闭监听
 *
 * @PACKAGE_NAME: com.mhsfire.myrpc.transport
 * @NAME: TransportServer
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/9/22
 * @PROJECT_NAME: my-rpc
 */
public interface TransportServer {
    void init(int port, RequestHandler handler);

    void start();

    void stop();
}
