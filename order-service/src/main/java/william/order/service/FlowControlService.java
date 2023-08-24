package william.order.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @author ZhangShenao
 * @date 2023/8/24 10:57 AM
 * @description: 流控Service
 */
@Service
public class FlowControlService {
    
    /**
     * 访问共享资源
     */
    @SentinelResource("shared-resource")    //通过注解指定资源名称
    public String visitSharedResource() {
        return "访问共享资源";
    }
}
