package com.citi.portfolio.controller;



import com.citi.portfolio.entity.Portfolio;
import com.citi.portfolio.service.serviceInterface.FundManagerService;
import com.citi.portfolio.service.serviceInterface.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {
//    private static final Logger LOGGER = getLogger(PortfolioController.class.getName());

    @Autowired
    PortfolioService portfolioService;

    @RequestMapping("/myportfolio")
    public ModelAndView myPortfolio(@RequestParam(value = "fundManagerId", required = true) Integer fundManagerId) {

        HashMap resultMap = portfolioService.findPortfolioByFundManagerId(fundManagerId);
        ModelAndView modelAndView = new ModelAndView("myportfolios");
        modelAndView.addObject("resultMap",resultMap);
        return modelAndView;

    }


}