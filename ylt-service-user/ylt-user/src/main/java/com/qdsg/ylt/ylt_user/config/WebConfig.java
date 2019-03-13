package com.qdsg.ylt.ylt_user.config;

import com.qdsg.ylt.ylt_user.config.properties.RestProperties;
//import com.qdsg.ylt.ylt_user.filter.AuthFilter;
import com.qdsg.ylt.ylt_user.security.DataSecurityAction;
import com.qdsg.ylt.ylt_user.security.impl.Base64SecurityAction;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * web配置
 *
 * @author fengshuonan
 * @date 2017-08-23 15:48
 */
@Configuration
public class WebConfig {

//    @Bean
//    @ConditionalOnProperty(prefix = RestProperties.REST_PREFIX, name = "auth-open", havingValue = "true", matchIfMissing = true)
//    public AuthFilter jwtAuthenticationTokenFilter() {
//        return new AuthFilter();
//    }
    @Bean
    public DataSecurityAction dataSecurityAction() {
        return new Base64SecurityAction();
    }
}
