package com.citi.portfolio.controller;



import com.citi.portfolio.dao.PositionMapper;
import com.citi.portfolio.service.serviceInterface.PositionService;
import com.citi.portfolio.service.serviceInterface.StockService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PositionController {

    @Autowired
    PositionService positionService;
    private static Logger logger = Logger.getLogger(PositionController.class);


    @RequestMapping("/fundSub.html")
    public ModelAndView positionForm() {

        ModelAndView modelAndView = new ModelAndView("fundSub");
        return modelAndView;

    }

    @RequestMapping("/selectAllPositions")
    public String selectAll(@RequestParam(value = "portfolioid", required = true)Integer portfolioId) {
            String json= positionService.selectAllPosition(portfolioId).toJSONString();
            return json;
    }

    @RequestMapping("/deletePosition")
    public String deletePosition(@RequestParam(value = "id", required = true) Integer id) {
        String json = positionService.deletePosition(id).toJSONString();
        return json;
    }
    @RequestMapping("/searchSecurity")
    public String insertPosition(@RequestParam(value = "asset", required = true)String asset,
                                 @RequestParam(value = "querysymbol", required = true)String querysymbol){
        String json  = positionService.selectSymbol(asset,querysymbol).toJSONString();
        return  json;

    }

    @RequestMapping("/insertPosition")
    public String insertPosition(@RequestParam(value = "securityid", required = true)String securityid,
                                 @RequestParam(value = "asset", required = true)String asset,
                                 @RequestParam(value = "portfolioid", required = true)Integer portfolioid,
                                 @RequestParam(value = "quantity", required = true)Double quantity){
        String json  = positionService.insertPosition(securityid,asset,portfolioid,quantity).toJSONString();
        logger.info("insert position "+ json);
        return  json;

    }



}