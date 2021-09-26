package com.mhsfire.myrpc.server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * 服务的实例，包含类实例与方法
 *
 * @PACKAGE_NAME: com.mhsfire.myrpc.server
 * @NAME: ServiceInstance
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/9/22
 * @PROJECT_NAME: my-rpc
 */
@Data
@AllArgsConstructor
public class ServiceInstance {
    /**
     * 类实例
     */
    private Object target;

    /**
     * 方法
     */
    private Method method;
}
