package william.order.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import william.api.order.dto.OrderDetailDTO;
import william.api.product.dto.SkuDetailDTO;
import william.api.user.dto.UserDetailDTO;
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
    private RestTemplate restTemplate;
    
//    @Resource
//    private CustomRandomLoadBalanceClient loadBalanceClient;
    
    /**
     * 创建订单
     */
    @PostMapping("/")
    public OrderDetailDTO create(@RequestBody CreateOrderParam param) {
        String userId = param.getUserId();
        String skuId = param.getSkuId();
    
        //使用自定义负载均衡客户端
//        String userUri = loadBalanceClient.getInstanceUri("user-service");
//        String userUrl = userUri + "/user/" + userId;
//        UserDetailDTO userDetailDTO = restTemplate.getForObject(userUrl,
//                UserDetailDTO.class);
//
//        String productUri = loadBalanceClient.getInstanceUri("product-service");
//        String productUrl = productUri + "/sku/" + skuId;
//        SkuDetailDTO skuDetailDTO = restTemplate.getForObject(productUrl,
//                SkuDetailDTO.class);
    
    
        //查询用户详情
        UserDetailDTO userDetailDTO = restTemplate.getForObject("http://user-service/user/" + userId,
                UserDetailDTO.class);

        //查询SKU详情
        SkuDetailDTO skuDetailDTO = restTemplate.getForObject("http://product-service/sku/" + skuId,
                SkuDetailDTO.class);
        
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
