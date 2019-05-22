package org.auth.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.auth.server.entity.GdUser;
import org.auth.server.mapper.GdUserMapper;
import org.fresh.gd.commons.consts.pojo.ResponseData;
import org.fresh.gd.commons.consts.pojo.dto.user.RoleAndUserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @DATA 2019-04-12 15:20
 * @Author 张国伟  WeChat:17630376104
 * @Description
 */
@Slf4j
@Controller
public class LoginController {
    @Autowired
    GdUserMapper gdUserMapper;

    @GetMapping("/login")
    public String login() {
        return "base-login";
    }


    @RequestMapping("/exitUser")
    public void exitUser(HttpServletResponse response, HttpServletRequest request) {
        request.getSession().invalidate();
        response.addCookie(new Cookie("JSESSIONID", ""));
        response.addCookie(new Cookie("OAUTH2SESSION", ""));
        response.addCookie(new Cookie("XSRF-TOKEN", ""));

    }


    @RequestMapping("/reUrl")
    public String reUrl() {
        return "redirect:http://127.0.0.1:8777";
    }

}
