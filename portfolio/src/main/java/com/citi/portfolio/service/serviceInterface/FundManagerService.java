package com.citi.portfolio.service.serviceInterface;

import com.citi.portfolio.entity.FundManager;

import java.util.ArrayList;

public interface FundManagerService {
    boolean insertUser(String username,String password);
    ArrayList<FundManager> queryFundManager(String username);
}
