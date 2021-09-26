package com.mhsfire.myrpc.server;

import com.mhsfire.myrpc.common.ReflectionUtils;
import com.mhsfire.myrpc.protocol.Request;

/**
 * 通过反射的方式调用类方法
 *
 * @PACKAGE_NAME: com.mhsfire.myrpc.server
 * @NAME: ServiceInvoker
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/9/22
 * @PROJECT_NAME: my-rpc
 */
public class ServiceInvoker {
    /**
     * 调用类方法
     *
     * @param service 类实例
     * @param request 请求
     * @return 该方法返回的结果
     */
    public Object invoke(ServiceInstance service, Request request) {
        return ReflectionUtils.invoke(
                service.getTarget(),
                service.getMethod(),
                request.getParameters());
    }
}
