package com.citi.portfolio.service.serviceImp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.citi.portfolio.dao.BondMapper;
import com.citi.portfolio.dao.FutureMapper;
import com.citi.portfolio.entity.DTO.BondDTO;
import com.citi.portfolio.entity.DTO.FutureDTO;
import com.citi.portfolio.service.serviceInterface.BondService;
import com.citi.portfolio.service.serviceInterface.FutureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class FutureServiceImp implements FutureService {
    @Autowired
    FutureMapper futureMapper;
    @Override
    public JSONArray selectAllFutures() {
        JSONArray json = new JSONArray();
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        ArrayList<FutureDTO> bonds = futureMapper.selectFutureDTO(sqlDate);
        json = (JSONArray) JSONObject.toJSON(bonds);
        return json;
    }




}
