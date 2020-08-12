package com.easyArch.client.manager;

import com.easeArch.common.constants.Constants;
import com.easeArch.common.entry.FriendItemVo;
import com.easyArch.client.ui.LayoutUi;
import com.easyArch.client.ui.UiController;
import com.easyArch.client.ui.container.IdContainer;
import com.easyArch.client.util.ImageUtil;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.*;


public class FriendManager {

    private static FriendManager instance = new FriendManager();
    private static Map<String, FriendItemVo> friends = new HashMap<>();
    private static Map<Integer, String> groupNames = new HashMap<>();
    private static TreeMap<Integer, List<FriendItemVo>> groupFriends = new TreeMap<>();

    static {
        //好友分组


        List<FriendItemVo> list1=new ArrayList<>();
         FriendItemVo friendItemVo = new FriendItemVo();



        //好友属性
        friendItemVo.setUserId("1000");
        friendItemVo.setRemark("xxx");
        friendItemVo.setOnline(Constants.ONLINE_STATUS);
        friendItemVo.setUserName("czt");
        friendItemVo.setGroup(1);
        friendItemVo.setGroupName("我的好友");



        FriendItemVo friendItemVo1 = new FriendItemVo();
        friendItemVo1.setUserId("1001");
        friendItemVo1.setRemark("yyy");
        friendItemVo1.setOnline(Constants.ONLINE_STATUS);
        friendItemVo1.setUserName("cwj");
        friendItemVo1.setGroup(1);
        friendItemVo1.setGroupName("我的好友");





        list1.add(friendItemVo);
        list1.add(friendItemVo1);





        friends.put("1000",friendItemVo);
        friends.put("1001",friendItemVo1);

    }

    /**
     * 分组好友视图
     */


    public static FriendManager getInstance() {
        return instance;
    }

    /**
     * 好友登录刷新
     *
     * @param friendId
     */





    public void onFriendLogin(String friendId) {

        //把自己从自己所有好友的map集合里面查找出来
//        每个用户都有自己的全部好友的map集合其中包括自己的id和信息
        FriendItemVo friend = friends.get(friendId);
        if (friend != null) {
            friend.setOnline(Constants.ONLINE_STATUS);
            //若该用户存在把他的所有的好友提取出来存到list里面
            List<FriendItemVo> friendItems = new ArrayList<>(friends.values());


            for (FriendItemVo itemVo: friendItems) {
                System.out.println(itemVo);
            }

            receiveFriendsList(friendItems);
        }
    }


    /**
     * 好友下线刷新
     *
     * @param friendId
     */
    public void onFriendLogout(long friendId) {
        FriendItemVo friend = friends.get(friendId);
        if (friend != null) {
            friend.setOnline(Constants.OFFLINE_STATUS);
            List<FriendItemVo> friendItems = new ArrayList<>(friends.values());
            receiveFriendsList(friendItems);
        }
    }


    public void receiveFriendsList(List<FriendItemVo> friendItems) {
//        friends.clear();
        for (FriendItemVo item : friendItems) {
            friends.put(item.getUserName(), item);
        }

        rangeToGroupFriends(friendItems);

        refreshMyFriendsView(friendItems);

    }

    public void refreshMyFriendsView(List<FriendItemVo> friendItems) {
        UiController stageController = UiController.getInstance();
        Stage stage = stageController.getStageByName(IdContainer.MainView);

        ScrollPane scrollPane = (ScrollPane) stage.getScene().getRoot().lookup("#friendSp");
        Accordion friendGroup = (Accordion) scrollPane.getContent();
        friendGroup.getPanes().clear();


        for (Map.Entry<Integer, List<FriendItemVo>> entry : groupFriends.entrySet()) {
            int groupId = entry.getKey();
            String groupName = this.groupNames.get(groupId);
            decorateFriendGroup(friendGroup, groupName, entry.getValue());
//        }
        }


    }

    /**
     * 调整成好友分组结构
     */
    private void rangeToGroupFriends(List<FriendItemVo> friendItems) {
//        this.groupFriends.clear();
        TreeMap<Integer, List<FriendItemVo>> groupFriends = new TreeMap<>();
        for (FriendItemVo item : friendItems) {
            int groupId = item.getGroup();

            List<FriendItemVo> frendsByGroup = groupFriends.computeIfAbsent(groupId,k-> new ArrayList<>());
            if (frendsByGroup == null) {
                //若不存在该好友分组 也就是groupId 则在groupFriends里面添加该好友分组
                frendsByGroup = new ArrayList<>();
                groupFriends.put(groupId, frendsByGroup);
                	/*
=======
            List<FriendItemVo> frendsByGroup = groupFriends.computeIfAbsent(groupId, k -> new ArrayList<>());
            //若不存在该好友分组 也就是groupId 则在groupFriends里面添加该好友分组
            /*
>>>>>>> cxl/master
			*
		     查询出该用户有几种好友分组
			* */
                this.groupNames.put(groupId, item.getGroupName());
            }

//			把相同groupid的好友放在同一个group里面
            frendsByGroup.add(item);
        }
        this.groupFriends = groupFriends;
    }


    private void decorateFriendGroup(Accordion container, String groupName, List<FriendItemVo> friendItems) {
        System.out.println(">>>>>>"+groupName);
        ListView<Node> listView = new ListView<>();
        int onlineCount = 0;
        UiController stageController = UiController.getInstance();
        for (FriendItemVo item : friendItems) {
            if (item.isOnline()) {
                onlineCount++;
            }
            Pane pane = stageController.load(LayoutUi.FriendItem, Pane.class);

            decorateFriendItem(pane, item);
            listView.getItems().add(pane);
        }

        String groupInfo = groupName + " " + onlineCount + "/" + friendItems.size();
        TitledPane tp = new TitledPane(groupInfo, listView);
        container.getPanes().add(tp);
    }


    private void decorateFriendItem(Pane itemUi, FriendItemVo friendVo) {
        Hyperlink usernameUi = (Hyperlink) itemUi.lookup("#userName");
        usernameUi.setText(friendVo.getUserName());

        //隐藏域，聊天界面用
        Label userIdUi = (Label) itemUi.lookup("#friendId");
        userIdUi.setText(String.valueOf(friendVo.getUserId()));

        ImageView headImage = (ImageView) itemUi.lookup("#headIcon");

        if (!friendVo.isOnline()) {
            headImage.setImage(ImageUtil.convertToGray(headImage.getImage()));
        }

    }


}