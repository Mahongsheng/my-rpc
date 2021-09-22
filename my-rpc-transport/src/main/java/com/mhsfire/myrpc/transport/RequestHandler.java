package com.mhsfire.myrpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 请求处理器
 *
 * @PACKAGE_NAME: com.mhsfire.myrpc.transport
 * @NAME: RequestHandler
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/9/22
 * @PROJECT_NAME: my-rpc
 */
public interface RequestHandler {
    void onRequest(InputStream receive, OutputStream toResp);
}
