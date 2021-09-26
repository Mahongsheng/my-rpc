package com.mhsfire.myrpc.transport;

import com.mhsfire.myrpc.protocol.Peer;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 客户端实现
 *
 * @PACKAGE_NAME: com.mhsfire.myrpc.transport
 * @NAME: HTTPTransportClient
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/9/22
 * @PROJECT_NAME: my-rpc
 */
public class HTTPTransportClient implements TransportClient {
    private String url;

    @Override
    public void connect(Peer peer) {
        // 因为在此使用的是TCP短连接（想要使用长连接可以考虑套接字）
        // 所以connect方法只是象征性初始化url数据，没有进行网络请求
        this.url = "http://" + peer.getHost() + ":" + peer.getPort();
    }

    @Override
    public InputStream write(InputStream data) {
        // 在write方法中，我们进行了网络请求
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.connect();
            IOUtils.copy(data, httpURLConnection.getOutputStream());

            int resultCode = httpURLConnection.getResponseCode();
            if (resultCode == HttpURLConnection.HTTP_OK) {
                return httpURLConnection.getInputStream();
            } else {
                return httpURLConnection.getErrorStream();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() {
        // 同connect方法，close方法只是进行了一次模拟
    }
}
