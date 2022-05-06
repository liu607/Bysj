package com.fc.controller;


import com.fc.entity.Users;
import com.fc.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    private String login(){
        return "/student/login";
    }
    @PostMapping("/CanLogin")
    private String CanLogin(String username, String userpwd, HttpServletRequest request){
        Iterable<Users> users = loginService.findAll();
        String errorInfo = "";
        for (Users lis : users){
            if(lis.getUsername().equals(username) && lis.getUserpwd().equals(userpwd)){
                if (lis.getRoleid().equals(1)){
                    request.getSession().setAttribute("Tlis",lis);
                    Integer id = lis.getClassid();
                    request.getSession().setAttribute("Tclassid",id);
                    return "redirect:/TeacherManage";
                }else if (lis.getRoleid().equals(2)){
                    request.getSession().setAttribute("lis",lis);
                    Integer usid = lis.getUserid();
                    Integer id = lis.getClassid();
                    request.getSession().setAttribute("classid",id);
                    request.getSession().setAttribute("userid",usid);
                    return "redirect:/StuMan";
                }else if (lis.getRoleid().equals(3)){
                    request.getSession().setAttribute("Alis",lis);
                    request.getSession().setMaxInactiveInterval(30 * 60);
                    return "redirect:/AdminManage";
                }
            }else {
                errorInfo = "账号或密码错误";
                request.getSession().setAttribute("errorInfo",errorInfo);
            }

        }
        return "redirect:/login";
    }

}
