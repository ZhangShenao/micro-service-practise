package william.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import william.api.user.dto.UserDetailDTO;
import william.user.props.DatasourceProperties;

import javax.annotation.Resource;

/**
 * @author ZhangShenao
 * @date 2023/8/7 2:24 PM
 * @description: 用户API
 */
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Resource
    private DatasourceProperties datasourceProperties;
    
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
    
    /**
     * 查询数据源名称
     */
    @GetMapping("/datasource_name")
    public String datasourceName() {
        return datasourceProperties.getDatasourceName();
    }
}
