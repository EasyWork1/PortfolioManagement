package com.citi.portfolio.controller;



import com.citi.portfolio.service.serviceInterface.PositionHistoryService;
import com.citi.portfolio.service.serviceInterface.StockService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PositionHistoryController {

    @Autowired
    PositionHistoryService positionHistoryService;
    private static Logger logger = Logger.getLogger(PositionHistoryController.class);

    @RequestMapping("/history.html")
    public ModelAndView historyForm() {

        ModelAndView modelAndView = new ModelAndView("history");
        return modelAndView;

    }

    @RequestMapping("/selectAllHistory")
    public String selectAll(@RequestParam(value = "fundmanagerid", required = true)Integer fundmanagerid) {

        String json= positionHistoryService.selectAllPosition(fundmanagerid).toJSONString();
        logger.info("select all Position History: "+ json);
        return json;

    }



}