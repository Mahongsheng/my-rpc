package com.mhsfire.myrpc;

import com.alibaba.fastjson.JSON;

/**
 * JSON序列化
 *
 * @PACKAGE_NAME: com.mhsfire.myrpc
 * @NAME: JSONEncoder
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/9/22
 * @PROJECT_NAME: my-rpc
 */
public class JSONEncoder implements Encoder {
    @Override
    public byte[] encode(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}
