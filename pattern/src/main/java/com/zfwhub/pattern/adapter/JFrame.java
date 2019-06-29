package com.zfwhub.pattern.adapter;

import java.awt.*;
import java.awt.event.*;

public class JFrame {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Frame f = new Frame("我的第一个窗口");
        f.setSize(400, 600); //设置窗体大小
        f.setLocation(500, 50); //设置窗体位置
        f.setIconImage(Toolkit.getDefaultToolkit().createImage("javagui.jpg"));
        Button b1 = new Button("按钮一");
        Button b2 = new Button("按钮二");
        f.add(b1);
        f.add(b2);
        f.setLayout(new FlowLayout());//设置布局管理器
        //f.addWindowListener(new MyWindowListener );
        //f.addWindowListener(new MyWindowAdapter());
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        b1.addMouseListener(new MouseAdapter() {
            /*@Override
            public void mouseClicked(MouseEvent e) {//单击,不给力,推荐用释放
                System.exit(0);
            }*/
            @Override
            public void mouseReleased(MouseEvent e) { //释放
                System.exit(0);
            }
        });

        b1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                //System.exit(0);
                //System.out.println(e.getKeyCode());
                //if(e.getKeyCode() == 32) {
                /*if(e.getKeyCode() == KeyEvent.VK_SPACE){
                    System.exit(0);
                }*/
            }
        });

        b2.addActionListener(new ActionListener() {
            //添加动作监听,应用场景就是暂停视频和播放视频

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e);
                System.exit(0);//table +　默认空格
            }
        });

        f.setVisible(true);

    }
}
