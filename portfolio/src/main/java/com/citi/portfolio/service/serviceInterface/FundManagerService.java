package com.citi.portfolio.service.serviceInterface;

import com.citi.portfolio.entity.FundManager;

import java.util.ArrayList;
import java.util.HashMap;

public interface FundManagerService {
    HashMap register(String username, String password,String firstName,String lastName,String telephone,String email);
    HashMap login(String username, String password);
    HashMap selectAll();
    HashMap deleteFundManager(int id);
    HashMap updateFundManager(String firstName,String lastName,String telephone,String email,String password);
}
