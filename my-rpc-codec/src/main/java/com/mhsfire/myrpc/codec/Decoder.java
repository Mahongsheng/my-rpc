package com.mhsfire.myrpc.codec;

/**
 * 反序列化
 *
 * @PACKAGE_NAME: com.mhsfire.myrpc.codec
 * @NAME: Decoder
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/9/22
 * @PROJECT_NAME: my-rpc
 */
public interface Decoder {
    <T> T decode(byte[] bytes, Class<T> clazz);
}
