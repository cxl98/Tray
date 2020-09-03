package com.easyArch.server.server;

import com.cxl.rpc.remoting.net.params.RpcResponse;
import com.cxl.rpc.remoting.provider.annotation.RpcService;
import com.cxl.rpc.util.ChannelUtil;
import com.easeArch.common.entry.FriendItemVo;
import com.easeArch.common.entry.User;
import com.easeArch.common.enums.StatusCode;
import com.easeArch.common.res.TrayResponse;
import com.easeArch.common.service.API;
import com.easyArch.server.util.Id;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RpcService
@Service
public class Server implements API {

    @Autowired
    private UserServer userServer;
    @Autowired
    private Id id;



    private static Map<String,Channel> online=new ConcurrentHashMap<>();
    private static List<User> users=new ArrayList<>();




    public Object login(User user) {
        User userByUid = userServer.findUserByUsername(user.getAccount());
        if (null!=userByUid&&userByUid.getAccount().equals(user.getAccount())){
            Channel channel = ChannelUtil.getChannels().getChannel();
            if (online.get(user.getAccount())!=channel){
                onFriend(user);
            }
            online.put(user.getAccount(),channel);
            users.add(user);
            return userByUid;
        }

        return StatusCode.ACCOUNT_NOT_MATCH.getCode();
    }


    private void onFriend(User user) {
        for (User item: users) {
            Channel channel = online.get(item.getAccount());
            RpcResponse rpcResponse=new TrayResponse();
            rpcResponse.setRequestId(user.getAccount());
            rpcResponse.setResult(user);
            try {
                channel.writeAndFlush(rpcResponse).sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Object registry(User user) {
        boolean register = userServer.register(user);
        if (register){
            return StatusCode.SUCCESS.getCode();
        }

        return StatusCode.FAIL.getCode();
    }

    @Override
    public List<FriendItemVo> friend(String account) {
        List<FriendItemVo> friendItemVos = userServer.friendByCount(account);
        if (null==friendItemVos){
            return null;
        }
        return friendItemVos;
    }

    @Override
    public User searchFriend(String account) {
        User user = userServer.findUserByAccount(account);
        if (null==user){
            return null;
        }
        return user;

    }


}
