package com.citi.portfolio.service.serviceImp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.citi.portfolio.dao.BondMapper;
import com.citi.portfolio.dao.StockMapper;
import com.citi.portfolio.entity.DTO.BondDTO;
import com.citi.portfolio.entity.DTO.StockDTO;
import com.citi.portfolio.service.serviceInterface.BondService;
import com.citi.portfolio.service.serviceInterface.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class BondServiceImp implements BondService {

    @Autowired
    BondMapper bondMapper;

    @Override
    public JSONArray selectAllBonds() {
        JSONArray json = new JSONArray();
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        ArrayList<BondDTO> bonds = bondMapper.selectBondDTO(sqlDate);
        json = (JSONArray) JSONObject.toJSON(bonds);
        return json;
    }
}
