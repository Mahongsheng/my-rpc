package com.mhsfire.myrpc.server;

import com.mhsfire.myrpc.common.ReflectionUtils;
import com.mhsfire.myrpc.protocol.Request;
import com.mhsfire.myrpc.protocol.ServiceDescriptor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务管理器，主要通过服务的描述来注册和找到服务的实例
 *
 * @PACKAGE_NAME: com.mhsfire.myrpc.server
 * @NAME: ServiceManager
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/9/22
 * @PROJECT_NAME: my-rpc
 */
@Slf4j
public class ServiceManager {
    /**
     * 每个服务的描述对应一个服务的实例，方便我们进行调用
     */
    private Map<ServiceDescriptor, ServiceInstance> services;

    public ServiceManager() {
        // 此处使用线程安全的HashMap，防止多线程同时注册服务实例导致服务实例重复
        this.services = new ConcurrentHashMap<>();
    }

    /**
     * 注册服务描述与服务实例的映射关系
     *
     * @param interfaceClass 服务描述
     * @param bean           服务实例
     * @param <T>            泛型方法标识
     */
    public <T> void register(Class<T> interfaceClass, T bean) {
        Method[] methods = ReflectionUtils.getPublicMethods(interfaceClass);
        for (Method method : methods) {
            ServiceInstance sis = new ServiceInstance(bean, method);
            ServiceDescriptor sdp = ServiceDescriptor.from(interfaceClass, method);
            services.put(sdp, sis);
            log.info("register service: {} {}", sdp.getClazz(), sdp.getMethod());
        }
    }

    /**
     * 根据服务的描述获取服务实例
     *
     * @param request 请求
     * @return 服务实例
     */
    public ServiceInstance lookup(Request request) {
        ServiceDescriptor sdp = request.getService();
        return services.get(sdp);
    }
}
