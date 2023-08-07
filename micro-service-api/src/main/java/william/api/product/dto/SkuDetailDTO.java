package william.api.product.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ZhangShenao
 * @date 2023/8/7 2:31 PM
 * @description: 商品详情DTO
 */
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SkuDetailDTO implements Serializable {
    
    /**
     * 商品ID
     */
    private String skuId;
    
    /**
     * 商品名称
     */
    private String skuTitle;
}
