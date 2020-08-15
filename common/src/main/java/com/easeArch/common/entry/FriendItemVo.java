package com.easeArch.common.entry;


import com.easeArch.common.constants.Constants;

public class FriendItemVo {
    private int fid;
    private String  username;
//    /** 在线状态 {@link Constants#ONLINE_STATUS} */
    private Byte  online;
    /** 昵称 */
    private String nickname;
    /** 所属好友分组 */
    private int group;
    /** 分组备注 */
    private String groupName;


    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Byte getOnline() {
        return online;
    }

    public void setOnline(Byte online) {
        this.online = online;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "FriendItemVo{" +
                "fid=" + fid +
                ", username='" + username + '\'' +
                ", online=" + online +
                ", nickname='" + nickname + '\'' +
                ", group=" + group +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
