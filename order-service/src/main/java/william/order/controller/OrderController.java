package william.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import william.api.order.dto.OrderDetailDTO;
import william.api.product.dto.SkuDetailDTO;
import william.api.user.dto.UserDetailDTO;
import william.order.feign.SkuServiceFeignClient;
import william.order.feign.UserServiceServiceFeignClient;
import william.order.param.CreateOrderParam;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author ZhangShenao
 * @date 2023/8/7 2:46 PM
 * @description: 订单API
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    
    @Resource
    private UserServiceServiceFeignClient userClient;   //注入Feign客户端,底层是动态代理
    
    @Resource
    private SkuServiceFeignClient skuClient;
    
    /**
     * 创建订单
     */
    @PostMapping("/")
    @SentinelResource("create-order")   //使用注解标识需要被Sentinel流控的资源
    public OrderDetailDTO create(@RequestBody CreateOrderParam param) {
        String userId = param.getUserId();
        String skuId = param.getSkuId();
        
        //Feign远程调用
        UserDetailDTO userDetailDTO = userClient.detail(userId);     //查询用户详情
        SkuDetailDTO skuDetailDTO = skuClient.detail(skuId);    //查询SKU详情
        
        OrderDetailDTO dto = new OrderDetailDTO();
        dto.setUserId(userId);
        dto.setUserName(userDetailDTO.getUserName());
        dto.setSkuId(skuId);
        dto.setSkuTitle(skuDetailDTO.getSkuTitle());
        dto.setOrderId(UUID.randomUUID().toString());
        dto.setPayAmount(param.getPayAmount());
        return dto;
    }
}
