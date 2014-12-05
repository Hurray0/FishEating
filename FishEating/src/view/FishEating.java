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

import control.*;
import control.Fish1;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import static java.lang.Math.abs;
//import java.awt.*;
import java.util.*;
import javax.swing.*;
import model.FishTpl;
import model.Methods;
import model.R;

/**
 * @Author hurray
 * @Class FishEating
 */
public class FishEating extends JFrame {

    public List fishList;
    public static int mouseX;
    public static int mouseY;

    class myMouseListener implements MouseMotionListener {

        public void mouseMoved(MouseEvent e) {
            FishEating.mouseX = e.getX();
            FishEating.mouseY = e.getY();
//            System.out.println(mouseX+" "+mouseY);
        }

        public void mouseDragged(MouseEvent e) {
        }
    ;

    }
    
    public FishEating() {

        fishList = new ArrayList();

        //GUI
        setLayout(new BorderLayout());
        setTitle("FishEating");
        setSize(R.screenX, R.screenY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.addMouseMotionListener(new myMouseListener());//对在面板上的鼠标移动进行监听。

        //这里添加鱼
        for (int i = 0; i < 50; i++) {
            fishList.add(new Fish1());
        }
        fishList.add(new Fish2());
//        //這裡對魚進行排序操作
//        ComparatorFish comparator = new ComparatorFish();
//        Collections.sort(fishList, comparator);

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
                        thisFish.getNext(fishList, mouseX, mouseY);
//                        System.out.println("X="+mouseX+" "+"Y="+mouseY);
                    }
                    for (int i = 0; i < fishList.size(); i++) {
                        FishTpl thisFish = (FishTpl) fishList.get(i);
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

    public class ComparatorFish implements Comparator {

        public int compare(Object arg0, Object arg1) {
            FishTpl fish1 = (FishTpl) arg0;
            FishTpl fish2 = (FishTpl) arg0;

            return (int)((fish1.getRadius() - fish2.getRadius())/
                    abs(fish1.getRadius() - fish2.getRadius()));

        }

    }
    
    public static void main(String[] args) {

        new FishEating();
    }
}
