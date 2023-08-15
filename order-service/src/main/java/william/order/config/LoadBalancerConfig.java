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
