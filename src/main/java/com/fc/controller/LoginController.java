package com.fc.controller;


import com.fc.dao.PjclassMapper;
import com.fc.entity.Pjclass;
import com.fc.entity.Users;
import com.fc.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
                errorInfo = "?????????????????????";
                request.getSession().setAttribute("errorInfo",errorInfo);
            }

        }
        return "redirect:/login";
    }
    @GetMapping("/register")
    private  String register(Model model){
//        Iterable<Pjclass> list = PjclassMapper.findAll();
//        model.addAttribute("list",list);
        return "/student/register";
    }
    @PostMapping("/CanRegister")
    private  String CanRegister(Integer roleid,String username,String userpwd,String truename ,Integer classid ,HttpServletRequest request){
        System.out.println(roleid+":"+username+":"+userpwd+":"+truename+":"+classid);
        String wk = "";
        String cz = "";
        String cg = "";
        Users users = new Users();
        users.setRoleid(roleid);
        users.setUsername(username);
        users.setUserpwd(userpwd);
        users.setTruename(truename);
        users.setClassid(classid);
        Users byUsername = loginService.findByUsername(username);
        System.out.println(byUsername);
        if (byUsername==null){
            if(userpwd.equals("")){
                wk = "??????????????????????????????";
                request.getSession().setAttribute("wk",wk);

            }else {
                if (truename.equals("")){
                    wk = "??????????????????????????????";
                    request.getSession().setAttribute("wk",wk);
                }
                else {
                    loginService.save(users);

                    cg = "???????????????????????????";
                    request.getSession().setAttribute("cg",cg);
                }
            }
        }else {
            System.out.println("cz");
            cz = "????????????????????????";
            request.getSession().setAttribute("cz",cz);
            return "redirect:/register";
        }
        return "redirect:/register";
    }

}
