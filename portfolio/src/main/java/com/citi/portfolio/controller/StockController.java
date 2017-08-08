package com.citi.portfolio.controller;



import com.citi.portfolio.service.serviceInterface.StockService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

    @Autowired
    StockService stockService;
    private static Logger logger = Logger.getLogger(StockController.class);


    @RequestMapping("/selectAllStocks")
    public String selectAll() {

            String json= stockService.selectAllStocks().toJSONString();
            return json;

    }



}