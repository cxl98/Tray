package com.easyArch.client.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class UiController {
    private static final UiController instance = new UiController();

    public static UiController getInstance() {
        return instance;
    }

    private UiController() {
    }

    private ConcurrentMap<String, Stage> stageMap = new ConcurrentHashMap<>();
    private ConcurrentMap<String, ControllerStage> controllerStageMap = new ConcurrentHashMap<>();

    public Stage getStageByName(String name) {
        return this.stageMap.get(name);
    }

    public void setPrimaryStage(String name, Stage stage) {
        addStage(name, stage);
    }

    private void addStage(String name, Stage stage) {
        stageMap.put(name, stage);
    }

    public Stage loadStage(String name, String resource, StageStyle... styles) {
        Stage result = null;
        try {
            URL url = Thread.currentThread().getContextClassLoader().getResource(resource);
            FXMLLoader loader = new FXMLLoader(url);
            loader.setResources(ResourceBundle.getBundle("i18n/message"));

            Pane pane = loader.load();

            ControllerStage controller = loader.getController();
            controllerStageMap.put(name, controller);
            Scene scene = new Scene(pane);

            result = new Stage();
            result.setScene(scene);

            for (StageStyle item : styles) {
                result.initStyle(item);
            }
            addStage(name, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean switchStage(String toShow, String toClose) {
        Stage stageByName = getStageByName(toClose);
        Stage stage = setStage(toShow);
        if (null == stageByName || null == stage) {
            return false;
        }
        stageByName.close();
        stage.show();
        return true;
    }

    public Stage setStage(String name) {
        Stage stage = getStageByName(name);
        if (null == stage) {
            return null;
        }
        stage.show();
        return stage;
    }

    public void closeStage(String name) {
        Stage target = getStageByName(name);
        target.close();
    }
}
