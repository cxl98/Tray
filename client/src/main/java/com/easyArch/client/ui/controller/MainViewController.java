package com.easyArch.client.ui.controller;

import com.easeArch.common.entry.User;
import com.easyArch.client.manager.UserManager;
import com.easyArch.client.ui.ControllerStage;
import com.easyArch.client.ui.UiController;
import com.easyArch.client.ui.container.IdContainer;
import com.easyArch.client.ui.container.ResourceContainer;
import com.easyArch.client.util.ImageUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable, ControllerStage {

    @FXML
    private ImageView close;
    @FXML
    private ImageView min;
    @FXML
    private ImageView shineImage;
    @FXML
    private Accordion friends;
    @FXML
    private ScrollPane friendSp;
    @FXML
    private Label username;
    @Override
    public Stage getStage() {
        UiController uiController = UiController.getInstance();
        return uiController.getStageByName(IdContainer.MainView);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserManager instance = UserManager.getInstance();
        User user = instance.getUser();
        String username = user.getUsername();
        SimpleStringProperty simpleStringProperty = new SimpleStringProperty();
        simpleStringProperty.set(username);
        this.username.textProperty().bind(simpleStringProperty);
        Image image=ResourceContainer.getHead();
        shineImage.setImage(image);
    }
    @FXML
    private void close(){
        System.exit(0);
    }
    @FXML
    private void closeEntered(){
        Image image= ResourceContainer.getClose_1();
        close.setImage(image);
    }

    @FXML
    private void closeExited(){
        Image image=ResourceContainer.getClose();
        close.setImage(image);
    }

    @FXML
    private void minEntered(){
        Image image=ResourceContainer.getMin_1();
        min.setImage(image);
    }
    @FXML
    private void minExited(){
        Image image=ResourceContainer.getMin();
        min.setImage(image);
    }

    @FXML
    private void min(){
        getStage().setIconified(true);
    }




    public void clicked() {
        FileChooser fileChooser=new FileChooser();
        Stage stage=new Stage();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG","*.png"),new FileChooser.ExtensionFilter("JPG","*.jpg"));
        File file=fileChooser.showOpenDialog(stage);
        if (null!=file){
            String path = file.getAbsoluteFile().toURI().toString();
            System.out.println(path);
            File image = ImageUtil.image(file);
            System.out.println(image);
            String string = file.getAbsoluteFile().toURI().toString();

            Image image1=new Image(string);



            shineImage.setImage(image1);
        }
    }
}
