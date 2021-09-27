# My-RPC

参照慕课网[《自己动手实现RPC框架》](https://www.imooc.com/learn/1158)课程实现的简易RPC框架，在原有项目基础上优化了部分代码结构、添加更加详尽的注释并总结笔记。

## 什么是RPC

RPC是远程过程调用（Remote Procedure Call）的缩写形式。

假设我们有两台服务器A与B，一个在A服务器上部署的应用想要调用B服务器上部署的应用的函数、方法，由于不在同一个内存空间，不能直接调用，因此需要通过网络来表达调用的语义和传达调用的数据。在Java中，即将被调用的类、方法、参数序列化后通过网络传到目标应用，通过反射的方式调用执行。

![整体架构](https://github.com/Mahongsheng/my-rpc/raw/main/images/RPC%E6%95%B4%E4%BD%93%E6%9E%B6%E6%9E%84.png)



## 数据交换方式

利用中间件进行数据交换

![中间件-数据交互](https://github.com/Mahongsheng/my-rpc/raw/main/images/%E4%B8%AD%E9%97%B4%E4%BB%B6-%E6%95%B0%E6%8D%AE%E4%BA%A4%E4%BA%92.png)

直接进行数据交换

![直接数据交互](https://github.com/Mahongsheng/my-rpc/raw/main/images/%E7%9B%B4%E6%8E%A5%E6%95%B0%E6%8D%AE%E4%BA%A4%E4%BA%92.png)

## 现有RPC框架对比

目前主流的RPC框架如下表所示：

![现有RPC框架对比](https://github.com/Mahongsheng/my-rpc/raw/main/images/%E7%8E%B0%E6%9C%89RPC%E6%A1%86%E6%9E%B6%E5%AF%B9%E6%AF%94.png)

## 核心原理

### 调用流程

![调用流程](https://github.com/Mahongsheng/my-rpc/raw/main/images/%E8%B0%83%E7%94%A8%E6%B5%81%E7%A8%8B.jpg)

- Server: Provider，服务提供者
- Client: Consumer，服务消费者
- Stub: 存根，服务描述

一次函数调用的流程如下：

1. 首先客户端需要告诉服务端，需要调用的函数，这里函数和进程存在一个映射，客户端远程调用时，需要查一下函数，找到对应的标识，然后执行函数的代码。
2. 客户端需要把本地参数传给远程函数，本地调用的过程中，直接压栈即可，但是在远程调用过程中不在同一个内存里，无法直接传递函数的参数，因此需要客户端把参数转换成字节流，传给服务端，然后服务端将字节流转换成自身能读取的格式，是一个序列化和反序列化的过程。
3. 数据准备好了之后，如何进行传输？网络传输层需要把调用的ID和序列化后的参数传给服务端，然后把计算好的结果序列化传给客户端，因此TCP层即可完成上述过程。

### 架构

```lua
my-rpc
├── my-rpc-client    -- 客户端
├── my-rpc-codec     -- 序列化
├── my-rpc-common    -- 反射
├── my-rpc-protocol  -- 订单微服务模块
├── my-rpc-server    -- 服务端
├── my-rpc-transport -- 网络
└── my-rpc-example   -- 例子
```

模块依赖关系如下图所示，my-rpc-server和my-rpc-client依赖关系相同。

![模块依赖关系](https://github.com/Mahongsheng/my-rpc/raw/main/images/%E6%A8%A1%E5%9D%97%E4%BE%9D%E8%B5%96%E5%85%B3%E7%B3%BB.png)

## 技术栈

- 基础：Java、Maven、反射、JDK动态代理
- 序列化：FastJson
- 网络：Jetty、URLConnection

