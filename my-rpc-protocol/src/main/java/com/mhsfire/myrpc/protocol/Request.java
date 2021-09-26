package com.mhsfire.myrpc.protocol;

import lombok.Data;

/**
 * 表示RPC的一个请求
 *
 * @PACKAGE_NAME: com.mhsfire.myrpc.protocol
 * @NAME: Request
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/9/22
 * @PROJECT_NAME: my-rpc
 */
@Data
public class Request {
    /**
     * 请求中包含的服务描述，用于定位需要调用的类的方法（排除重载）
     */
    private ServiceDescriptor service;

    /**
     * 调用的方法需要的参数（传参）
     */
    private Object[] parameters;
}
