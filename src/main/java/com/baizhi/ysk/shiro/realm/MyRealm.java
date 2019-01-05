package com.baizhi.ysk.shiro.realm;

import com.baizhi.ysk.entity.Admin;
import com.baizhi.ysk.entity.Limits;
import com.baizhi.ysk.entity.Role;
import com.baizhi.ysk.mapper.AdminMapper;
import lombok.extern.log4j.Log4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Log4j
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private AdminMapper adminMapper;

    @Override     //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        Admin a = new Admin();
        a.setName(principal);
        Admin admin = adminMapper.selectOne(a);
        AuthenticationInfo authenticationInfo = null;
        if (admin != null) {
            authenticationInfo = new SimpleAuthenticationInfo(admin.getName(), admin.getPassword(), ByteSource.Util.bytes(admin.getSalt()), this.getName());
        }
        return authenticationInfo;
    }

    @Override    //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("授权");
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        List<Role> roles = adminMapper.queryRolesByName(primaryPrincipal);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (roles.size() != 0) {
            for (Role role : roles) {
                authorizationInfo.addRole(role.getName());
                List<Limits> limits = adminMapper.queryLimitsById(role.getId());
                if (limits.size() != 0) {
                    for (Limits limit : limits) {
                        authorizationInfo.addStringPermission(limit.getName());
                    }
                }
            }
        }
        return authorizationInfo;
    }
}
