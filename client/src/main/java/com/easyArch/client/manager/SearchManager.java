package com.easyArch.client.manager;

import java.util.List;


import com.easeArch.common.entry.User;
import com.easyArch.client.ui.UiController;
import com.easyArch.client.ui.container.IdContainer;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SearchManager {

    private static SearchManager instance = new SearchManager();

    public static SearchManager getInstance() {
        return instance;
    }

    public void refreshRecommendFriends(List<User> items) {
//        StageController stageController = UiBaseService.INSTANCE.getStageController();
////		stageController.switchStage(R.id.SearchView, R.id.MainView);
//        Stage stage = stageController.setStage(R.id.SearchView);

        UiController stageController = UiController.getInstance();
        Stage stage = stageController.getStageByName(IdContainer.RecommendFriendItem);



        GridPane scrollPane = lookUpFriendsContainer();
//        scrollPane.getChildren().clear();

        if (items == null || items.size() <= 0) {
            // 暂时填充假数据
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 2; j++) {
                    Pane item = stageController.load(IdContainer.RecommendFriendItem, Pane.class);
                    decorateItem(item, null);
                    scrollPane.add(item, i, j);
                }
            }
        }
        else {
            for (int i = 0; i < items.size(); i++) {
                int colIndex = items.size() / 1;
                int rowIndex = items.size() % 1;
                Pane itemUi = stageController.load(IdContainer.RecommendFriendItem, Pane.class);
                decorateItem(itemUi, items.get(i));
                scrollPane.add(itemUi, colIndex, rowIndex);
            }
        }

    }

    private Accordion lookUpFriendsContainer() {
//        StageController stageController = UiBaseService.INSTANCE.getStageController();
//        // 使用SplitPane有坑，由于SplitPane没有children子标签，所以这样需要间接lookup
//        Stage stage = stageController.getStageBy(R.id.SearchView);

        UiController stageController = UiController.getInstance();
        Stage stage = stageController.getStageByName(IdContainer.RecommendFriendItem);
       ScrollPane   scrollPane = (ScrollPane)stage.getScene().getRoot().lookup("#friendSp");
//        ObservableList<Node> itmes = scrollPane.getItems();
        Accordion content = (Accordion) scrollPane.getContent();
        content.getPanes().clear();

        return   content;
    }

    private void decorateItem(Pane itemUi, User item) {

        Hyperlink usernameUi = (Hyperlink) itemUi.lookup("#userName");
        usernameUi.setText(item.getUsername());



//        Label nickNameUi = (Label)itemUi.lookup("#nickName");
//        nickNameUi.setText(item == null ? "起个名字好难" : item.getNickName());
//        Label reasonUi = (Label)itemUi.lookup("#reason");
//        reasonUi.setText("10个共同好友");
//
//        ImageView headImage = (ImageView)itemUi.lookup("#headIcon");

    }

}
