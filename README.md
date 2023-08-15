# 微服务技术体系实战

# 核心模块介绍

|          模块名          |          功能           |     备注     |
| :----------------------: | :---------------------: | :----------: |
| micro-service-dependency | 基于 BOM 的统一依赖管理 |              |
|    micro-service-api     |      服务接口定义       |              |
|       user-service       |       用户微服务        | 端口号：1001 |
|     product-service      |       商品微服务        | 端口号：2001 |
|      order-service       |       订单微服务        | 端口号：3001 |

# Spring Cloud Alibaba 版本说明

https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E

# Nacos

## Nacos 整体架构

![Nacos整体架构](docs/Nacos整体架构.png)

## Nacos 逻辑架构与组件

![Nacos逻辑架构与组件](docs/Nacos逻辑架构与组件.png)

## 数据模型

![Nacos数据模型](docs/Nacos数据模型.png)

## 服务领域模型

![Nacos服务领域模型](docs/Nacos服务领域模型.png)

## 服务隔离最佳实践

![服务隔离最佳实践](docs/服务隔离最佳实践.png)

### Namespace：命名空间——租户隔离/环境隔离

### Group：服务分组——业务隔离

### Service：服务的逻辑划分

### Cluster：集群——数据中心隔离/机房隔离

### Instance：具体的服务实例

## 微服务注册中心的核心功能

![微服务注册中心的核心功能.png](docs/微服务注册中心的核心功能.png)

## 服务注册流程

![服务注册流程](docs/服务注册流程.png)

## 服务订阅流程

![服务订阅流程](docs/服务订阅流程.png)

## 心跳上报 & 健康检查流程

![心跳上报&健康检查流程](docs/心跳上报&健康检查流程.png)

## Distro 协议原理

- **每个 Distro 节点都会负责一部分数据，节点自己处理这部分数据的写请求**
- **节点负责的数据发生变更时，会将变更异步同步到其他节点**
- **每个节点会定时将自己负责数据的 checksum 校验值发送到其他节点，以保证数据的最终一致性**
- **新加入集群的 Distro 节点，会从其它节点拉取全量数据，并保存在本地**

## Nacos 内部重要的注册表

- ServiceInfoHolder#serviceInfoMap：客户端的服务列表本地缓存
- AbstractClient#subscribers：服务端的服务订阅列表
- AbstractClient#publishers：服务端的服务通知列表，在服务发生变更时，会回调通知所有订阅的服务

## Nacos 注册中心的使用

### 1. 引入 Nacos 注册中心依赖

```XML
<!--Nacos服务注册中心-->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
```

### 2. 在配置文件中指定注册中心地址

```YAML
spring:
  application:
    name: user-service
  cloud:
    nacos:
      #配置服务&配置中心地址
      server-addr: localhost:8848,localhost:8948,localhost:9048
      # Nacos注册中心配置
      discovery:
        namespace: 9874d759-858b-4564-a5af-81fb8bc02975
```

### 3. 服务启动时，会自动发起注册

## Nacos 配置中心的使用

### 2. 引入 Nacos 配置中心依赖

```XML
<!--Nacos配置中心-->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
</dependency>
```

### 2. 引入 BootStrap 依赖

**引入 BootStrap 依赖，支持服务启动时优先加载 bootstrap.yml 配置文件。**

```XML
<!--BootStrap依赖,支持服务启动时优先加载bootstrap.yml配置文件-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-bootstrap</artifactId>
</dependency>
```

### 3. 创建 bootstrap.yml 配置，指定配置中心地址和配置文件列表

```YAML
spring:
  application:
    name: user-service
  cloud:
    nacos:
      #配置服务&配置中心地址
      server-addr: localhost:8848,localhost:8948,localhost:9048
      # Nacos注册中心配置
      discovery:
        namespace: 9874d759-858b-4564-a5af-81fb8bc02975

      # Nacos配置中心配置
      config:
        namespace: 9874d759-858b-4564-a5af-81fb8bc02975
        group: USER_SERVICE
        file-extension: yml
        extension-configs:
          - data-id: user-service-datasource.yml
            group: USER_SERVICE
            refresh: true
```

### 4. 在程序中加载配置，并使用 @RefreshScope 注解实现配置自动刷新。

```Java
/**
 * @author ZhangShenao
 * @date 2023/8/10 10:36 AM
 * @description: 数据源属性
 */
@Component
@RefreshScope   //属性动态刷新
public class DatasourceProperties {
    
    @Value("${datasource.name}")
    private String datasourceName;
    
    public String getDatasourceName() {
        return datasourceName;
    }
    
}
```

# 负载均衡

# 负载均衡的分类

## 服务端负载均衡

由负载均衡服务器（代理服务器）实现负载均衡策略，给客户端返回一个最终可调用的实例地址。

典型实现：Nginx

## 客户端负载均衡

由客户端自行实现负载均衡策略，从注册中心拉取待调用服务的全量实例列表，自行决定向哪个实例发起调用。

典型实现：Spring Cloud LoadBalancer、Ribbon

**实际微服务治理中场景中，主流方案都是客户端负载均衡。**

## 自定义负载均衡客户端

基于 `DiscoveryClient` 实现。

```Java
package william.order.client;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ZhangShenao
 * @date 2023/8/10 2:27 PM
 * @description: 基于DiscoveryClient, 实现自定义负载均衡客户端, 采用随机算法
 */
@Component
public class CustomRandomLoadBalanceClient {
    
    @Resource
    private DiscoveryClient discoveryClient;
    
    /**
     * 获取服务实例URI
     *
     * @param serviceName 服务名
     * @return 实例URI
     */
    public String getInstanceUri(String serviceName) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        if (CollectionUtils.isEmpty(instances)) {
            throw new RuntimeException("no available instance for service: " + serviceName);
        }
        ServiceInstance chosen = instances.get(ThreadLocalRandom.current().nextInt(0, instances.size()));
        return chosen.getUri().toString();
    }
}
```

# Spring Cloud Loadbalancer 的使用

## 1. 引入依赖

```XML
<!--Spring Cloud LoadBalancer 负载均衡实现-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-loadbalancer</artifactId>
</dependency>
```

## 2. 注入 RestTemplate 实现，并增加负载均衡功能

```Java
@LoadBalanced   //负载均衡实现
@Bean
public RestTemplate restTemplate() {
    return new RestTemplate();
}
```

- 在 `LoadBalancerAutoConfiguration` 中注入 `LoadBalancerInterceptor` 拦截器，为 `RestTemplate` 增加负载均衡功能。
- Spring Cloud Loadbalancer 会在 `BlockingLoadBalancerClientAutoConfiguration` 中注入 **`BlockingLoadBalancerClient`** ，作为负载均衡客户端的默认实现。**默认的负载均衡算法为 Round-Robin。**

# 自定义负载均衡策略

## 1. 自定义负载均衡器

```Java
package william.order.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ZhangShenao
 * @date 2023/8/15 2:29 PM
 * @description: 自定义负载均衡器, 采用随机算法
 */
@Slf4j
public class CustomRandomLoadBalancer implements ReactorServiceInstanceLoadBalancer {
    
    /**
     * 服务列表
     */
    private ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;
    
    public CustomRandomLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider) {
        this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
    }
    
    /**
     * 实现负载均衡算法
     */
    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {
        ServiceInstanceListSupplier supplier = serviceInstanceListSupplierProvider.getIfAvailable();
        return supplier.get().next().map(this::randomServiceInstance);
    }
    
    
    private Response<ServiceInstance> randomServiceInstance(List<ServiceInstance> instances) {
        log.info("custom random load balance");
        if (CollectionUtils.isEmpty(instances)) {
            return new EmptyResponse();
        }
        int idx = ThreadLocalRandom.current().nextInt(0, instances.size());
        return new DefaultResponse(instances.get(idx));
    }
}
```

## 2. 负载均衡配置

```Java
package william.order.config;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClientConfiguration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.context.annotation.Configuration;
import william.order.client.CustomRandomLoadBalancer;

/**
 * @author ZhangShenao
 * @date 2023/8/15 2:39 PM
 * @description: 负载均衡配置
 */
@Configuration
@LoadBalancerClients(defaultConfiguration = LoadBalancerClientConfiguration.class,    //默认全局配置
        value = {@LoadBalancerClient(value = "user-service", configuration = CustomRandomLoadBalancer.class), //服务级别配置
                @LoadBalancerClient(value = "product-service", configuration = CustomRandomLoadBalancer.class),})
public class LoadBalancerConfig {
    
}
```

# 负载均衡实现流程

![负载均衡实现流程](docs/负载均衡实现流程.png)
