package com.easeArch.common.entry;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private static final long serialVersionUID=43210L;
    private String username;
    private String pwd;
    private String nickname;
    private Date cmt;
    private String ip;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return pwd;
    }

    public void setPassword(String password) {
        this.pwd = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
                "username='" + username + '\'' +
                ", password='" + pwd + '\'' +
                ", nickname='" + nickname + '\'' +
                ", createMillisTime=" + cmt +
                ", ip='" + ip + '\'' +
                '}';
    }
}
