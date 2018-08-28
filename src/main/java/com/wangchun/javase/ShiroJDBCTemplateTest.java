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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Created by Administrator on 2018/8/28.
 */
public class ShiroJDBCTemplateTest {
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


        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/shiro");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        MyRealm2 myRealm2 = new MyRealm2();
        myRealm2.setJdbcTemplate(jdbcTemplate);

        SecurityUtils.setSecurityManager(securityManager);
        securityManager.setRealm(myRealm2);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("admin@shiro.com", "admin");
        try {
            subject.login(token);
            System.out.println("用户验证通过");
        }catch (AuthenticationException e){
            //e.printStackTrace();
            System.out.println("用户名或密码输入错误，登录失败！");
        }

    }
}
