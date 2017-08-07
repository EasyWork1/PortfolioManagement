package com.citi.portfolio.service.serviceInterface;

import java.util.HashMap;

public interface FundManagerService {
    HashMap register(String username, String password);
    HashMap login(String username, String password);
}
