package com.mhsfire.myrpc.example;

import com.mhsfire.myrpc.client.RpcClient;

/**
 * @PACKAGE_NAME: com.mhsfire.myrpc.example
 * @NAME: Client
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/9/22
 * @PROJECT_NAME: my-rpc
 */
public class Client {

    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        CalcService service = client.getProxy(CalcService.class);
        int r1 = service.add(1, 2);
        int r2 = service.minus(10, 8);
        System.out.println(r1);
        System.out.println(r2);
    }
}
