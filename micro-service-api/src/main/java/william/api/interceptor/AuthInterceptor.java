package william.api.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static william.api.constants.AuthConstants.*;

/**
 * @author ZhangShenao
 * @date 2023/8/16 11:47 AM
 * @description: 鉴权拦截器
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //校验请求Token
        String token = request.getHeader(TOKEN_HEADER_KEY);
        log.info("auth token: {}", token);
        return StringUtils.hasText(token);
    }
}
