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
