package com.easyArch.client.ui.controller;


import com.cxl.rpc.util.IpUtil;
import com.easeArch.common.entry.User;
import com.easeArch.common.handler.Handler;
import com.easyArch.client.handler.HandlerFactory;
import com.easyArch.client.ui.ControllerStage;
import com.easyArch.client.ui.UiController;
import com.easyArch.client.ui.container.IdContainer;
import com.easyArch.client.ui.container.ResourceContainer;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;

public class RegistryViewController implements Initializable, ControllerStage {
    @FXML
    public TextField nickname;
    @FXML
    private Button register;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label errorTips;
    @FXML
    private ImageView minBtn;
    @FXML
    private ImageView closeBtn;
    private User user;

    @FXML
    public void register() {
        String userName= username.getText();
        String pwd = password.getText();
        String nickName = nickname.getText();
        register(userName,pwd,nickName);

        if (null!=user&&!"".equals(user)){
            HandlerFactory handlerFactory=HandlerFactory.getFactory();
            Handler registry = handlerFactory.handler("registry");
            Object handler = registry.handler(user);
            if (null!=handler&&!handler.equals("")){
                gotoLogin();
            }
        }
    }

    private void register(String userName, String pwd, String nickName) {
        user=new User();
        user.setAccount(userName);
        user.setPwd(pwd);
        user.setUsername(nickName);
        user.setCreateMillisTime(new Date());
        user.setIp(IpUtil.getIp());
    }

    @FXML
    public void register_entered() {
        register.setStyle("-fx-background-radius:4;-fx-background-color: #097299");
    }
    @FXML
    public void register_exit() {
        register.setStyle("-fx-background-radius:4;-fx-background-color: #2d50bb");
    }
    @FXML
    public void gotoLogin() {
        clearFields();
        UiController uiController=UiController.getInstance();
        uiController.switchStage(IdContainer.LoginView,IdContainer.RegisterView);
    }

    private void clearFields() {
        username.setText("");
        password.setText("");
        errorTips.setText("");
        nickname.setText("");
        errorTips.setVisible(false);
    }

    @FXML
    public void close() {
        System.exit(0);
    }
    @FXML
    public void closeEntered() {
        Image image= ResourceContainer.getClose_1();
        closeBtn.setImage(image);
    }
    @FXML
    public void closeExited() {
        Image image=ResourceContainer.getClose();
        closeBtn.setImage(image);
    }
    @FXML
    public void min() {
        Stage stage=getStage();
        if (null!=stage){
            stage.setIconified(true);
        }
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

    @Override
    public Stage getStage() {
        UiController uiController=UiController.getInstance();
       return uiController.getStageByName(IdContainer.RegisterView);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        register.disableProperty().bind(Bindings.createBooleanBinding(
                ()->0==username.getText().length()||
                    0==password.getText().length()||0==nickname.getText().length(),
                username.textProperty(),
                password.textProperty(),
                nickname.textProperty()
        ));

    }
}
