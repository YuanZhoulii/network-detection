package com.blaze.network.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.fastjson.JSON;
import com.blaze.network.common.exception.ErrorCodeEnum;
import com.blaze.network.common.utils.R;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author yuanzhouli
 * @date 2021/2/23 - 22:24
 */
@Configuration
public class SentinelGatewayConfig {
    public SentinelGatewayConfig(){
        GatewayCallbackManager.setBlockHandler(new BlockRequestHandler() {
            //网关限流了请求，就会调用此回调
            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable throwable) {
                R error = R.error(ErrorCodeEnum.TOO_MANY_REQUEST.getCode(), ErrorCodeEnum.TOO_MANY_REQUEST.getMsg());
                String errJson = JSON.toJSONString(error);
//                Mono<String> aaa = Mono.just("aaa");
                Mono<ServerResponse> body = ServerResponse.ok().body(Mono.just(errJson), String.class);
                return body;
            }
        });
    }
}
