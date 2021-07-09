//package com.rhb.gateway.config;
//
//import cn.hutool.json.JSONUtil;
//import com.alibaba.nacos.api.NacosFactory;
//import com.alibaba.nacos.api.PropertyKeyConst;
//import com.alibaba.nacos.api.config.ConfigService;
//import com.alibaba.nacos.api.config.listener.Listener;
//import io.netty.util.internal.StringUtil;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//import java.util.concurrent.Executor;
//import javax.annotation.PostConstruct;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
//import org.springframework.cloud.gateway.route.RouteDefinition;
//import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.context.ApplicationEventPublisherAware;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
///**
// * Gateway 适配 nacos 实现动态的路由配置
// *
// * @author renhuibo
// * @date 2021/7/8 15:22
// */
//@Slf4j
//@Component
//@RefreshScope
//public class DynamicRouteConfig implements ApplicationEventPublisherAware {
//
//  @Value("${spring.cloud.nacos.config.dataId}")
//  private String dataId;
//  @Value("${spring.cloud.nacos.config.group}")
//  private String group;
//  @Value("${spring.cloud.nacos.config.server-addr}")
//  private String serverAddr;
//  @Value("${spring.cloud.nacos.config.username}")
//  private String username;
//  @Value("${spring.cloud.nacos.config.password}")
//  private String password;
//
//  private ApplicationEventPublisher applicationEventPublisher;
//
//  private static final List<String> ROUTE_LIST = new ArrayList<>();
//
//  @Autowired
//  private RouteDefinitionWriter routeDefinitionWriter;
//
//  @PostConstruct
//  public void dynamicRouteByNacosListener(){
//    try{
//      /**
//       * 可以查看
//       * - 官网-授权：https://nacos.io/zh-cn/docs/auth.html
//       * - 官网-SDK：https://nacos.io/zh-cn/docs/sdk.html
//       */
//      Properties properties = new Properties();
//      properties.setProperty(PropertyKeyConst.SERVER_ADDR,serverAddr);
//      properties.put("username",username);
//      properties.put("password",password);
//      ConfigService configService = NacosFactory.createConfigService(properties);
//      configService.getConfig(dataId,group,5000);
//      configService.addListener(dataId, group, new Listener() {
//        @Override
//        public Executor getExecutor() {
//          return null;
//        }
//        @Override
//        public void receiveConfigInfo(String configInfo) {
//          clearRoutes();
//          try{
//            if(StringUtil.isNullOrEmpty(configInfo)){
//              return;
//            }
//            List<RouteDefinition> gatewayRouteDefinitions = JSONUtil.toList(configInfo,RouteDefinition.class);
//            for(RouteDefinition routeDefinition : gatewayRouteDefinitions){
//              addRoute(routeDefinition);
//            }
//            publish();
//          }catch (Exception e){
//            log.error("receiveConfigInfo error: {}",e);
//          }
//        }
//      });
//    }catch (Exception e){
//      log.error("dynamicRouteByNacosListener error: {}",e);
//    }
//  }
//
//  private void clearRoutes(){
//    for(String id:ROUTE_LIST){
//      this.routeDefinitionWriter.delete(Mono.just(id)).subscribe();
//    }
//    ROUTE_LIST.clear();
//  }
//
//  private void addRoute(RouteDefinition definition){
//    try{
//      routeDefinitionWriter.save(Mono.just(definition));
//      ROUTE_LIST.add(definition.getId());
//    }catch (Exception e){
//      log.error("add route error: {}",e);
//    }
//  }
//
//  public void publish(){
//    this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this.routeDefinitionWriter));
//  }
//
//  @Override
//  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
//    this.applicationEventPublisher = applicationEventPublisher;
//  }
//}
