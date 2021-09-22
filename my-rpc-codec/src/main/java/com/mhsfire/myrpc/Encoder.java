package com.mhsfire.myrpc;

/**
 * 序列化
 *
 * @PACKAGE_NAME: com.mhsfire.myrpc
 * @NAME: Encoder
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/9/22
 * @PROJECT_NAME: my-rpc
 */
public interface Encoder {
    byte[] encode(Object obj);
}
