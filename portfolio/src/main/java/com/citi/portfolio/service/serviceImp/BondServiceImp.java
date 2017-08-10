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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class BondServiceImp implements BondService {

    @Autowired
    BondMapper bondMapper;

    @Override
    public JSONObject selectAllBonds() {
        JSONObject json = new JSONObject();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date sqlDate = null;
        try {
            sqlDate = new java.sql.Date(simpleDateFormat.parse("2017-03-30").getTime());
            ArrayList<BondDTO> bonds = bondMapper.selectBondDTO(sqlDate);
            JSONArray BondsJson = (JSONArray) JSONObject.toJSON(bonds);
            json.put("total",bonds.size());
            json.put("data",BondsJson);
        } catch (ParseException e) {
        e.printStackTrace();
    }
        return json;
    }
}
