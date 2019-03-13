package com.qdsg.ylt.ylt_jwt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * jwt相关配置
 *
 * @author fengshuonan
 * @date 2017-08-23 9:23
 */
@Configuration
@ConfigurationProperties(prefix = JwtClientProperties.JWT_PREFIX)
public class JwtClientProperties {

    public static final String JWT_PREFIX = "jwt-client";

    private String header = "Authorization";

    public String getUserServer() {
        return userServer;
    }

    public void setUserServer(String userServer) {
        this.userServer = userServer;
    }

    private String userServer = "";

    public static String getJwtPrefix() {
        return JWT_PREFIX;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public boolean isRemoteMode() {
        return remoteMode;
    }

    public void setRemoteMode(boolean remoteMode) {
        this.remoteMode = remoteMode;
    }

    private boolean remoteMode = false;



}
