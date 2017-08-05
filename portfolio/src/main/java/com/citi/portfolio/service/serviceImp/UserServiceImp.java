package com.citi.portfolio.service.serviceImp;

import com.citi.portfolio.dao.UserMapper;
import com.citi.portfolio.entity.User;
import com.citi.portfolio.service.serviceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserMapper userMapper;
    @Override
    public boolean insertUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        int result = userMapper.insert(user);
        if (result != 0){
            return true;
        }
        return false;
    }
}
