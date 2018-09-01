package com.jqh.SpringShiroDemo;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * 自定义密码匹配规则
 */
public class CredentialMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
        String password = new String(usernamePasswordToken.getPassword()) ;// 获取token密码
        String dbPassword = (String) info.getCredentials();  // 获取数据库的密码
        return this.equals(password,dbPassword);
    }
}
