package william.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShenao
 * @date 2023/8/29 10:02 AM
 * @description: 熔断降级API
 */
@RestController
@RequestMapping("/circuit_breaker")
public class CircuitBreakerController {
    
    @GetMapping("/slow_request_ratio")
    public String slowRequestRatio(@RequestParam("order_id") String orderId) throws InterruptedException {
        if ("1".equalsIgnoreCase(orderId)) {
            //模拟慢调用
            Thread.sleep(500L);
        }
        return "熔断规则——慢调用比例";
    }
    
    @GetMapping("/exception_ratio")
    public String exceptionRatio(@RequestParam("order_id") String orderId) {
        if ("1".equalsIgnoreCase(orderId)) {
            //模拟业务异常
            throw new RuntimeException("business exception");
        }
        return "熔断规则——异常比例";
    }
}
