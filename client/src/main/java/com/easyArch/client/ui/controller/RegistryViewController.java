package com.easyArch.client.ui.controller;


import com.cxl.rpc.util.IpUtil;
import com.easeArch.common.entry.User;
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
    private Button register;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    @FXML
    private Label errorTips;
    @FXML
    private ToggleGroup sexGroup;
    @FXML
    private ImageView minBtn;
    @FXML
    private ImageView closeBtn;

    @FXML
    public void register() {
        String userNameText = userName.getText();
        String text = password.getText();

        User user=new User();
        user.setUid(UUID.randomUUID().toString());
        user.setUsername(userNameText);
        user.setPassword(text);
        user.setCreateMillisTime(new Date());
        user.setIp(IpUtil.getIp());
//        API login= (API) new RpcReferenceBean(NetEnum.NETTY, Serializer.SerializerEnum.PROTOSTUFF.getSerializer(), CallType.SYNC, LoadBalance.ROUND,API.class,null,500,"127.0.0.1:8080",null,null,null).getObject();
//        Object login1 = login.registry(user);
//        System.out.println(login1);
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
        userName.setText("");
        password.setText("");
        errorTips.setText("");
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
                ()->0==userName.getText().length()||
                    0==password.getText().length(),
                userName.textProperty(),
                password.textProperty()
        ));

    }
}
