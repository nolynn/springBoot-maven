package com.lynn.utils.realm;

import com.lynn.web.entities.User;
import com.lynn.web.service.MenuService;
import com.lynn.web.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @Description:
 * @Date: 2019/4/4 16:29
 * @Auther: lynn
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    /**
     * 授权
     *
     * @param token
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {

        User user = (User) token.getPrimaryPrincipal();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> menuRoleList;

        if (user.getId().equals("1")) {
            menuRoleList = menuService.getMenuByAdmin();
        } else {
            menuRoleList = menuService.getMenuByUId(user.getId());
        }
        info.setStringPermissions(menuRoleList);
        return info;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getCredentials();
        String password = new String((char[]) token.getCredentials()); //得到密码
        User user = userService.findUserByUserName(username);

        if (StringUtils.isBlank(username)) {
//            String passwords = Md5.encodeBase64(user);
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, "123123", getName());
            return info;
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }
}
