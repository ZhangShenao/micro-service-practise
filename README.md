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

# LoadBalance

# Gateway

# Sentinel

# Seata

