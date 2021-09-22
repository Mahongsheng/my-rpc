package com.mhsfire.myrpc.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * 表示服务
 *
 * @PACKAGE_NAME: com.mhsfire.myrpc.protocol
 * @NAME: ServiceDescriptor
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/9/22
 * @PROJECT_NAME: my-rpc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {
    private String clazz;

    private String method;

    private String returnType;

    private String[] parameterTypes;

    public static ServiceDescriptor from(Class clazz, Method method){
        ServiceDescriptor sdp = new ServiceDescriptor();
        sdp.setClazz(clazz.getName());
        sdp.setMethod(method.getName());
        sdp.setReturnType(method.getReturnType().getName());
        Class<?>[] parameterClasses = method.getParameterTypes();
        String[] parameterTypes = new String[parameterClasses.length];
        for (int i=0; i<parameterClasses.length; i++){
            parameterTypes[i] = parameterClasses[i].getName();
        }
        sdp.setParameterTypes(parameterTypes);
        return sdp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceDescriptor that = (ServiceDescriptor) o;
        return Objects.equals(clazz, that.clazz) &&
                Objects.equals(method, that.method) &&
                Objects.equals(returnType, that.returnType) &&
                Arrays.equals(parameterTypes, that.parameterTypes);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(clazz, method, returnType);
        result = 31 * result + Arrays.hashCode(parameterTypes);
        return result;
    }
}
