package com.lynn.web.utils;

import com.lynn.web.entities.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @Description:
 * @Date: 2019/4/15 16:57
 * @Auther: lynn
 */
public class ShiroSessionUtils {

    private static final String LOGIN_USER = "login_user";


    public static Session getSession() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null) {
                session = subject.getSession();
            }
            if (session != null) {
                return session;
            }
        } catch (InvalidSessionException e) {

        }
        return null;
    }

    /**
     * 获取session中的Attribute
     *
     * @param name
     * @return
     */
    public static Object getSessionAttribute(String name) {
        Session session = getSession();
        return session == null ? null : session.getAttribute(name);
    }

    /**
     * 设置session的Attribute
     *
     * @param name
     * @param value
     */
    public static void setSessionAttribute(String name, Object value) {
        Session session = getSession();
        if (session != null) {
            session.setAttribute(name, value);
        }
    }


    /**
     * 删除session中的Attribute
     *
     * @param name
     */
    public static void removeSessionAttribute(String name) {
        getSession().removeAttribute(name);
    }


    /**
     * 将sessionUser 添加到 session
     */

    public static void addUserToSession(User user) {

        setSessionAttribute(LOGIN_USER, user);

    }

    /**
     * 将 sessionUser  移除
     *
     * @param
     */
    public static void removeSessionUser() {
        getSession().removeAttribute("LOGIN_USER");
    }
}
