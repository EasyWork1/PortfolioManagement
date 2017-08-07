package com.citi.portfolio.service.serviceImp;

import com.citi.portfolio.dao.FundManagerMapper;
import com.citi.portfolio.entity.FundManager;
import com.citi.portfolio.service.serviceInterface.FundManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class FundManagerServiceImp implements FundManagerService {

    @Autowired
    FundManagerMapper fundManagerMapper;
    @Override
    public HashMap register(String username, String password) {
        HashMap hashMap = new HashMap();
        FundManager fundManager = new FundManager();
        fundManager.setUsername(username);
        fundManager.setPassword(password);
        hashMap.put("result",false);
        ArrayList<FundManager> fundManagers = fundManagerMapper.selectByUserName(username);
        if (fundManagers.isEmpty()){
            int result = fundManagerMapper.insert(fundManager);
            if (result != 0){

                fundManagers = fundManagerMapper.selectByUserName(username);
                if (!fundManagers.isEmpty()){
                    fundManager = fundManagers.get(0);
                    hashMap.put("fundManager",fundManager);
                    hashMap.put("result",true);
                }
                hashMap.put("errorMessage","can't find FundManager in DataBase.");
            }
            else{
                hashMap.put("errorMessage","insert failed.");
            }

        }
        else{
            hashMap.put("errorMessage","username has exsit.");
        }

        return hashMap;
    }

    @Override
    public HashMap login(String username,String password) {
        HashMap hashMap = new HashMap();
        ArrayList<FundManager> fundManagers = fundManagerMapper.selectByUserName(username);
        if (!fundManagers.isEmpty()){
            FundManager fundManager = fundManagers.get(0);
            if (fundManager.getPassword().equals(password)){
                hashMap.put("result",true);
                hashMap.put("fundManagers",fundManager);
            }

        }
        else {
            hashMap.put("result",false);
        }
       return hashMap;
    }
}
