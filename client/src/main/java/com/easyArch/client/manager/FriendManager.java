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
        groupNames.put(0,"我的好友");
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
            friend.setStatus(Constants.ONLINE_STATUS);
            //若该用户存在把他的所有的好友提取出来存到list里面
            List<FriendItemVo> friendItems = new ArrayList<>(friends.values());
            UiController.getInstance().runTask(() -> refreshMyFriendsView(friendItems)
            );
        }
    }


    /**
     * 好友下线刷新
     *
     * @param friendId
     */
    public void onFriendLogout(String friendId) {
        FriendItemVo friend = friends.get(friendId);
        if (friend != null) {
            friend.setStatus(Constants.OFFLINE_STATUS);
            List<FriendItemVo> friendItems = new ArrayList<>(friends.values());
            UiController.getInstance().runTask(() -> refreshMyFriendsView(friendItems)
            );
        }
    }


    public void receiveFriendsList(List<FriendItemVo> friendItems) {
        friends.clear();
        for (FriendItemVo item : friendItems) {
            friends.put(item.getAccount(), item);
        }
//        UiController.getInstance().runTask(() -> refreshMyFriendsView(friendItems)
//        );
    }

    public void refreshMyFriendsView(List<FriendItemVo> friendItems) {
        System.out.println("xxxx??????");
        Accordion competent = getCompetent();
        System.out.println("xxxxxxxxxx???>>>>>>>"+competent);
        for (FriendItemVo itemVo : friendItems) {
            int groupId = itemVo.getFid();
            String groupName = groupNames.get(groupId);
                decorateFriendGroup(competent, groupName, friendItems);
        }


    }

    /**
     * 调整成好友分组结构
     */
//    private void rangeToGroupFriends(List<FriendItemVo> friendItems) {
//        groupFriends.clear();
//        TreeMap<Integer, List<FriendItemVo>> tempGroupFriends = new TreeMap<>();
//        for (FriendItemVo item : friendItems) {
//            int groupId = item.getGroup();
//
//            List<FriendItemVo> frendsByGroup = groupFriends.computeIfAbsent(groupId, k -> new ArrayList<>());
//            groupFriends = tempGroupFriends;
//        }
//    }

    private void decorateFriendGroup(Accordion container, String groupName, List<FriendItemVo> friendItems) {
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
        usernameUi.setText(friendVo.getUsername());

        //隐藏域，聊天界面用
        Label userIdUi = (Label) itemUi.lookup("#friendId");
        userIdUi.setText(friendVo.getUsername());

        ImageView headImage = (ImageView) itemUi.lookup("#headIcon");

        if (!friendVo.isOnline()) {
            headImage.setImage(ImageUtil.convertToGray(headImage.getImage()));
        }

    }

    private Accordion getCompetent() {
        UiController stageController = UiController.getInstance();
        Stage stage = stageController.getStageByName(IdContainer.MainView);

        ScrollPane scrollPane = (ScrollPane) stage.getScene().getRoot().lookup("#friendSp");
        Accordion friendGroup = (Accordion) scrollPane.getContent();
        friendGroup.getPanes().clear();
        return friendGroup;
    }
}

