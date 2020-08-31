package com.easyArch.client;

import com.cxl.rpc.util.ProxyPush;
import com.easyArch.client.manager.Friend;
import com.easyArch.client.ui.LayoutUi;
import com.easyArch.client.ui.UiController;
import com.easyArch.client.ui.container.IdContainer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TrayApplication extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) {
        UiController uiController = UiController.getInstance();
        uiController.setPrimaryStage("root", stage);
        Stage login = uiController.loadStage(IdContainer.LoginView, LayoutUi.LoginView, StageStyle.UNDECORATED);
        login.setTitle("托盘");
        uiController.loadStage(IdContainer.RegisterView, LayoutUi.RegisterView, StageStyle.UNDECORATED);

        uiController.setStage(IdContainer.LoginView);
//        uiController.setStage(IdContainer.MainView);
    }

    @Override
    public void init() {
        ProxyPush.getInstance().setClassName(new Friend());
    }
}
