package william.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author ZhangShenao
 * @date 2023/8/7 2:55 PM
 * @description: RestTemplate配置
 */
@Configuration
public class RestTemplateConfig {
    
    @LoadBalanced   //负载均衡实现
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
