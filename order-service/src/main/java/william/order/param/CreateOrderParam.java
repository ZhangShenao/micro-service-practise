package william.order.param;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

/**
 * @author ZhangShenao
 * @date 2023/8/7 2:52 PM
 * @description: 创建订单请求参数
 */
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateOrderParam {
    
    /**
     * 用户ID
     */
    private String userId;
    
    /**
     * 商品ID
     */
    private String skuId;
    
    /**
     * 支付金额
     */
    private long payAmount;
}
