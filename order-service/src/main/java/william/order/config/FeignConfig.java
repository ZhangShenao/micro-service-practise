package william.order.config;

import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import william.order.interceptor.FeignAuthInterceptor;

import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShenao
 * @date 2023/8/16 11:26 AM
 * @description: Feign配置
 */
public class FeignConfig {
    
    //开启全量日志
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
    
    //自定义请求拦截器
    @Bean
    public FeignAuthInterceptor feignAuthInterceptor() {
        return new FeignAuthInterceptor();
    }
    
    //超时配置
    @Bean
    public Request.Options feignRequestOptions() {
        return new Request.Options(5000L, TimeUnit.MILLISECONDS, 2000L, TimeUnit.MICROSECONDS, true);
    }
}
