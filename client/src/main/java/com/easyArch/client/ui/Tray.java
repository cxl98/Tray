package com.easyArch.client.ui;


import com.easyArch.client.file.GetPath;
import com.easyArch.client.file.Keyboard;
import com.easyArch.client.file.Peizhi;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Tray {
    public volatile static int x = 0;
    public static Thread thread = null;
    private static Logger logger = Logger.getLogger(Tray.class);
    public static String path = GetPath.getfile();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                createGUI());
    }

    public static void createGUI() {
        logger.info(new Date() + "  托盘启动");
        logger.info(new Date() + "  托盘启动" + GetPath.getfile());
        System.out.println(new Date() + "  托盘启动");
        /*
         * 添加系统托盘
         */
        if (SystemTray.isSupported()) {
            // 获取当前平台的系统托盘
            final SystemTray tray = SystemTray.getSystemTray();

            // 加载一个图片用于托盘图标的显示
//            Image image = Toolkit.getDefaultToolkit().getImage(path+"1043767227.jpg");
            Image image = Toolkit.getDefaultToolkit().getImage("/home/cxl/桌面/周计划/cxl/托盘/Tray/client/src/main/resources/main/img/icon.jpg");
            System.out.println(image);
            // 创建点击图标时的弹出菜单
            PopupMenu popupMenu = new PopupMenu();

            MenuItem openFile = new MenuItem("自定义配置文件");
            MenuItem openItem = new MenuItem("开始工作");
            MenuItem websocket = new MenuItem("连接服务端");
            MenuItem websocketclose = new MenuItem("断开服务端");
            MenuItem exitItem = new MenuItem("退出");

            openFile.addActionListener(e -> {
                if (thread != null) {
//                    try {
////                        MyServer.ss.close();
//                        logger.info(new Date()+"  修改文件");
//                    } catch (IOException e1) {
//                        e1.printStackTrace();
//                    }
                }
                System.out.println(x);
                File file = new File(path + "/键位.txt");
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                        logger.info(file.getPath());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                try {
                    logger.info(file.getPath());
                    Desktop.getDesktop().open(file);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });

            openItem.addActionListener(e -> {
                // 点击打开菜单时显示窗口
//                MyServer myServer=new MyServer();
//                thread=new Thread(myServer);
                logger.info(new Date() + "  开始启动");
                if (thread != null) {
                    Peizhi.readFile(path + "键位.txt", Keyboard.hashMap1, "1");
                }
                thread.start();
                System.out.println(Thread.currentThread().getId());
            });

            websocket.addActionListener(e -> {
                // 点击打开菜单时显示窗口
//                Myclient myclient=new Myclient();
//                thread=new Thread(myclient);
                logger.info(new Date() + "  开始连接服务器");
                thread.start();
                System.out.println(Thread.currentThread().getId());
            });

            websocketclose.addActionListener(e -> {
                // 点击打开菜单时显示窗口
//                if(Myclient.eventLoopGroup!=null){
//                    Myclient.eventLoopGroup.shutdownGracefully();
//                }
                logger.info(new Date() + "  断开与服务器的连接");

            });


            exitItem.addActionListener(e -> {
                // 点击退出菜单时退出程序
                logger.info(new Date() + "  退出托盘程序");
                System.out.println("退出托盘程序");
                System.exit(0);
            });
            popupMenu.add(openFile);
            popupMenu.add(openItem);
            popupMenu.add(websocket);
            popupMenu.add(websocketclose);
            popupMenu.add(exitItem);
            // 创建一个托盘图标
            TrayIcon trayIcon = new TrayIcon(image, "这是一个托盘图标", popupMenu);

            // 托盘图标自适应尺寸
            trayIcon.setImageAutoSize(true);

            try {
                tray.add(trayIcon);
                System.out.println("添加托盘到系统");
            } catch (AWTException e) {
                System.out.println("添加托盘到系统失败");
                e.printStackTrace();
            }


            trayIcon.addActionListener(e -> System.out.println("托盘图标被右键点击"));
            trayIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    switch (e.getButton()) {
                        case MouseEvent.BUTTON1: {
                            System.out.println("托盘图标被鼠标左键被点击");
                            break;
                        }
                        case MouseEvent.BUTTON2: {
                            System.out.println("托盘图标被鼠标中键被点击");
                            break;
                        }
                        case MouseEvent.BUTTON3: {
                            System.out.println("托盘图标被鼠标右键被点击");
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                }
            });

        } else {
            System.out.println("当前系统不支持系统托盘");
        }

    }

}

