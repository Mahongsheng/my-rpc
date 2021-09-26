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

    /**
     * 启动jetty
     *
     * @param port    端口
     * @param handler 请求处理器
     */
    void init(int port, RequestHandler handler);

    /**
     * 开始等待连接
     */
    void start();

    /**
     * 关闭jetty
     */
    void stop();
}
