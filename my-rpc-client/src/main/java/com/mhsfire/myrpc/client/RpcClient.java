package com.mhsfire.myrpc.client;

import com.mhsfire.myrpc.codec.Decoder;
import com.mhsfire.myrpc.codec.Encoder;
import com.mhsfire.myrpc.common.ReflectionUtils;

import java.lang.reflect.Proxy;

/**
 * RPC客户端，在调用方法时通过动态代理的方式进行拦截
 * 可以传入自定义的RPC配置或使用默认配置
 *
 * @PACKAGE_NAME: com.mhsfire.myrpc.client
 * @NAME: RpcClient
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/9/26
 * @PROJECT_NAME: my-rpc
 */
public class RpcClient {
    private RpcClientConfig config;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RpcClient() {
        this(new RpcClientConfig());
    }

    public RpcClient(RpcClientConfig config) {
        this.config = config;
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());
        this.selector = ReflectionUtils.newInstance(config.getSelectorClass());
        this.selector.init(
                this.config.getServers(),
                this.config.getConnectCount(),
                this.config.getTransportClass()
        );
    }

    /**
     * 应用JDK动态代理，该方法主要用来生成一个代理对象
     *
     * @param clazz 被代理实现的接口
     * @return 类的实例
     */
    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{clazz},
                new RemoteInvoker(clazz, encoder, decoder, selector)
        );
    }
}
