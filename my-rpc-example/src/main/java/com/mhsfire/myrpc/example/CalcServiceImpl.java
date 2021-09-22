package com.mhsfire.myrpc.example;

/**
 * @PACKAGE_NAME: com.mhsfire.myrpc.example
 * @NAME: CalcServiceImpl
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/9/22
 * @PROJECT_NAME: my-rpc
 */
public class CalcServiceImpl implements CalcService {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int minus(int a, int b) {
        return a - b;
    }
}
