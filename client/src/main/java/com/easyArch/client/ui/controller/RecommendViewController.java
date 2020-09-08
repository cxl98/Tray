package com.easyArch.client.ui.controller;

import com.easyArch.client.ui.ControllerStage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RecommendViewController implements Initializable, ControllerStage {
    @Override
    public Stage getStage() {
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private ImageView  imageView;

    @FXML
    private Hyperlink userName;





}
