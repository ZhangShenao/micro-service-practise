package william.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import william.api.user.service.UserServiceInterface;

/**
 * @author ZhangShenao
 * @date 2023/8/16 10:50 AM
 * @description: 用户服务Feign远程客户端
 */
@FeignClient(value = "user-service")    //声明Feign远程客户端
public interface UserServiceServiceFeignClient extends UserServiceInterface {

}
