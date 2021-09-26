package com.mhsfire.myrpc.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 表示网络传输的一个端点
 *
 * @PACKAGE_NAME: com.mhsfire.myrpc.protocol
 * @NAME: Peer
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/9/22
 * @PROJECT_NAME: my-rpc
 */
@Data
@AllArgsConstructor
public class Peer {
    /**
     * IP地址
     */
    private String host;

    /**
     * 端口
     */
    private int port;
}
