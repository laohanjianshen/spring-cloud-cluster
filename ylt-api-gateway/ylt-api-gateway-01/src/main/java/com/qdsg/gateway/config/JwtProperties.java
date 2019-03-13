package com.qdsg.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.List;

/**
 * jwt相关配置
 *
 * @author fengshuonan
 * @date 2017-08-23 9:23
 */
@Configuration
@ConfigurationProperties(prefix = JwtProperties.JWT_PREFIX)
@RefreshScope
@Data
public class JwtProperties implements Serializable {
    private static final long serialVersionUID = 2986908638598584021L;

    public static final String JWT_PREFIX = "jwt";

    private String header = "Authorization";

    private String secret = "defaultSecret";

    private Long expiration = 604800L;

    private String authPath = "auth";

    private String md5Key = "randomKey";

    private String doctor = "doctor";

    private String userId = "userId";

    private List<String> whiteUriList;
}
