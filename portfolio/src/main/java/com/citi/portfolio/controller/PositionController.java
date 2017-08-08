package com.citi.portfolio.controller;



import com.citi.portfolio.dao.PositionMapper;
import com.citi.portfolio.service.serviceInterface.PositionService;
import com.citi.portfolio.service.serviceInterface.StockService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PositionController {

    @Autowired
    PositionService positionService;
    private static Logger logger = Logger.getLogger(PositionController.class);


    @RequestMapping("/selectAllStocks")
    public String selectAll() {

            String json= stockService.selectAllStocks().toJSONString();
            return json;

    }

//    @RequestMapping("/deleteStock")
//    public String deleteStock(@RequestParam(value = "id", required = true) String id) {
//
//        String json = stockService.deleteStock(id).toJSONString();
//        return json;
//
//    }
//
//    @RequestMapping("/updateStock")
//    public String updateStock(@RequestParam(value = "firstName", required = true) String firstName,
//                                          @RequestParam(value = "lastName", required = true)String lastName,
//                                          @RequestParam(value = "telephone", required = true) String telephone,
//                                          @RequestParam(value = "email", required = true) String email,
//                                          @RequestParam(value = "password", required = true) String password) {
//
//        String json= stockService.updateStock(firstName, lastName, telephone, email, password).toJSONString();
//        return json;
//
//    }


}