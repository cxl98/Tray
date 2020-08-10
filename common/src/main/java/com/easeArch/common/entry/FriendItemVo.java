package com.easeArch.common.entry;


import com.easeArch.common.constants.Constants;

public class FriendItemVo {
    private long  userId;
    /** 备注 */
    private String remark;
//    /** 在线状态 {@link Constants#ONLINE_STATUS} */
    private Byte  online;
    /** 昵称 */
    private String userName;
    /** 所属好友分组 */
    private int group;
    /** 分组备注 */
    private String groupName;

//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }

    public void setOnline(Byte online) {
        this.online = online;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Byte getOnline() {
        return online;
    }

    public void setOnline(byte online) {
        this.online = online;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isOnline() {
        return this.online == Constants.ONLINE_STATUS;
    }
}
