package com.mhsfire.myrpc.client;

import com.mhsfire.myrpc.codec.Decoder;
import com.mhsfire.myrpc.codec.Encoder;
import com.mhsfire.myrpc.codec.JSONDecoder;
import com.mhsfire.myrpc.codec.JSONEncoder;
import com.mhsfire.myrpc.protocol.Peer;
import com.mhsfire.myrpc.transport.HTTPTransportClient;
import com.mhsfire.myrpc.transport.TransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * RPC连接配置，包括网络模块、序列化、反序列化以及选择器、连接数、服务端节点列表
 *
 * @PACKAGE_NAME: com.mhsfire.myrpc.client
 * @NAME: RpcClientConfig
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/9/26
 * @PROJECT_NAME: my-rpc
 */
@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass = HTTPTransportClient.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;
    private int connectCount = 1;
    private List<Peer> servers = Arrays.asList(
            new Peer("127.0.0.1", 3000)
    );
}
