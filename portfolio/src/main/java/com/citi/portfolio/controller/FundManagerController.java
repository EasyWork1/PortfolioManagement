package com.citi.portfolio.controller;



import com.citi.portfolio.entity.FundManager;
import com.alibaba.fastjson.JSONObject;
import com.citi.portfolio.service.serviceInterface.FundManagerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class FundManagerController {

    @Autowired
    FundManagerService fundManagerService;
    private static Logger logger = Logger.getLogger(FundManagerController.class);


    @RequestMapping("/showFundManagerForm")
    public ModelAndView addManagerForm() {

        ModelAndView modelAndView = new ModelAndView("fundManager");
        return modelAndView;

    }
    @RequestMapping("/loginForm")
    public ModelAndView loginForm() {

        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;

    }
    @RequestMapping("/registerForm")
    public ModelAndView registerForm() {

        ModelAndView modelAndView = new ModelAndView("sign");
        return modelAndView;

    }
    @RequestMapping("/register")
    public String register(@RequestParam(value = "username", required = true) String username,
                         @RequestParam(value = "password", required = true) String password,
                         @RequestParam(value = "firstName", required = true) String firstname,
                         @RequestParam(value = "lastName", required = true)String lastName,
                         @RequestParam(value = "telephone", required = true) String telephone,
                         @RequestParam(value = "email", required = true) String email) {
        String json  = fundManagerService.register(username,password,firstname,lastName,telephone,email).toJSONString();
        logger.info("register new fundManager: "+ json);
        return  json;
    }

    @RequestMapping("/login")
    public String login(@RequestParam(value = "username", required = true) String username,
                              @RequestParam(value = "password",required = true) String password) {

        String json  = fundManagerService.login(username,password).toJSONString();
        logger.info("login fundManager: "+ json);
        return  json;

    }
    @RequestMapping("/selectAllFundManagers")
    public String selectAll() {

        String json= fundManagerService.selectAll().toJSONString();
        logger.info("select all fundManager: "+ json);
        return json;


    }

    @RequestMapping("/deleteFundManager")
    public String deleteFundManager(@RequestParam(value = "id", required = true) int id) {

        String json = fundManagerService.deleteFundManager(id).toJSONString();
        logger.info("delete fundManager: "+ json);
        return json;

    }

    @RequestMapping("/updateFundManager")
    public String updateFundManager(@RequestParam(value = "firstName", required = true) String firstName,
                                          @RequestParam(value = "lastName", required = true)String lastName,
                                          @RequestParam(value = "telephone", required = true) String telephone,
                                          @RequestParam(value = "email", required = true) String email,
                                          @RequestParam(value = "password", required = true) String password) {

        String json= fundManagerService.updateFundManager(firstName, lastName, telephone, email, password).toJSONString();
        logger.info("update fundManager: "+ json);
        return json;

    }


}