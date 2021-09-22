package com.mhsfire.myrpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * JSON反序列化
 *
 * @PACKAGE_NAME: com.mhsfire.myrpc.codec
 * @NAME: JSONDecoder
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/9/22
 * @PROJECT_NAME: my-rpc
 */
public class JSONDecoder implements Decoder {

    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
