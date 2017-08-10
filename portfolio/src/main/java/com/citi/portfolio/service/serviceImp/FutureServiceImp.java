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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class FutureServiceImp implements FutureService {
    @Autowired
    FutureMapper futureMapper;

    @Override
    public JSONObject selectAllFutures() {
        JSONObject json = new JSONObject();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date sqlDate = null;
        try {
            sqlDate = new java.sql.Date(simpleDateFormat.parse("2017-04-03").getTime());
            ArrayList<FutureDTO> futures = futureMapper.selectFutureDTO(sqlDate);
            JSONArray futuresJson = (JSONArray) JSONObject.toJSON(futures);
            json.put("total", futures.size());
            json.put("data", futuresJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return json;


    }
}
