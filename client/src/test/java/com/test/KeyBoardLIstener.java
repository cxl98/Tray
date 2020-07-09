package com.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;

public class KeyBoardLIstener extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        TextField textField=new TextField();
        textField.setOnKeyTyped(event -> {
            if (event.getCode()== KeyCode.A){
                System.out.println("=========");
            }
        });
        textField.setOnKeyPressed(event -> {
            if (event.getCode()==KeyCode.A){
                System.out.println("KeyPressed");
            }
        });
        textField.setOnKeyReleased(event -> {
            if (event.getCode()==KeyCode.A){
                System.out.println("KeyReleased");
            }
        });
        Scene scene=new Scene(textField,300,250);
        primaryStage.setTitle("hello");
        InputStream inputStream=new FileInputStream("/home/cxl/桌面/周计划/cxl/托盘/Tray/client/src/test/java/com/test/icon.png");
        Image image= new Image(inputStream);
        primaryStage.setIconified(true);
        primaryStage.getIcons().add(image);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
