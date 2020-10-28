package shiro.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;

/**
 * Notice:
 *
 * @author xuxu
 * @version 1.0
 * @date 2020/10/27
 * @since 1.0
 */
@Getter
@Component
@PropertySource(value={"classpath:test.properties"})
public class ShiroProperty {
    @Value("${security.shiro.loginUrl:}")
    private String loginUrl;
    @Value("${security.shiro.unauthorizedUrl:}")
    private String unauthorizedUrl;
    @Value("${security.shiro.successUrl:}")
    private String successUrl;
    @Value("${security.shiro.ignore-urls:}")
    private String ignoreUrl;
    /**
     * 配置匿名访问FilterChain
     * @param filterChainDefinitionMap 过滤链配置集合
     */
    public void configAnonFilterChain(LinkedHashMap<String, String> filterChainDefinitionMap){
        if(StringUtils.isEmpty(ignoreUrl)){return;}
        String[] urls=StringUtils.split(ignoreUrl,",");
        for (String url:urls) {
            filterChainDefinitionMap.put(url,"anon");
        }
    }
}
