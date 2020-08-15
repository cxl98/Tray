package com.easeArch.common.entry;


import com.easeArch.common.constants.Constants;

public class FriendItemVo {
    private int fid;
    private String  account;
//    /** 在线状态 {@link Constants#ONLINE_STATUS} */
    /** 昵称 */
    private String username;

    private Byte  status;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Byte getStatus() {
        return status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    public boolean isOnline() {
        return this.status == Constants.ONLINE_STATUS;
    }
    @Override
    public String toString() {
        return "FriendItemVo{" +
                "fid=" + fid +
                ", account='" + account + '\'' +
                ", username=" + username +
                ", status='" + status + '\'' +
                ", group=" + group +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
