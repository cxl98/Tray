package com.easeArch.common.entry;

import com.cxl.rpc.util.Push;

public abstract class Packet implements Push {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public abstract void exec(Object o);
}
