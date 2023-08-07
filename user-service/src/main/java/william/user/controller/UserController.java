package william.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import william.api.user.dto.UserDetailDTO;

/**
 * @author ZhangShenao
 * @date 2023/8/7 2:24 PM
 * @description: 用户API
 */
@RestController
@RequestMapping("/user")
public class UserController {
    
    /**
     * 查询用户详情
     */
    @GetMapping("/{user_id}")
    public UserDetailDTO detail(@PathVariable("user_id") String userId) {
        //TODO mock
        UserDetailDTO dto = new UserDetailDTO();
        dto.setUserId(userId);
        dto.setUserName("用户-" + userId);
        return dto;
    }
}
