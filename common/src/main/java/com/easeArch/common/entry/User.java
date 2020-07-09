package com.easeArch.common.entry;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private static final long serialVersionUID=43210L;
    private String uid;
    private String username;
    private String password;
    private Date createMillisTime;
    private String ip;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateMillisTime() {
        return createMillisTime;
    }

    public void setCreateMillisTime(Date createMillisTime) {
        this.createMillisTime = createMillisTime;
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
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createMillisTime=" + createMillisTime +
                ", ip='" + ip + '\'' +
                '}';
    }
}
