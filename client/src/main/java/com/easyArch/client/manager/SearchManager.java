package com.easyArch.client.manager;

import java.util.List;


import com.easeArch.common.entry.User;
import com.easyArch.client.ui.LayoutUi;
import com.easyArch.client.ui.UiController;
import com.easyArch.client.ui.container.IdContainer;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class SearchManager {

    private static SearchManager instance = new SearchManager();

    public static SearchManager getInstance() {
        return instance;
    }

    public void refreshRecommendFriends(List<User> items) {
        UiController stageController = UiController.getInstance();
        Stage stage = stageController.getStageByName(IdContainer.AddView);
        ScrollPane   scrollPane = (ScrollPane)stage.getScene().getRoot().lookup("#friendSp");
        Accordion content = (Accordion) scrollPane.getContent();
          lookUpFriendsContainer(content,items);
    }

    private void lookUpFriendsContainer(Accordion container,List<User> items) {


        ListView<Node> listView = new ListView<>();

        UiController uiController = UiController.getInstance();


        if (items == null || items.size() <= 0) {
//            // 暂时填充假数据
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 2; j++) {
                    Pane pane = uiController.load(LayoutUi.RecommendFriendItem, Pane.class);
                    decorateItem(pane, null);
                    listView.getItems().add(pane);
                }
            }
            String  groupInfo="推荐好友"+items.size();
            TitledPane titledPane = new TitledPane(groupInfo, listView);
            container.getPanes().add(titledPane);
        }
        else {

            for (User user : items) {
                Pane pane = uiController.load(LayoutUi.RecommendFriendItem, Pane.class);
                decorateItem(pane, user);
                listView.getItems().add(pane);
            }

            String  groupInfo="推荐好友"+items.size();
            TitledPane titledPane = new TitledPane(groupInfo, listView);
            container.getPanes().add(titledPane);
        }


    }







    private void decorateItem(Pane itemUi, User item) {

        Hyperlink usernameUi = (Hyperlink) itemUi.lookup("#userName");
        usernameUi.setText(item == null ? "暂时没有该用户" : item.getUsername());



    }

}
