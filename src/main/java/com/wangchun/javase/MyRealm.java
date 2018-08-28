package com.wangchun.javase;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created by Administrator on 2018/8/28.
 */
public class MyRealm implements Realm{
    @Override
    public String getName() {
        return "myRealm1";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        //限制只支持UsernamePasswordToken这个token
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //拿到Token中的用户名
        String username = (String) authenticationToken.getPrincipal();
        //拿到Token中的密码,因为密码存储的是数组类型的,所以我们必须如下强转
        String password = new String((char[]) authenticationToken.getCredentials());
        if(!"test".equals(username)){
            throw new UnknownAccountException();
        }
        if(!"123456".equals(password)){
            throw new IncorrectCredentialsException();
        }

        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
