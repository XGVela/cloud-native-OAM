package org.xgvela.oam.config;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authz.SslFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/** @author KeviN */
@Configuration
public class ShiroConfig {

  @Bean(name = "shiroFilter")
  public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
    ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
    Map<String, Filter> filters = new HashMap<>();
    filters.put("ssl", new SslFilter());
    filters.put("jwt", new JWTFilter());
    shiroFilter.setFilters(filters);
    shiroFilter.setLoginUrl("/sec/login");
    shiroFilter.setSecurityManager(manager);
    shiroFilter.setUnauthorizedUrl("/sec/401");

    LinkedHashMap<String, String> filterRules = new LinkedHashMap<>();
    filterRules.put("/webjars/**", "anon");
    filterRules.put("/swagger**", "anon");
    filterRules.put("/v2/api-docs", "anon");
    filterRules.put("/swagger-resources/configuration/ui", "anon");
    filterRules.put("/swagger-ui/index.html", "anon");
    filterRules.put("/druid/**", "anon");
    filterRules.put("/api/auth/tokens", "anon");
    filterRules.put("/api/rest/securityManagement/*/oauth/token", "anon");
    filterRules.put("/api/auth/register", "anon");
    filterRules.put("/api/auth/401", "anon");
    filterRules.put("/api/mep/*/auth", "anon");
    filterRules.put("/v1/authentication/token", "anon");
    filterRules.put("/app-command/**", "anon");
    filterRules.put("/logs/receiveLogs", "anon");
    filterRules.put("/ws/api/alarm/listener", "anon");
    filterRules.put("/api/logs", "anon");
    filterRules.put("/pm/historyPmData", "anon");
    filterRules.put("/ne/config/notifyDownLoadFiles", "anon");

    filterRules.put("/prometheus", "anon");
    
    filterRules.put("/ws/**","anon");
    filterRules.put("/ms/**","anon");

    filterRules.put("/smoke", "anon");
    filterRules.put("/actuator/health", "anon");
    filterRules.put("/**", "jwt");
    shiroFilter.setFilterChainDefinitionMap(filterRules);
    return shiroFilter;
  }

  @Bean(name = "securityManager")
  public SecurityManager securityManager() {
    DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
    manager.setRealm(jwtRealm());
    // http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
    DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
    DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
    defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
    subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
    manager.setSubjectDAO(subjectDAO);

    return manager;
  }

  @Bean(name = "jwtRealm")
  public JwtRealm jwtRealm() {
    return new JwtRealm();
  }

  @Bean
  public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    return new LifecycleBeanPostProcessor();
  }

  @Bean
  public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
    DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
    creator.setProxyTargetClass(true);
    return creator;
  }

  @Bean
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
    AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
    advisor.setSecurityManager(manager);
    return advisor;
  }

  public static void main(String[] args) {
    System.out.println();
  }
}
