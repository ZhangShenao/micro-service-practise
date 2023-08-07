package william.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import william.api.product.dto.SkuDetailDTO;

/**
 * @author ZhangShenao
 * @date 2023/8/7 2:42 PM
 * @description: 商品API
 */
@RestController
@RequestMapping("/sku")
public class SKUController {
    
    /**
     * 查询SKU详情
     */
    @GetMapping("/{sku_id}")
    public SkuDetailDTO detail(@PathVariable("sku_id") String skuId) {
        //TODO mock
        SkuDetailDTO dto = new SkuDetailDTO();
        dto.setSkuId(skuId);
        dto.setSkuTitle("商品-" + skuId);
        return dto;
    }
}
