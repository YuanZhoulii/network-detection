//package com.blaze.network.user.config;
//
//
//import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
//import com.alibaba.csp.sentinel.slots.block.BlockException;
//import com.alibaba.fastjson.JSON;
//import com.blaze.network.common.exception.ErrorCodeEnum;
//import com.blaze.network.common.utils.R;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
///**
// * 自定义sentinel异常返回信息
// */
//@Component
//public class NetworkSentinelConfig implements BlockExceptionHandler{
//
//    @Override
//    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException ex) throws Exception {
//        R error = R.error(ErrorCodeEnum.TOO_MANY_REQUEST.getCode(), ErrorCodeEnum.TOO_MANY_REQUEST.getMsg());
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("application/json");
//        response.getWriter().write(JSON.toJSONString(error));
//    }
//}
//
//
//
