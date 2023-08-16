package william.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import william.api.product.service.SKUServiceInterface;

/**
 * @author ZhangShenao
 * @date 2023/8/16 10:53 AM
 * @description: 商品服务Feign远程客户端
 */
@FeignClient(value = "product-service")
public interface SkuServiceFeignClient extends SKUServiceInterface {

}
