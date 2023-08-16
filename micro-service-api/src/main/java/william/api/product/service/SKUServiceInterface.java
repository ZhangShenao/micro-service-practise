package william.api.product.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import william.api.product.dto.SkuDetailDTO;

/**
 * @author ZhangShenao
 * @date 2023/8/16 10:46 AM
 * @description: 商品服务接口定义
 */
public interface SKUServiceInterface {
    /**
     * 查询SKU详情
     */
    @GetMapping("/sku/{sku_id}")
    SkuDetailDTO detail(@PathVariable("sku_id") String skuId);
}
