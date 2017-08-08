package com.citi.portfolio.controller;



import com.citi.portfolio.service.serviceInterface.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    @Autowired
    PortfolioService portfolioService;
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FundManagerController.class);


    @RequestMapping("/insertPortfolio")
    public String insertPortfolio(@RequestParam(value = "name", required = true) String name,
                                        @RequestParam(value = "fundManagerId", required = true) Integer fundManagerId) {
        String json  = portfolioService.insertPortfolio(name,fundManagerId).toJSONString();
        logger.info("insert portfolio "+ json);
        return  json;


    }
    @RequestMapping("/myportfolio")
    public String myPortfolio(@RequestParam(value = "fundManagerId", required = true) Integer fundManagerId) {

        String json  = portfolioService.findPortfolioByFundManagerId(fundManagerId).toJSONString();
        logger.info("show my portfolio "+ json);
        return  json;

    }


}