package william.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import william.api.user.dto.UserDetailDTO;
import william.api.user.service.UserServiceInterface;
import william.user.props.DatasourceProperties;

import javax.annotation.Resource;

/**
 * @author ZhangShenao
 * @date 2023/8/7 2:24 PM
 * @description: 用户API
 */
@RestController
@Slf4j
public class UserController implements UserServiceInterface {
    
    @Resource
    private DatasourceProperties datasourceProperties;
    
    @Value("${PORT}")
    private int port;
    
    @Override
    public UserDetailDTO detail(String userId) {
        log.info("query user detail. port: {}", port);
        
        //模拟请求超时
        //        try {
        //            Thread.sleep(4000L);
        //        } catch (InterruptedException e) {
        //            throw new RuntimeException(e);
        //        }
        //TODO mock
        UserDetailDTO dto = new UserDetailDTO();
        dto.setUserId(userId);
        dto.setUserName("用户-" + userId);
        return dto;
    }
    
    @Override
    public String datasourceName() {
        return datasourceProperties.getDatasourceName();
    }
}
