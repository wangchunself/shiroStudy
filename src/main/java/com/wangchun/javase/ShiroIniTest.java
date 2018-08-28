package com.wangchun.javase;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * Created by Administrator on 2018/8/28.
 */
public class ShiroIniTest {
    public static void main(String[] args) {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        //指定身份验证的策略
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        //至少有一个匹配的策略
        authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        securityManager.setAuthenticator(authenticator);

        //指定授权
        ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
        //用于解析对应的字符串到授权中
        authorizer.setPermissionResolver(new WildcardPermissionResolver());
        securityManager.setAuthorizer(authorizer);

        SecurityUtils.setSecurityManager(securityManager);
        securityManager.setRealm(new MyRealm());

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("111", "123456");
        try {
            subject.login(token);
            System.out.println("用户验证通过");
        }catch (AuthenticationException e){
            //e.printStackTrace();
            System.out.println("用户名或密码输入错误，登录失败！");
        }

    }
}
