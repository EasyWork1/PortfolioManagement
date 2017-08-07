package com.citi.portfolio.controller;



import com.citi.portfolio.entity.FundManager;
import com.citi.portfolio.service.serviceInterface.FundManagerService;
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

    @RequestMapping("/index")
    public ModelAndView index() {

        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;

    }
    @RequestMapping("/register")
    public ModelAndView register(@RequestParam(value = "username", required = true) String username,
                         @RequestParam(value = "password", required = true) String password,
                         @RequestParam(value = "firstname", required = true) String firstname,
                         @RequestParam(value = "lastName", required = true)String lastName,
                         @RequestParam(value = "telephone", required = true) String telephone,
                         @RequestParam(value = "email", required = true) String email) {
        HashMap resultMap = fundManagerService.register(username,password,firstname,lastName,telephone,email);
        boolean success = (boolean)resultMap.get("result");
        if (success){
            ModelAndView modelAndView = new ModelAndView("show");
            modelAndView.addObject("resultMap",resultMap);
            return modelAndView;
        }
        return  new ModelAndView("error");
    }

    @RequestMapping("/login")
    public ModelAndView login(@RequestParam(value = "username", required = true) String username,
                              @RequestParam(value = "password",required = true) String password) {

            HashMap resultMap = fundManagerService.login(username,password);
            ModelAndView modelAndView = new ModelAndView("show");
            modelAndView.addObject("resultMap",resultMap);
            return modelAndView;

    }
    @RequestMapping("/selectAll")
    public ModelAndView selectAll() {

            HashMap resultMap = fundManagerService.selectAll();
            ModelAndView modelAndView = new ModelAndView("show");
            modelAndView.addObject("resultMap",resultMap);
            return modelAndView;

    }

    @RequestMapping("/deleteFundManager")
    public ModelAndView deleteFundManager(@RequestParam(value = "id", required = true) int id) {

        HashMap resultMap = fundManagerService.deleteFundManager(id);
        ModelAndView modelAndView = new ModelAndView("show");
        modelAndView.addObject("resultMap",resultMap);
        return modelAndView;

    }

    @RequestMapping("/updateFundManager")
    public ModelAndView updateFundManager(@RequestParam(value = "firstName", required = true) String firstName,
                                          @RequestParam(value = "lastName", required = true)String lastName,
                                          @RequestParam(value = "telephone", required = true) String telephone,
                                          @RequestParam(value = "email", required = true) String email,
                                          @RequestParam(value = "password", required = true) String password) {

        HashMap resultMap = fundManagerService.updateFundManager(firstName, lastName, telephone, email, password);
        ModelAndView modelAndView = new ModelAndView("show");
        modelAndView.addObject("resultMap",resultMap);
        return modelAndView;

    }


}