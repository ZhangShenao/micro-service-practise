package william.order.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.UUID;

import static william.api.constants.AuthConstants.TOKEN_HEADER_KEY;

/**
 * @author ZhangShenao
 * @date 2023/8/16 11:35 AM
 * @description: Feign鉴权拦截器
 */
public class FeignAuthInterceptor implements RequestInterceptor {
    
    @Override
    public void apply(RequestTemplate requestTemplate) {
        //设置鉴权Token
        requestTemplate.header(TOKEN_HEADER_KEY, UUID.randomUUID().toString());
    }
}
