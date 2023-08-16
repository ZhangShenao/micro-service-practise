package william.api.user.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import william.api.user.dto.UserDetailDTO;

/**
 * @author ZhangShenao
 * @date 2023/8/16 10:39 AM
 * @description: 用户服务接口定义
 */
public interface UserServiceInterface {
    
    /**
     * 查询用户详情
     */
    @GetMapping("/user/{user_id}")
    UserDetailDTO detail(@PathVariable("user_id") String userId);
    
    /**
     * 查询数据源名称
     */
    @GetMapping("/user/datasource_name")
    String datasourceName();
}
