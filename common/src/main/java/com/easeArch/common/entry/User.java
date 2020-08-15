package com.easeArch.common.entry;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private static final long serialVersionUID=43210L;
    private String account;
    private String pwd;
    private String username;
    private Date cmt;
    private String ip;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCmt() {
        return cmt;
    }

    public void setCmt(Date cmt) {
        this.cmt = cmt;
    }

    public Date getCreateMillisTime() {
        return cmt;
    }

    public void setCreateMillisTime(Date createMillisTime) {
        this.cmt = createMillisTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "User{" +
                "account='" + account + '\'' +
                ", password='" + pwd + '\'' +
                ",username ='" + username + '\'' +
                ", createMillisTime=" + cmt +
                ", ip='" + ip + '\'' +
                '}';
    }
}
