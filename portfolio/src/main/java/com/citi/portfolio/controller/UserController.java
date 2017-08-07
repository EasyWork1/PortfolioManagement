package com.citi.portfolio.controller;



import com.citi.portfolio.service.serviceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/register")
    public ModelAndView register(@RequestParam(value = "username", required = true) String username,
                         @RequestParam(value = "password", required = true) String password) {
        boolean success = userService.insertUser(username,password);
        if (success){
            ModelAndView modelAndView = new ModelAndView("show");
            modelAndView.addObject("username",username);
            return modelAndView;
        }
        return null;
    }

    @RequestMapping("/query")
    public String query(@RequestParam(value = "id", required = true) Integer id) {
        return  "failed";
    }

    private String creatMD5(String loginNum){
        // 生成一个MD5加密计算摘要
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(loginNum.getBytes());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new BigInteger(1, md.digest()).toString(16);
    }
}