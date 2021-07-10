package com.rhb.admin.security;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * 添加Spring Security的配置
 *
 * @author renhuibo
 * @date 2021/7/10 14:43
 */
@Configuration
@Order(Ordered.LOWEST_PRECEDENCE)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final String adminContextPath;

  public SecurityConfig(AdminServerProperties adminServerProperties) {
    this.adminContextPath = adminServerProperties.getContextPath();
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
    successHandler.setTargetUrlParameter("redirectTo");
    http.authorizeRequests()
        .antMatchers(adminContextPath + "/assets/**").permitAll()
        //登录页面允许访问
        .antMatchers(adminContextPath + "/login").permitAll()
        //其他所有请求需要登录
        .anyRequest().authenticated().and()
        //登录页面配置替换security默认页面
        .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
        .logout().logoutUrl(adminContextPath + "/logout").and()
        .httpBasic().and()
        .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        .ignoringAntMatchers(
            "/instances",
            "/actuator/**"
        );
  }
}
