package com.citi.portfolio.service.serviceImp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.citi.portfolio.dao.FundManagerMapper;
import com.citi.portfolio.dao.PortfolioMapper;
import com.citi.portfolio.dao.PositionMapper;
import com.citi.portfolio.dao.PriceMapper;
import com.citi.portfolio.entity.FundManager;
import com.citi.portfolio.entity.Portfolio;
import com.citi.portfolio.entity.Position;
import com.citi.portfolio.entity.Price;
import com.citi.portfolio.service.serviceInterface.FundManagerService;
import com.citi.portfolio.util.FundManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class FundManagerServiceImp implements FundManagerService {

    @Autowired
    FundManagerMapper fundManagerMapper;
    @Autowired
    PortfolioMapper portfolioMapper;
    @Autowired
    PositionMapper positionMapper;
    @Autowired
    PriceMapper priceMapper;

    @Override
    public JSONObject register(String username, String password, String firstName, String lastName, String telephone, String email) {
        JSONObject jsonObject = new JSONObject();
        FundManager fundManager = new FundManager();
        fundManager.setUsername(username);
        fundManager.setPassword(password);
        fundManager.setFirstname(firstName);
        fundManager.setLastname(lastName);
        fundManager.setTelephone(telephone);
        fundManager.setEmail(email);
        fundManager.setBalance(0D);

        try {
            fundManager.setPassword(FundManagerUtil.EncoderByMd5(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        jsonObject.put("resultCode", 0);
        ArrayList<FundManager> fundManagers = fundManagerMapper.selectByUserName(username);
        if (fundManagers.isEmpty()) {
            int result = fundManagerMapper.insert(fundManager);
            if (result != 0) {

                fundManagers = fundManagerMapper.selectByUserName(username);
                if (!fundManagers.isEmpty()) {
                    fundManager = fundManagers.get(0);
                    jsonObject = (JSONObject) JSONObject.toJSON(fundManager);
                    jsonObject.put("resultCode", 1);
                } else {
                    jsonObject.put("errorMessage", "can't find FundManager in DataBase.");
                }

            } else {
                jsonObject.put("errorMessage", "insert failed.");
            }

        } else {
            jsonObject.put("errorMessage", "username has exsit.");
        }

        return jsonObject;
    }

    @Override
    public JSONObject login(String username, String password) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("resultCode", 0);
        ArrayList<FundManager> fundManagers = fundManagerMapper.selectByUserName(username);
        if (!fundManagers.isEmpty()) {
            FundManager fundManager = fundManagers.get(0);
            try {
                if (fundManager.getPassword().equals(FundManagerUtil.EncoderByMd5(password))) {
                    jsonObject = (JSONObject) JSONObject.toJSON(fundManager);
                    jsonObject.put("resultCode", 1);
                } else {
                    jsonObject.put("errorMessage", "password error.");
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                jsonObject.put("errorMessage", "NoSuchAlgorithmException");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                jsonObject.put("errorMessage", "UnsupportedEncodingException");
            }

        }

        return jsonObject;
    }

    @Override
    public JSONArray selectAll() {
        JSONArray jsonObject = new JSONArray();
        ArrayList<FundManager> fundManagers = fundManagerMapper.selectAll();
        jsonObject = (JSONArray) JSONObject.toJSON(fundManagers);
        return jsonObject;
    }

    @Override
    public JSONObject deleteFundManager(int id) {
        JSONObject jsonObject = new JSONObject();
        int result = fundManagerMapper.deleteByPrimaryKey(id);
        jsonObject.put("resultCode", result);
        return jsonObject;
    }

    @Override
    public JSONObject updateFundManager(int id, String firstName, String lastName, String telephone, String email) {
        JSONObject jsonObject = new JSONObject();
        FundManager fundManager = new FundManager(id, firstName, lastName, telephone, email);
        int resultCode = fundManagerMapper.updateByPrimaryKeySelective(fundManager);
        jsonObject.put("resultCode", resultCode);
        return jsonObject;
    }

    @Override
    public JSONObject calculateBenifit(int id) {
        JSONObject jsonObject = new JSONObject();
        ArrayList<Portfolio> portfolios = portfolioMapper.selectByfundManagerId(id);
        double fundManagerBenifitSum = 0;
        double portfolioBenifitSum = 0;
        int result = 0;
        for (Portfolio portfolio : portfolios) {
            portfolioBenifitSum = 0;
            int porfolioId = portfolio.getId();
            ArrayList<Position> positions = positionMapper.selectByPortfolioId(porfolioId);
            for (Position position : positions) {
                double lastPrice = position.getLastprice();
                double quantity = position.getQuantity();
                String securitySymbol = position.getSecurityid();
                HashMap hashMap = new HashMap();
                hashMap.put("symbol", securitySymbol);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.sql.Date sqlDate = null;
                try {
                    sqlDate = new java.sql.Date(simpleDateFormat.parse("2017-04-03").getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                hashMap.put("date", sqlDate);
                Price price = priceMapper.selectBySymbolAndDate(hashMap);
                double offerPrice = price.getOfferprice();
                double benifit = (offerPrice - lastPrice) * quantity;
                fundManagerBenifitSum += benifit;
                portfolioBenifitSum += benifit;
                BigDecimal bg = new BigDecimal(benifit).setScale(2, RoundingMode.UP);
                benifit = bg.doubleValue();
                position.setBenifit(benifit);
                result = positionMapper.updateByPrimaryKey(position);
                if (result == 0) {
                    jsonObject.put("resultCode", 0);
                    return jsonObject;
                }
                bg = new BigDecimal(portfolioBenifitSum).setScale(2, RoundingMode.UP);
                portfolioBenifitSum = bg.doubleValue();
                portfolio.setBenefit(portfolioBenifitSum);
                result = portfolioMapper.updateByPrimaryKey(portfolio);
                if (result == 0) {
                    jsonObject.put("resultCode", 0);
                    return jsonObject;
                }
            }
            FundManager fundManager = fundManagerMapper.selectByPrimaryKey(id);
            BigDecimal bg = new BigDecimal(fundManagerBenifitSum).setScale(2, RoundingMode.UP);
            fundManagerBenifitSum = bg.doubleValue();
            fundManager.setBalance(fundManagerBenifitSum);
            result = fundManagerMapper.updateByPrimaryKey(fundManager);
            if (result == 0) {
                jsonObject.put("resultCode", 0);
                return jsonObject;
            }

        }
        jsonObject.put("resultCode", 1);
        return jsonObject;
    }
}


