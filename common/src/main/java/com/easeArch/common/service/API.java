package com.easeArch.common.service;

import com.easeArch.common.entry.User;

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

}
