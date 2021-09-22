package com.mhsfire.myrpc;

import lombok.Data;

/**
 * 表示网络传输的一个端点
 *
 * @PACKAGE_NAME: com.mhsfire.myrpc
 * @NAME: Peer
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/9/22
 * @PROJECT_NAME: my-rpc
 */
@Data
public class Peer {
    private String host;

    private int port;
}
