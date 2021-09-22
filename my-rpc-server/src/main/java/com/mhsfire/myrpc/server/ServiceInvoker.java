package com.mhsfire.myrpc.server;

import com.mhsfire.myrpc.common.ReflectionUtils;
import com.mhsfire.myrpc.protocol.Request;

/**
 * @PACKAGE_NAME: com.mhsfire.myrpc.server
 * @NAME: ServiceInvoker
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/9/22
 * @PROJECT_NAME: my-rpc
 */
public class ServiceInvoker {
    public Object invoke(ServiceInstance service, Request request) {
        return ReflectionUtils.invoke(
                service.getTarget(),
                service.getMethod(),
                request.getParameters());
    }
}
