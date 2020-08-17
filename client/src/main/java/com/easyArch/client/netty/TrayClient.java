package com.easyArch.client.netty;

import com.easeArch.common.service.API;

public class TrayClient extends AbstractTrayClient{
    private static final TrayClient client=new TrayClient();

    public static TrayClient getClient() {
        return client;
    }

    @Override
    public API handler() {
        return super.handler();
    }
}
