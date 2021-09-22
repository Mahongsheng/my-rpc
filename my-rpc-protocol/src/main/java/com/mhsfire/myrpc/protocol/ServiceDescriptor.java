package com.mhsfire.myrpc.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
