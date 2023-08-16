package william.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import william.order.config.FeignConfig;

/**
 * @author ZhangShenao
 * @date 2023/8/7 11:16 AM
 * @description: 订单服务启动类
 */
@SpringBootApplication
@EnableFeignClients(defaultConfiguration = FeignConfig.class) //开启Feign客户端功能,并指定默认配置
public class OrderServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
