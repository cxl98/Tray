package com.easeArch.common.service;

import com.easeArch.common.entry.FriendItemVo;
import com.easeArch.common.entry.User;

import java.util.List;

public interface  API {
    /**
     * 登录业务
     * @param user 用户
     * @return
     */
    Object login(User user);

    /**
     * 注册业务
     * @param user 用户
     * @return
     */
    Object registry(User user);

    /**
     * 好友
     * @param account 账号
     * @return 好友列表
     */
    List<FriendItemVo> friend(String account);




}
