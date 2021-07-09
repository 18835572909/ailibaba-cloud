//package com.rhb.gateway.config;
//
//import java.nio.charset.StandardCharsets;
//import java.util.Map;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.RequestRateLimiterGatewayFilterFactory;
//import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
//import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
//import org.springframework.cloud.gateway.route.Route;
//import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import reactor.core.publisher.Mono;
//
///**
// * 自定义限流工程类
// *
// * @author renhuibo
// * @date 2021/7/9 17:47
// */
//@Slf4j
//public class DefinedRequestRateLimiterGatewayFilterFactory extends
//    RequestRateLimiterGatewayFilterFactory  {
//
//  private final RateLimiter defaultRateLimiter;
//  private final KeyResolver defaultKeyResolver;
//
//  public DefinedRequestRateLimiterGatewayFilterFactory(
//      RateLimiter defaultRateLimiter,
//      KeyResolver defaultKeyResolver) {
//    super(defaultRateLimiter, defaultKeyResolver);
//    this.defaultRateLimiter = defaultRateLimiter;
//    this.defaultKeyResolver = defaultKeyResolver;
//  }
//
//  @Override
//  public GatewayFilter apply(Config config) {
//    KeyResolver resolver = getOrDefault(config.getKeyResolver(), defaultKeyResolver);
//    RateLimiter<Object> limiter = getOrDefault(config.getRateLimiter(), defaultRateLimiter);
//    return (exchange, chain) -> resolver.resolve(exchange).flatMap(key -> {
////            if (EMPTY_KEY.equals(key)) {
////                if (denyEmpty) {
////                    setResponseStatus(exchange, emptyKeyStatus);
////                    return exchange.getResponse().setComplete();
////                }
////                return chain.filter(exchange);
////            }
//      String routeId = config.getRouteId();
//      if (routeId == null) {
//        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
//        routeId = route.getId();
//      }
//
//      String finalRouteId = routeId;
//      return limiter.isAllowed(routeId, key).flatMap(response -> {
//
//        for (Map.Entry<String, String> header : response.getHeaders().entrySet()) {
//          exchange.getResponse().getHeaders().add(header.getKey(), header.getValue());
//        }
//
//        if (response.isAllowed()) {
//          return chain.filter(exchange);
//        }
//
//        log.warn("已限流: {}", finalRouteId);
//        ServerHttpResponse httpResponse = exchange.getResponse();
//        httpResponse.setStatusCode(config.getStatusCode());
//        if (!httpResponse.getHeaders().containsKey("Content-Type")) {
//          httpResponse.getHeaders().add("Content-Type", "application/json");
//        }
//        DataBuffer buffer = httpResponse.bufferFactory().wrap("{'msg':'访问已受限制，请稍候重试'}".getBytes(
//            StandardCharsets.UTF_8));
//        return httpResponse.writeWith(Mono.just(buffer));
//
//        // return exchange.getResponse().setComplete();
//      });
//    });
//  }
//}
