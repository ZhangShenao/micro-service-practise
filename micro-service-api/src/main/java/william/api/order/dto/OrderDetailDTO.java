package william.api.order.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ZhangShenao
 * @date 2023/8/7 2:47 PM
 * @description: 订单详情DTO
 */
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderDetailDTO implements Serializable {
    
    /**
     * 用户ID
     */
    private String userId;
    
    /**
     * 用户名
     */
    private String userName;
    
    /**
     * 商品ID
     */
    private String skuId;
    
    /**
     * 商品名称
     */
    private String skuTitle;
    
    /**
     * 订单ID
     */
    private String orderId;
    
    /**
     * 支付金额
     */
    private long payAmount;
}
