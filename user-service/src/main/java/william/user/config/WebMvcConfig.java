package william.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import william.api.interceptor.AuthInterceptor;

/**
 * @author ZhangShenao
 * @date 2023/8/16 11:50 AM
 * @description: MVC配置
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //注册鉴权拦截器
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**");
    }
}
