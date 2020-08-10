package com.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.File;

public class Test extends Application {
    static final WebView browser = new WebView();
    static final WebEngine webEngine=browser.getEngine();
    public static void main(String[] args) {
       launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        File file = new File("/home/cxl/桌面/周计划/cxl/托盘/Mixly1.0_Linux64/blockly/apps/mixly/index.html");
        String string = file.toURI().toString();
        webEngine.load(string);
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("old "+observable.getValue().name());
            System.out.println("new "+newValue.name());
        });
        Scene scene = new Scene(browser);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
