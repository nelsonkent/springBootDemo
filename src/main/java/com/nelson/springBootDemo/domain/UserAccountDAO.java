package com.nelson.springBootDemo.domain;

import org.apache.catalina.User;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
@Transactional
@Repository
public class UserAccountDAO {
    @PersistenceContext
    private EntityManager entityManager;
    public UserAccount getUserAccount(String account){
        return  entityManager.find(UserAccount.class, account);
    }

    public void addUserAccount(UserAccount userAccount){
        entityManager.refresh (entityManager);
    }

    public boolean validAccount(String account) {
        Query query = entityManager.createNativeQuery ("select COUNT(*)  from useraccount where account='"+ account +"'");
        int count = ((BigInteger)query.getSingleResult()).intValue ();
        if(count == 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean activateAccount(UserAccount userAccount) {
        try {
            userAccount.setGroupMember ("beginner");
            entityManager.merge(userAccount);
            return true;
        }catch (Exception e){
            e.printStackTrace ();
            return false;
        }
    }

    public UserAccount loadUserAccount(String account) {
        return entityManager.find (UserAccount.class, account);
    }
}
