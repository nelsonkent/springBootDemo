package com.nelson.springBootDemo.domain;

import org.hibernate.annotations.GeneratorType;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "useraccount")
public class UserAccount implements Serializable {
    //账号
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String id;
    @Id
    @Column(name = "account")
    private String account;

    @Column(name = "email")
    private String email;
    //姓名
    @Column(name = "name")
    private String name;
    //ID证件
    @Column(name = "id_number")
    private String idNumber;
    //用户组
    @Column(name = "group_member")
    private String groupMember;
    //个人邮箱确认随机码
    @Column(name = "hash")
    private String hash;
    //密码
    @Column(name = "password")
    private String password;
    //密保问题
    @Column(name = "security_question")
    private String securityQuestion;
    //密保答案
    @Column(name = "security_answer")
    private String securityAnswer;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getGroupMember() {
        return groupMember;
    }

    public void setGroupMember(String groupMember) {
        this.groupMember = groupMember;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
