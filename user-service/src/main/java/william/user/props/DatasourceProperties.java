package william.user.props;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author ZhangShenao
 * @date 2023/8/10 10:36 AM
 * @description: 数据源属性
 */
@Component
@RefreshScope   //属性动态刷新
public class DatasourceProperties {
    @Value("${datasource.name}")
    private String datasourceName;
    
    public String getDatasourceName(){
        return datasourceName;
    }
    
}
