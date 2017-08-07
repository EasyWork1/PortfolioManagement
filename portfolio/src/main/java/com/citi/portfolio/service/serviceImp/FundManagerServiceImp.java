package com.citi.portfolio.service.serviceImp;

import com.citi.portfolio.dao.FundManagerMapper;
import com.citi.portfolio.entity.FundManager;
import com.citi.portfolio.service.serviceInterface.FundManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FundManagerServiceImp implements FundManagerService {

    @Autowired
    FundManagerMapper fundManagerMapper;
    @Override
    public boolean insertUser(String username, String password) {
        FundManager fundManager = new FundManager();
        fundManager.setUsername(username);
        fundManager.setPassword(password);
        int result = fundManagerMapper.insert(fundManager);
        if (result != 0){
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<FundManager> queryFundManager(String username) {
        ArrayList<FundManager> fundManagers = fundManagerMapper.selectByUserName(username);
        return fundManagers;
    }
}
