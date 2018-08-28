package com.wangchun.javase;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * Created by Administrator on 2018/8/22.
 */
public class JDBCShiroTest {
    public static void main(String[] args) {
        //创建SecurityManager对象的工厂
        Factory<SecurityManager> factory= new IniSecurityManagerFactory("classpath:shiro-mysql.ini");
        SecurityManager securityManager = factory.getInstance();
        //将securityManager绑定在上下文上,SecurityUtils是全局对象,只用绑定一次就行
        SecurityUtils.setSecurityManager(securityManager);
        //根据上下文对象,拿到Subject
        //此对象是和系统进行交互的对象
        Subject subject = SecurityUtils.getSubject();
        //用户名密码票据对象
        UsernamePasswordToken token = new UsernamePasswordToken("admin@shiro.com", "admin");
        try {
            subject.login(token);
            //用户是否验证通过,如果没有通过返回false
            if(subject.isAuthenticated()){
                System.out.println("登陆成功！");
                //判断用户有没有此角色
                if(subject.hasRole("admin")){
                    System.out.println("有admin角色");
                }else{
                    System.out.println("没有admin角色");
                }
                //判断用户有没有此权限
                if(subject.isPermitted("search")){
                    System.out.println("有search权限");
                }
            }
        }catch (AuthenticationException e){
            //e.printStackTrace();
            System.out.println("用户名或密码输入错误，登录失败！");
        }finally {
            System.out.println(subject.isAuthenticated());
        }

    }
}
