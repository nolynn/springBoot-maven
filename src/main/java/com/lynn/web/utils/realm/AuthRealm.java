package com.lynn.web.utils.realm;

import com.lynn.web.entities.User;
import com.lynn.web.service.MenuService;
import com.lynn.web.service.UserService;
import com.lynn.web.utils.PasswordUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
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
        String pwd = PasswordUtils.encodeBase64(password);
        User user = userService.findUserByUserName(username);

        if (null == user) {
            throw new UnknownAccountException();
        }
        if (!StringUtils.equals(user.getPassword(), pwd)) {
            throw new IncorrectCredentialsException();
        }
        if (user.getEnable() == 1) {
            throw new DisabledAccountException();
        }
        return new SimpleAuthenticationInfo(user, pwd, getName());
    }

    public static void main(String[] args) {
        System.out.println(PasswordUtils.encodeBase64("123123"));
    }
}
