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
