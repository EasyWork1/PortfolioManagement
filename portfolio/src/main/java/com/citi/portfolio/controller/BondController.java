package com.citi.portfolio.controller;



import com.citi.portfolio.service.serviceInterface.BondService;
import com.citi.portfolio.service.serviceInterface.StockService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BondController {

    @Autowired
    BondService bondService;
    private static Logger logger = Logger.getLogger(BondController.class);


    @RequestMapping("/selectAllBonds")
    public String selectAll() {

        String json= bondService.selectAllBonds().toJSONString();
        logger.info("select all bonds: "+ json);
        return json;

    }



}