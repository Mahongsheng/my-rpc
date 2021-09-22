package com.mhsfire.myrpc.protocol;

import lombok.Data;

/**
 * 表示RPC的返回
 *
 * @PACKAGE_NAME: com.mhsfire.myrpc.protocol
 * @NAME: Response
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/9/22
 * @PROJECT_NAME: my-rpc
 */
@Data
public class Response {
    /**
     * 服务返回编码，0-成功，非0失败
     */
    private int code = 0;

    /**
     * 具体的错误信息
     */
    private String message = "ok";

    /**
     * 返回的数据
     */
    private Object data;
}
