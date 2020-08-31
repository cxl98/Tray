package com.easyArch.client.ui.controller;


import com.cxl.rpc.remoting.net.params.RpcResponse;
import com.easeArch.common.entry.FriendItemVo;
import com.easeArch.common.entry.User;
import com.easeArch.common.enums.StatusCode;
import com.easeArch.common.handler.Handler;
import com.easyArch.client.handler.HandlerFactory;
import com.easyArch.client.manager.FriendManager;
import com.easyArch.client.manager.UserManager;
import com.easyArch.client.netty.TrayClient;
import com.easyArch.client.ui.ControllerStage;
import com.easyArch.client.ui.LayoutUi;
import com.easyArch.client.ui.Tray;
import com.easyArch.client.ui.UiController;
import com.easyArch.client.ui.container.IdContainer;
import com.easyArch.client.ui.container.ResourceContainer;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class LoginViewController implements Initializable, ControllerStage {
    @FXML
    private Button login;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ImageView closeBtn;
    @FXML
    private ImageView minBtn;
    @FXML
    private ProgressBar loginProgress;

    @FXML
    private Pane errorPane;
    @FXML
    private Label errorTips;

    private User user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        login.disableProperty().bind(
                Bindings.createBooleanBinding(() ->
                                username.getText().length() == 0 ||
                                        0 == password.getText().length(),
                        username.textProperty(), password.textProperty()));

    }

    @Override
    public Stage getStage() {
        UiController uiController = UiController.getInstance();
        return uiController.getStageByName(IdContainer.LoginView);
    }

    @FXML
    public void login() {
        final String name = username.getText();
        final String text = password.getText();
        if (!"".equals(name) || !"".equals(text)) {
            Object object = isObject(name, text);
            List<FriendItemVo> friend = isFriend(name);
            if (null!=friend){
                System.out.println("login"+friend);
                FriendManager.getInstance().receiveFriendsList(friend);
            }
            if (null!=object&&!"".equals(object)) {
                getStage().close();
                UserManager.getInstance().addUser((User) object);
                new Thread(() -> SwingUtilities.invokeLater(Tray::createGUI)).start();
                gotoMain(user);
            }
            else {
                errorPane.setVisible(true);
                errorTips.setText(StatusCode.macth("6"));
            }
        }

    }

    private List<FriendItemVo> isFriend(String name) {
        HandlerFactory factory = HandlerFactory.getFactory();
        Handler send = factory.handler("send");
        return (List<FriendItemVo>) send.handler(name);

    }

    private Object isObject(String name, String pwd) {
        user = new User();
        user.setAccount(name);
        user.setPwd(pwd);
        HandlerFactory factory = HandlerFactory.getFactory();
        Handler login = factory.handler("login");
        return login.handler(user);
    }

    private void gotoMain(User user) {
        UiController uiController = UiController.getInstance();
        uiController.loadStage(IdContainer.MainView, LayoutUi.MainView, StageStyle.UNDECORATED);
        uiController.switchStage(IdContainer.MainView, IdContainer.LoginView);
        FriendManager.getInstance().onFriendLogin(user.getAccount());
    }

    @FXML
    public void login_en() {
        login.setStyle("-fx-background-radius:4;-fx-background-color: #17fdff");
    }

    @FXML
    public void login_ex() {
        login.setStyle("-fx-background-radius:4;-fx-background-color: #e4e4e4");
    }





    @FXML
    public void close() {
        System.exit(0);
    }

    public void min() {
        Stage stage = getStage();
        if (null != stage) {
            stage.setIconified(true);
        }
    }

    @FXML
    public void closeEntered() {
        Image image = ResourceContainer.getClose_1();
        closeBtn.setImage(image);
    }

    @FXML
    public void closeExited() {
        Image image = ResourceContainer.getClose();
        closeBtn.setImage(image);
    }

    @FXML
    public void minEntered() {
        Image image = ResourceContainer.getMin_1();
        minBtn.setImage(image);
    }

    @FXML
    public void minExited() {
        Image image = ResourceContainer.getMin();
        minBtn.setImage(image);
    }

    @FXML
    public void backToLogin() {
        loginProgress.setVisible(false);
        errorPane.setVisible(false);
        login.setVisible(true);
    }

    @FXML
    public void gotoRegister() {
        UiController uiController = UiController.getInstance();
        uiController.switchStage(IdContainer.RegisterView, IdContainer.LoginView);
    }
}
