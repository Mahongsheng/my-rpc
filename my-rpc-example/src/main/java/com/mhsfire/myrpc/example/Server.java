package com.mhsfire.myrpc.example;

import com.mhsfire.myrpc.server.RpcServer;

/**
 * @PACKAGE_NAME: com.mhsfire.myrpc.example
 * @NAME: Server
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/9/22
 * @PROJECT_NAME: my-rpc
 */
public class Server {
    public static void main(String[] args) {
        RpcServer server = new RpcServer();
        server.register(CalcService.class, new CalcServiceImpl());
        server.start();
    }
}
