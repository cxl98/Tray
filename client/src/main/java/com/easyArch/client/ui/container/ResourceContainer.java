package com.easyArch.client.ui.container;

import javafx.scene.image.Image;

public class ResourceContainer {
    private static Image close = getImage("login/img/close.png");
    private static Image close_1 = getImage("login/img/close_1.png");
    private static Image min = getImage("login/img/min.png");
    private static Image min_1 = getImage("login/img/min_1.png");
    private static Image head=getImage("main/img/head.png");

    public static Image getMin_1() {
        return min_1;
    }

    public static Image getClose() {
        return close;
    }

    public static Image getClose_1() {
        return close_1;
    }

    public static Image getMin() {
        return min;
    }

    public static Image getHead() {
        return head;
    }

    public static void setHead(Image head) {
        ResourceContainer.head = head;
    }



    private static Image getImage(String resourcePath) {
        return new Image(Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath));

    }
}
