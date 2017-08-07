package com.citi.portfolio.service.serviceImp;

import com.citi.portfolio.dao.FundManagerMapper;
import com.citi.portfolio.entity.FundManager;
import com.citi.portfolio.service.serviceInterface.FundManagerService;
import com.citi.portfolio.util.FundManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class FundManagerServiceImp implements FundManagerService {

    @Autowired
    FundManagerMapper fundManagerMapper;
    @Override
    public HashMap register(String username, String password,String firstName,String lastName,String telephone,String email) {
        HashMap hashMap = new HashMap();
        FundManager fundManager = new FundManager();
        fundManager.setUsername(username);
        try {
            fundManager.setPassword(FundManagerUtil.EncoderByMd5(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        hashMap.put("resultCode",0);
        ArrayList<FundManager> fundManagers = fundManagerMapper.selectByUserName(username);
        if (fundManagers.isEmpty()){
            int result = fundManagerMapper.insert(fundManager);
            if (result != 0){

                fundManagers = fundManagerMapper.selectByUserName(username);
                if (!fundManagers.isEmpty()){
                    fundManager = fundManagers.get(0);
                    hashMap.put("fundManager",fundManager);
                    hashMap.put("result",1);
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
        hashMap.put("resultCode",0);
        ArrayList<FundManager> fundManagers = fundManagerMapper.selectByUserName(username);
        if (!fundManagers.isEmpty()){
            FundManager fundManager = fundManagers.get(0);
            try {
                if (fundManager.getPassword().equals(FundManagerUtil.EncoderByMd5(password))){
                    hashMap.put("resultCode",1);
                    hashMap.put("fundManager",fundManager);
                }
                else{
                    hashMap.put("errorMessage","password error.");
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                hashMap.put("errorMessage","NoSuchAlgorithmException");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                hashMap.put("errorMessage","UnsupportedEncodingException");
            }

        }

       return hashMap;
    }

    @Override
    public HashMap selectAll() {
        HashMap hashMap = new HashMap();
        ArrayList<FundManager> fundManagers = fundManagerMapper.selectAll();
        hashMap.put("fundManagers",fundManagers);
        return hashMap;
    }

    @Override
    public HashMap deleteFundManager(int id) {
        HashMap hashMap = new HashMap();
        int resultLine = fundManagerMapper.deleteByPrimaryKey(id);
        hashMap.put("resultLine",resultLine);
        return hashMap;
    }

    @Override
    public HashMap updateFundManager(String firstName, String lastName, String telephone, String email, String password) {
        HashMap hashMap = new HashMap();
        FundManager fundManager = new FundManager(firstName,lastName,telephone,email,password);
        int resultLine = fundManagerMapper.updateByPrimaryKeySelective(fundManager);
        hashMap.put("resultLine",resultLine);
        return hashMap;
    }
}
