package com.mhsfire.myrpc.server;

import com.mhsfire.myrpc.codec.Decoder;
import com.mhsfire.myrpc.codec.Encoder;
import com.mhsfire.myrpc.codec.JSONDecoder;
import com.mhsfire.myrpc.codec.JSONEncoder;
import com.mhsfire.myrpc.transport.HTTPTransportServer;
import com.mhsfire.myrpc.transport.TransportServer;
import lombok.Data;

/**
 * Server配置
 *
 * @PACKAGE_NAME: com.mhsfire.myrpc.server
 * @NAME: RpcServerConfig
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/9/22
 * @PROJECT_NAME: my-rpc
 */
@Data
public class RpcServerConfig {
    private Class<? extends TransportServer> transportClass = HTTPTransportServer.class;
    private Class<? extends Encoder> encodeClass = JSONEncoder.class;
    private Class<? extends Decoder> decodeClass = JSONDecoder.class;
    private int port = 3000;
}
