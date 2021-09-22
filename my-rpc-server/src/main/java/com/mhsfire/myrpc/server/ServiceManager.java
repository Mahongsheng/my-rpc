package com.mhsfire.myrpc.server;

import com.mhsfire.myrpc.common.ReflectionUtils;
import com.mhsfire.myrpc.protocol.Request;
import com.mhsfire.myrpc.protocol.ServiceDescriptor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @PACKAGE_NAME: com.mhsfire.myrpc.server
 * @NAME: ServiceManager
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/9/22
 * @PROJECT_NAME: my-rpc
 */
@Slf4j
public class ServiceManager {
    private Map<ServiceDescriptor, ServiceInstance> services;

    public ServiceManager() {
        this.services = new ConcurrentHashMap<>();
    }

    public <T> void register(Class<T> interfaceClass, T bean) {
        Method[] methods = ReflectionUtils.getPublicMethods(interfaceClass);
        for (Method method : methods) {
            ServiceInstance sis = new ServiceInstance(bean, method);
            ServiceDescriptor sdp = ServiceDescriptor.from(interfaceClass, method);
            services.put(sdp, sis);
            log.info("register service: {} {}", sdp.getClazz(), sdp.getMethod());
        }
    }

    public ServiceInstance lookup(Request request) {
        ServiceDescriptor sdp = request.getService();
        return services.get(sdp);
    }
}
