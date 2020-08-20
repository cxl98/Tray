package com.easyArch.server.config;

import com.cxl.rpc.remoting.provider.impl.RpcSpringProviderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RpcProviderConfig {
    private static final Logger LOGGER= LoggerFactory.getLogger(RpcProviderConfig.class);

    @Bean
    public RpcSpringProviderFactory rpcSpringProviderFactory(){
        RpcSpringProviderFactory providerFactory=new RpcSpringProviderFactory();
        LOGGER.info(">>>>>>>>>>>rpc provider config init finish.");
        return providerFactory;
    }
}
