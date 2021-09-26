package com.mhsfire.myrpc.client;

import com.mhsfire.myrpc.codec.Decoder;
import com.mhsfire.myrpc.codec.Encoder;
import com.mhsfire.myrpc.protocol.Request;
import com.mhsfire.myrpc.protocol.Response;
import com.mhsfire.myrpc.protocol.ServiceDescriptor;
import com.mhsfire.myrpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RemoteInvoker(Class clazz, Encoder encoder, Decoder decoder, TransportSelector selector) {
        this.clazz = clazz;
        this.decoder = decoder;
        this.encoder = encoder;
        this.selector = selector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Request request = new Request();
        request.setService(ServiceDescriptor.from(clazz, method));
        request.setParameters(args);

        // 远程调用
        Response resp = invokeRemote(request);
        if (resp == null || resp.getCode() != 0) {
            throw new IllegalStateException("fail to invoke remote: " + resp);
        }

        return resp.getData();
    }

    /**
     * RPC远程调用
     *
     * @param request 请求
     * @return 请求结果
     */
    private Response invokeRemote(Request request) {
        Response resp;
        TransportClient client = null;

        try {
            // 随机选择一个已经连接好的Client网络模块
            client = selector.select();

            // 序列化请求
            byte[] outBytes = encoder.encode(request);

            // 发送请求，即向输入流中写入数据并获得响应数据
            InputStream receive = client.write(new ByteArrayInputStream(outBytes));

            // 将响应数据转为字节数组
            byte[] inBytes = IOUtils.readFully(receive, receive.available());

            // 反序列化
            resp = decoder.decode(inBytes, Response.class);

        } catch (IOException e) {
            log.warn(e.getMessage(), e);
            resp = new Response();
            resp.setCode(1);
            resp.setMessage("RpcClient got error: " + e.getClass() + ":" + e.getMessage());
        } finally {
            if (client != null) {
                selector.release(client);
            }
        }
        return resp;
    }
}
