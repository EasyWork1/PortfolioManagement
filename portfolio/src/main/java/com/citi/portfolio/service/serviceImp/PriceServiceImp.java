package com.citi.portfolio.service.serviceImp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.citi.portfolio.dao.BondMapper;
import com.citi.portfolio.dao.PriceMapper;
import com.citi.portfolio.entity.DTO.BondDTO;
import com.citi.portfolio.service.serviceInterface.BondService;
import com.citi.portfolio.service.serviceInterface.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@Service
public class PriceServiceImp implements PriceService {
    @Autowired
    PriceMapper priceMapper;

    @Override
    public Double getOfferPriceBySymbol(String symbol, Date date) {
        HashMap hashMap = new HashMap();
        hashMap.put("symbol", symbol);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date sqlDate = null;
        try {
            sqlDate = new java.sql.Date(simpleDateFormat.parse("2017-03-30").getTime());
            hashMap.put("date", sqlDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return priceMapper.selectBySymbolAndDate(hashMap).getOfferprice();

    }
}
