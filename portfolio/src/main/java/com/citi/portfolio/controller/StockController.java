package com.citi.portfolio.controller;



import com.citi.portfolio.service.serviceInterface.StockService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class StockController {

    @Autowired
    StockService stockService;
    private static Logger logger = Logger.getLogger(StockController.class);

    @RequestMapping("/stock.html")
    public ModelAndView registerForm() {

        ModelAndView modelAndView = new ModelAndView("stock");
        return modelAndView;

    }
    @RequestMapping("/stockPage.html")
    public ModelAndView stockPage() {

        ModelAndView modelAndView = new ModelAndView("stockPage");
        return modelAndView;

    }

    @RequestMapping("/selectAllStocks")
    public String selectAll() {

        String json= stockService.selectAllStocks().toJSONString();
        logger.info("select all stocks: "+ json);
        return json;

    }



}