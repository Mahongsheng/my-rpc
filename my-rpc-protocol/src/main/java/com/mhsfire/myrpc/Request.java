package com.mhsfire.myrpc;

import lombok.Data;

/**
 * 表示RPC的一个请求
 *
 * @PACKAGE_NAME: com.mhsfire.myrpc
 * @NAME: Request
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/9/22
 * @PROJECT_NAME: my-rpc
 */
@Data
public class Request {
    private ServiceDescriptor service;
    private Object[] parameters;
}
