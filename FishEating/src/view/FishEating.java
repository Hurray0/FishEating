/**
 * @Author hurray
 * @Part FishEating
 * @Note 程序主入口
 * @Encoding UTF-8
 * @Date 2014-12-05 08:20:43
 * @Copyright Hurray@BUPT
 * @MainPage http://www.ihurray.com
 *
 */
package view;

import model.FishTpl;
import control.Fish1;
import control.*;
import java.awt.BorderLayout;
//import java.awt.*;
import java.util.*;
import javax.swing.*;
import model.Methods;
import model.R;

/**
 * @Author hurray
 * @Class FishEating
 */
public class FishEating extends JFrame {

    public List fishList;

    public FishEating() {

        fishList = new ArrayList();

        //GUI
        setLayout(new BorderLayout());
        setTitle("FishEating");
        setSize(R.screenX, R.screenY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //这里添加鱼
        fishList.add(new Fish1());
        fishList.add(new Fish1());
        
        //绘制鱼的调用
        this.add(new DrawAllFish(fishList));
        refreshUI();

        setVisible(true); //这句必须放在最后，否则会出现panel显示不出来的情况。。
    }

    public void refreshUI() {
        new Thread() {
            public void run() {
                while (true) {
                    Iterator itr = fishList.iterator();
                    while (itr.hasNext()) {
                        FishTpl thisFish = (FishTpl) itr.next();
                        thisFish.getNext(fishList);
                    }
                    itr = fishList.iterator();
                    while (itr.hasNext()) {
                        FishTpl thisFish = (FishTpl) itr.next();
                        thisFish.move();
                    }
                    Methods.removeEatenFish(fishList);
                    try {
                        Thread.sleep(R.SLEEPTIME);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                    repaint();
                }
            }
        }.start();
    }

    public static void main(String[] args) {

        new FishEating();
    }
}
