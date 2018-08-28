package com.wangchun.javaweb.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2018/8/28.
 */
/*
    如果我们使用注解形式添加访问权限的时候,如果访问者没有权限,那么这个时候,就会如下错误,
    我们需要定义通知将此异常进行配置
    */
@ControllerAdvice
public class AuthExceptionException {
    //抓取这个异常
    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ModelAndView processUnauthenticatedException(NativeWebRequest request,UnauthorizedException e){
        //返回到error页面,携带Exception异常
        return new ModelAndView("error","exception",e);
    }
}
