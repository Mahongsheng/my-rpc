package com.mhsfire.myrpc.server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * 服务的实例
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
    private Object target;
    private Method method;
}
