package com.mhsfire.myrpc.server;

import com.mhsfire.myrpc.codec.Decoder;
import com.mhsfire.myrpc.codec.Encoder;
import com.mhsfire.myrpc.common.ReflectionUtils;
import com.mhsfire.myrpc.protocol.Request;
import com.mhsfire.myrpc.protocol.Response;
import com.mhsfire.myrpc.transport.RequestHandler;
import com.mhsfire.myrpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * RPC服务端，可以传入自定义的RPC配置或使用默认配置
 *
 * @PACKAGE_NAME: com.mhsfire.myrpc.server
 * @NAME: RpcServer
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/9/22
 * @PROJECT_NAME: my-rpc
 */
@Slf4j
public class RpcServer {
    private RpcServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    public RpcServer() {
        this(new RpcServerConfig());
    }

    public RpcServer(RpcServerConfig config) {
        this.config = config;

        // net
        this.net = ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(), this.handler);

        // codec
        this.encoder = ReflectionUtils.newInstance(config.getEncodeClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecodeClass());

        // service
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }

    /**
     * 注册服务实例
     *
     * @param interfaceClass 服务的描述
     * @param bean           服务实例
     * @param <T>            泛型标识符
     */
    public <T> void register(Class<T> interfaceClass, T bean) {
        serviceManager.register(interfaceClass, bean);
    }

    /**
     * 开启监听并等待连接
     */
    public void start() {
        this.net.start();
    }

    /**
     * 关闭监听
     */
    public void stop() {
        this.net.stop();
    }

    /**
     * 重写请求处理器
     */
    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream receive, OutputStream toResp) {
            Response resp = new Response();

            try {
                // 读取请求
                byte[] inBytes = IOUtils.readFully(receive, receive.available());

                // 反序列化
                Request request = decoder.decode(inBytes, Request.class);
                log.info("get request: {}", request);

                // 找到服务的实例
                ServiceInstance sis = serviceManager.lookup(request);

                // 调用方法并获得返回值
                Object ret = serviceInvoker.invoke(sis, request);

                // 将结果写入响应
                resp.setData(ret);

            } catch (Exception e) {
                log.warn(e.getMessage(), e);
                resp.setCode(1);
                resp.setMessage("RpcServer got error: " + e.getClass().getName() + " : " + e.getMessage());
            } finally {
                try {
                    // 序列化
                    byte[] outBytes = encoder.encode(resp);

                    // 写入流中
                    toResp.write(outBytes);

                    log.info("response client");
                } catch (IOException e) {
                    log.warn(e.getMessage(), e);
                }
            }
        }
    };
}
