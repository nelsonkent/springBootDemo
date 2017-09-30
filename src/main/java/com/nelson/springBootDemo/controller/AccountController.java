package com.nelson.springBootDemo.controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.nelson.springBootDemo.domain.UserAccount;
import com.nelson.springBootDemo.service.AccountService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;
import java.util.logging.Logger;
import java.security.MessageDigest;
import java.math.BigInteger;
@Controller
@RequestMapping(value = "/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    public String accounts(HttpServletRequest request, Map<String, Object>model) throws NoSuchAlgorithmException{
        String account = (String)request.getParameter ("account");
        String name = (String)request.getParameter("name");
        String email = (String)request.getParameter("email");
        String idNumber = (String)request.getParameter("idNumber");
        String password = (String)request.getParameter("password");
        String securityQuestion = (String)request.getParameter("securityQuestion");
        String securityAnswer = (String)request.getParameter("securityAnswer");
        UserAccount userAccount = new UserAccount ();
        userAccount.setAccount (account);
        userAccount.setEmail (email);
        userAccount.setName (name);
        userAccount.setIdNumber (idNumber);
        userAccount.setPassword (password);
        userAccount.setSecurityQuestion (securityQuestion);
        userAccount.setSecurityAnswer (securityAnswer);
        userAccount.setGroupMember ("notVerified");
        MessageDigest md = MessageDigest.getInstance ("MD5");
        md.update(userAccount.toString ().getBytes());
        userAccount.setHash (new BigInteger(1, md.digest()).toString(16));
        accountService.addUserAccount(userAccount);
        model.put("MSG", "add account:"+ account +" success !");
        return "success";
    }

    @RequestMapping(value = "/validAccount.do", method = RequestMethod.GET)
    public void validAccount(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String account = (String)request.getParameter ("account");
        if(accountService.validAccount(account)){
            response.getWriter().write ("true");
        }else{
            response.getWriter().write ("false");
        }
    }

    @RequestMapping(value = "/activate.do", method = RequestMethod.GET)
    public String activateAccount(HttpServletRequest request, HttpServletResponse response, Map<String, Object>model) throws IOException{
        String account = (String)request.getParameter ("account");
        String hash = (String)request.getParameter ("hash");
        UserAccount userAccount = loadUserAccount (account);
        if(userAccount == null || !hash.equals(userAccount.getHash())){
            model.put ("MSG", "SORRY,无效链接！");
            return "error";
        }else if(!"notVerified".equals (userAccount.getGroupMember())){
            model.put ("MSG", "账号无需再次激活，回到<a href='/login'>登录！</a>");
            return "error";
        }
        if(accountService.activateAccount(userAccount)){
            model.put ("MSG", "账号："+account+" 激活成功！");
            return "success";
        }else{
            model.put ("MSG", "SORRY,链接已经过期！");
            return "error";
        }
    }

    private UserAccount loadUserAccount(String account){
        return accountService.loadUserAccount(account);
    }
}
