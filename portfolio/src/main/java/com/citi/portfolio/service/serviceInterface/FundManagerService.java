package com.citi.portfolio.service.serviceInterface;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.citi.portfolio.entity.FundManager;

import java.util.ArrayList;
import java.util.HashMap;

public interface FundManagerService {
    JSONObject register(String username, String password, String firstName, String lastName, String telephone, String email);
    JSONObject login(String username, String password);
    JSONArray selectAll();
    JSONObject deleteFundManager(int id);
    JSONObject updateFundManager(String firstName,String lastName,String telephone,String email,String password);
}
