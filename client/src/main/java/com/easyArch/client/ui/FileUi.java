package com.easyArch.client.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FileUi extends JFrame implements ActionListener {
    private JTextField up;//上
    private JTextField down;//下
    private JTextField left;//左
    private JTextField right;//右

    public FileUi()  {
        setting();
        initBody();
    }

    private void setting() {
        setBackground(new Color(255,255,255));
        setSize(450,300);
        setResizable(false);
        setLocation(250, 250);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                setLocationRelativeTo(null);
            }
        });
    }


    private void initBody() {
        Font contentFont=new Font("宋体",Font.PLAIN,20);

        JPanel jPanel=new JPanel(new GridLayout(3,1));

        JPanel puppetNamePanel=new JPanel();
        jPanel.add(puppetNamePanel);
        JLabel jup=new JLabel();
        jup.setText("上:");
        jup.setFont(contentFont);
        puppetNamePanel.add(jup);

        up =new JTextField(10);
        up.setToolTipText("上");
        up.setFont(contentFont);
        puppetNamePanel.add(up);
        JLabel jdown=new JLabel();
        jdown.setText("上:");
        jdown.setFont(contentFont);
        puppetNamePanel.add(jdown);

//        up =new JTextField(10);
//        up.setToolTipText("上");
//        up.setFont(contentFont);
//        puppetNamePanel.add(up);
//        JLabel jLabel=new JLabel();
//        jLabel.setText("上:");
//        jLabel.setFont(contentFont);
//        puppetNamePanel.add(jLabel);
//
//        up =new JTextField(10);
//        up.setToolTipText("上");
//        up.setFont(contentFont);
//        puppetNamePanel.add(up);
//        JLabel jLabel=new JLabel();
//        jLabel.setText("上:");
//        jLabel.setFont(contentFont);
//        puppetNamePanel.add(jLabel);
//
//        up =new JTextField(10);
//        up.setToolTipText("上");
//        up.setFont(contentFont);
//        puppetNamePanel.add(up);

        JPanel remoteButtonPanel=new JPanel();
        jPanel.add(remoteButtonPanel);
        JButton jButton=new JButton();
        jButton.setText("远程");
        jButton.addActionListener(this);
        remoteButtonPanel.add(jButton);

        final Container contentPane = getContentPane();
        contentPane.add(BorderLayout.CENTER,jPanel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void s() {
        SwingUtilities.invokeLater(()->{
            setVisible(true);
        });
    }

    public static void main(String[] args) {
        FileUi fileUi=new FileUi();
        fileUi.s();
    }
}
