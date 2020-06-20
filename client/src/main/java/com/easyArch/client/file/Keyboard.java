package com.easyArch.client.file;



import com.easyArch.client.ui.Tray;

import java.awt.*;
import java.util.HashMap;

public class Keyboard {
    /**
     hashMap 表示键盘对应java属性
     */
   public static HashMap<String,Integer> hashMap;
   /**
   * hashMap1 表示手柄对应键盘
   */
    public static HashMap<String,String> hashMap1;
    private static Robot robot;
    static {
        try {
            robot = new Robot();
            hashMap=new HashMap<>();
            hashMap1=new HashMap<>();
            System.out.println("=====");
            Peizhi.readFile(Tray.path+"键位.txt",hashMap1,"1");
            Peizhi.readFile(Tray.path+"配置.txt",hashMap,"0");
        } catch (AWTException e) {
            e.printStackTrace();
        }

        // 执行完一个事件后再执行下一个
        robot.setAutoWaitForIdle(true);
    }

    public  void press(int key){
        robot.keyPress(key);
        robot.keyRelease(key);

    }

    public static void main(String[] args) {
        System.out.println(Integer.valueOf("48"));
    }

}
