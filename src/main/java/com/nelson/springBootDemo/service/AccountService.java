package com.nelson.springBootDemo.service;

import com.nelson.springBootDemo.domain.UserAccount;
import com.nelson.springBootDemo.domain.UserAccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private UserAccountDAO userAccountDAO;
    public UserAccount getUserAccount(String account){
        return  userAccountDAO.getUserAccount(account);
    }

    public void addUserAccount(UserAccount account) {
        userAccountDAO.addUserAccount(account);
    }

    public boolean validAccount(String account) {
        return userAccountDAO.validAccount(account);
    }

    public boolean activateAccount(UserAccount userAccount) {
        return userAccountDAO.activateAccount(userAccount);
    }

    public UserAccount loadUserAccount(String account) {
        return userAccountDAO.loadUserAccount(account);
    }
}
