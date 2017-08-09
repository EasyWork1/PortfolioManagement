package com.citi.portfolio.controller;



import com.citi.portfolio.service.serviceInterface.FutureService;
import com.citi.portfolio.service.serviceInterface.StockService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class FutureController {

    @Autowired
    FutureService futureService;
    private static Logger logger = Logger.getLogger(FutureController.class);


    @RequestMapping("/Future.html")
    public ModelAndView registerForm() {

        ModelAndView modelAndView = new ModelAndView("future");
        return modelAndView;

    }
    @RequestMapping("/selectAllFutures")
    public String selectAll() {

        String json= futureService.selectAllFutures().toJSONString();
        logger.info("select all futures: "+ json);
        return json;

    }



}