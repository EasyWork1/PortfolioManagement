package com.citi.portfolio.service.serviceImp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.citi.portfolio.dao.FundManagerMapper;
import com.citi.portfolio.dao.PortfolioMapper;
import com.citi.portfolio.dao.PositionMapper;
import com.citi.portfolio.entity.FundManager;
import com.citi.portfolio.entity.Portfolio;
import com.citi.portfolio.entity.Position;
import com.citi.portfolio.service.serviceInterface.FundManagerService;
import com.citi.portfolio.util.FundManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class FundManagerServiceImp implements FundManagerService {

    @Autowired
    FundManagerMapper fundManagerMapper;
    PortfolioMapper portfolioMapper;
    PositionMapper positionMapper;
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
        jsonObject.put("resultCode",0);
        ArrayList<FundManager> fundManagers = fundManagerMapper.selectByUserName(username);
        if (fundManagers.isEmpty()){
            int result = fundManagerMapper.insert(fundManager);
            if (result != 0){

                fundManagers = fundManagerMapper.selectByUserName(username);
                if (!fundManagers.isEmpty()){
                    fundManager = fundManagers.get(0);
                    jsonObject = (JSONObject) JSONObject.toJSON(fundManager);
                    jsonObject.put("resultCode",1);
                }
                else{
                    jsonObject.put("errorMessage","can't find FundManager in DataBase.");
                }

            }
            else{
                jsonObject.put("errorMessage","insert failed.");
            }

        }
        else{
            jsonObject.put("errorMessage","username has exsit.");
        }

        return jsonObject;
    }

    @Override
    public JSONObject login(String username,String password) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("resultCode",0);
        ArrayList<FundManager> fundManagers = fundManagerMapper.selectByUserName(username);
        if (!fundManagers.isEmpty()){
            FundManager fundManager = fundManagers.get(0);
            try {
                if (fundManager.getPassword().equals(FundManagerUtil.EncoderByMd5(password))){
                    jsonObject = (JSONObject)JSONObject.toJSON(fundManager);
                    jsonObject.put("resultCode",1);
                }
                else{
                    jsonObject.put("errorMessage","password error.");
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                jsonObject.put("errorMessage","NoSuchAlgorithmException");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                jsonObject.put("errorMessage","UnsupportedEncodingException");
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
        jsonObject.put("resultCode",result);
        return jsonObject;
    }

    @Override
    public JSONObject updateFundManager(int id,String firstName, String lastName, String telephone, String email) {
        JSONObject jsonObject = new JSONObject();
        FundManager fundManager = new FundManager(id,firstName,lastName,telephone,email);
        int resultCode = fundManagerMapper.updateByPrimaryKeySelective(fundManager);
        jsonObject.put("resultCode",resultCode);
        return jsonObject;
    }

    @Override
    public JSONObject calculateBenifit(int id) {
        JSONObject jsonObject = new JSONObject();
        ArrayList<Portfolio> portfolios = portfolioMapper.selectByfundManagerId(id);
        for (Portfolio portfolio: portfolios){
            int porfolioId = portfolio.getId();
            positionMapper.selectByPortfolioId(porfolioId);
        }
        return jsonObject;

    }
}
