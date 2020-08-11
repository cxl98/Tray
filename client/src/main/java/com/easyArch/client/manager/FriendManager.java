package com.easyArch.client.manager;

import com.easeArch.common.entry.FriendItemVo;
import com.easeArch.common.util.ImageUtil;
import com.easyArch.client.constants.Constants;
import com.easyArch.client.ui.LayoutUi;
import com.easyArch.client.ui.UiController;
import com.easyArch.client.ui.container.IdContainer;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.*;

//import com.sun.imageio.plugins.common.ImageUtil;

public class FriendManager {

    private static FriendManager instance = new FriendManager();
    private  static Map<Long, FriendItemVo> friends = new HashMap<>();
    private static Map<Integer, String> groupNames = new HashMap<>();
    private static TreeMap<Integer, List<FriendItemVo>> groupFriends = new TreeMap<>();

    static {
        //好友分组
        groupNames.put(1,"我的好友");
        groupNames.put(2,"同学");

        List<FriendItemVo> list1=new ArrayList<>();
        List<FriendItemVo>  list2=new ArrayList<>();
         FriendItemVo friendItemVo = new FriendItemVo();
        //好友属性
        friendItemVo.setUserId(1000);
        friendItemVo.setRemark("xxx");
//        friendItemVo.setOnline(Constants.ONLINE_STATUS);
        friendItemVo.setUserName("czt");
        friendItemVo.setGroup(1);
        //============================


        FriendItemVo friendItemVo1 = new FriendItemVo();
        friendItemVo1.setUserId(1000);
        friendItemVo1.setRemark("yyy");
//        friendItemVo1.setOnline(Constants.ONLINE_STATUS);
        friendItemVo1.setUserName("cwj");
        friendItemVo1.setGroup(1);

        FriendItemVo friendItemVo2 = new FriendItemVo();
        friendItemVo2.setUserId(1000);
        friendItemVo2.setRemark("yyy");
//        friendItemVo1.setOnline(Constants.ONLINE_STATUS);
        friendItemVo2.setUserName("ckg");
        friendItemVo2.setGroup(2);


        list1.add(0,friendItemVo);
        list1.add(1,friendItemVo1);


        list2.add(0,friendItemVo2);
        groupFriends.put(1,list1);
        groupFriends.put(0,list2);

        friends.put(1000L,friendItemVo);
        friends.put(1001L,friendItemVo1);
        friends.put(1002L,friendItemVo2);
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


    public void onFriendLogin(Long friendId) {
        //把自己从自己所有好友的map集合里面查找出来
//        每个用户都有自己的全部好友的map集合其中包括自己的id和信息
        FriendItemVo friend = friends.get(friendId);
        if (friend != null) {
            friend.setOnline(Constants.ONLINE_STATUS);
            //若该用户存在把他的所有的好友提取出来存到list里面
            List<FriendItemVo> friendItems = new ArrayList<>(friends.values());
            receiveFriendsList(friendItems);
        }
    }


    /**
     * 好友下线刷新
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
            friends.put(item.getUserId(), item);
        }

        rangeToGroupFriends(friendItems);

//        UiBaseService.INSTANCE.runTaskInFxThread(() -> {
        refreshMyFriendsView(friendItems);

    }

    public void refreshMyFriendsView(List<FriendItemVo> friendItems) {
//        StageController stageController = UiBaseService.INSTANCE.getStageController();
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


    }	/**
     * 调整成好友分组结构
     */
    private void rangeToGroupFriends(List<FriendItemVo> friendItems) {
//        this.groupFriends.clear();
        TreeMap<Integer, List<FriendItemVo>> groupFriends = new TreeMap<>();
        for (FriendItemVo item:friendItems) {
            int groupId= item.getGroup();
            List<FriendItemVo> frendsByGroup = groupFriends.get(groupId);
            if (frendsByGroup == null) {
                //若不存在该好友分组 也就是groupId 则在groupFriends里面添加该好友分组
                frendsByGroup = new ArrayList<>();
                groupFriends.put(groupId, frendsByGroup);
                	/*
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
        ListView<Node> listView = new ListView<Node>();
        int onlineCount = 0;
        UiController stageController = UiController.getInstance();
        for (FriendItemVo item:friendItems) {
            if (item.isOnline()) {
                onlineCount++;
            }
//         uiController.loadStage(IdContainer.RegisterView, LayoutUi.RegisterView, StageStyle.UNDECORATED);
            Pane pane = stageController.load(LayoutUi.FriendItem, Pane.class);

            decorateFriendItem(pane, item);
            listView.getItems().add(pane);
        }

        bindDoubleClickEvent(listView);
        String groupInfo = groupName + " " + onlineCount+"/"+friendItems.size();
        TitledPane tp = new TitledPane(groupInfo, listView);
        container.getPanes().add(tp);
    }






    private void decorateFriendItem(Pane itemUi, FriendItemVo friendVo) {
        Label autographLabel = (Label) itemUi.lookup("#signature");
        autographLabel.setText(friendVo.getRemark());
        Hyperlink usernameUi = (Hyperlink) itemUi.lookup("#userName");
        usernameUi.setText(friendVo.getUserName());

        //隐藏域，聊天界面用
        Label userIdUi = (Label)itemUi.lookup("#friendId");
        userIdUi.setText(String.valueOf(friendVo.getUserId()));

        ImageView headImage = (ImageView) itemUi.lookup("#headIcon");

        if (!friendVo.isOnline()) {
            headImage.setImage(ImageUtil.convertToGray(headImage.getImage()));
        }

    }


    private void bindDoubleClickEvent(ListView<Node> listView) {
//        listView.setOnMouseClicked(new DoubleClickEventHandler<Event>() {
//            @Override
//            public void handle(Event event) {
//                if (this.checkVaild()) {
//                    ListView<Node> view = (ListView<Node>) event.getSource();
//                    Node selectedItem = view.getSelectionModel().getSelectedItem();
//                    if (selectedItem == null)
//                        return;
//                    Pane pane = (Pane) selectedItem;
//                    Label userIdUi = (Label)pane.lookup("#friendId");
//
//                    Long friendId = Long.parseLong(userIdUi.getText());
//                    FriendItemVo targetFriend = friends.get(friendId);
//
//                     Long selfId =Long.parseLong( User.getInstance().getUid());
//                    if (friendId == selfId) {
//                        //不能跟自己聊天
//                        return;
//                    }
//                    if (targetFriend != null) {
////                        openChat2PointPanel(targetFriend);
//                    }
//                }
//            }
//        });
    }


//    private void openChat2PointPanel(FriendItemVo targetFriend) {
//        UiController stageController = UiController.getInstance();
//
////        StageController stageController = UiBaseService.INSTANCE.getStageController();
////        Stage chatStage = stageController.setStage(R.id.ChatToPoint);
//
//        Label userIdUi = (Label)chatStage.getScene().getRoot().lookup("#userIdUi");
//        userIdUi.setText(String.valueOf(targetFriend.getUserId()));
//        Hyperlink userNameUi = (Hyperlink)chatStage.getScene().getRoot().lookup("#userName");
////        Label signatureUi = (Label)chatStage.getScene().getRoot().lookup("#signature");
//        userNameUi.setText(targetFriend.getFullName());
//        signatureUi.setText(targetFriend.getSignature());
//
//    }


}