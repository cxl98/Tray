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

        JPanel upPanel=new JPanel();
        jPanel.add(upPanel);
        JLabel jup=new JLabel();
        jup.setText("上:");
        jup.setFont(contentFont);
        upPanel.add(jup);
        up =new JTextField(20);
        up.setToolTipText("上");
        up.setFont(contentFont);
        upPanel.add(up);

        JPanel downPanel=new JPanel();
        jPanel.add(downPanel);
        JLabel jdown=new JLabel();
        jdown.setText("下:");
        jdown.setFont(contentFont);
        downPanel.add(jdown);
        down=new JTextField(20);
        down.setToolTipText("下");
        down.setFont(contentFont);
        downPanel.add(down);

        JPanel leftPanel=new JPanel();
        jPanel.add(leftPanel);
        JLabel leftJLabel=new JLabel();
        leftJLabel.setText("左：");
        leftJLabel.setFont(contentFont);
        leftPanel.add(leftJLabel);
        left=new JTextField(20);
        left.setToolTipText("左");
        left.setFont(contentFont);
        leftPanel.add(left);

        JPanel rightPanel=new JPanel();
        jPanel.add(rightPanel);
        JLabel rightJLabel=new JLabel();
        rightJLabel.setText("右：");
        rightJLabel.setFont(contentFont);
        rightPanel.add(rightJLabel);
        right=new JTextField(20);
        right.setToolTipText("右");
        right.setFont(contentFont);
        rightPanel.add(right);

        JPanel buttonPanel=new JPanel();
        jPanel.add(buttonPanel);
        JButton jButton=new JButton();
        JButton jButton1=new JButton();
        jButton.setText("修改");
        jButton1.setText("重置");
        jButton1.addActionListener(this);
        jButton.addActionListener(this);
        buttonPanel.add(jButton);
        buttonPanel.add(jButton1);

        final Container contentPane = getContentPane();
        contentPane.add(BorderLayout.CENTER,jPanel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
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
