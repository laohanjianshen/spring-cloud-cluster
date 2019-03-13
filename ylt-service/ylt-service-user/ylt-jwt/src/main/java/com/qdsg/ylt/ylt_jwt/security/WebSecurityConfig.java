package com.qdsg.ylt.ylt_jwt.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * web配置
 *
 * @author fengshuonan
 * @date 2017-08-23 15:48
 */
@Configuration
@ConditionalOnClass(JwtAuthencationTokenFilter.class)
public class WebSecurityConfig {

  @Bean
  public JwtAuthencationTokenFilter jwtAuthenticationTokenFilter() {
    return new JwtAuthencationTokenFilter();
  }

}
