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
import java.awt.event.MouseListener;
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
    
    class mouseClickListener implements MouseListener {

        List fishList;

        class AI_Info {
            public int totalNum = 0;
            public int aliveNum = 0;
            public int totalTime = 0;

            public void addFish(FishTpl thisFish) {
                totalTime += thisFish.getTime();
                totalNum += 1;
                if(thisFish.isIsAlive())
                {
                    aliveNum += 1;
                }
            }
        }

        public mouseClickListener(List fishList) {
            this.fishList = fishList;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            int maxTime = 0;
            HashMap<Integer, AI_Info> map = new HashMap<Integer, AI_Info>();
            for (int i = 0; i < fishList.size(); i++) {
                FishTpl thisFish = (FishTpl) fishList.get(i);
                int thisTime = thisFish.getTime();
                if (thisFish.getTime() > maxTime) {
                    maxTime = thisTime;
                }
                if (map.containsKey(thisFish.getType())) {
                    AI_Info thisAI = map.get(thisFish.getType());
                    thisAI.addFish(thisFish);
                } else {
                    AI_Info newAI = new AI_Info();
                    newAI.addFish(thisFish);
                    map.put(thisFish.getType(), newAI);
                }
            }

            System.out.println("总时间为：" + maxTime);
            Iterator iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                int key = (int) entry.getKey();
                AI_Info ai = (AI_Info) entry.getValue();
                System.out.println("AI-" + key + ": 共"+ ai.totalNum +"个， "
                        + "死亡"+(ai.totalNum - ai.aliveNum)+"个， "
                        + "存活"+ai.aliveNum+"个，"
                        + "平均存活时间为"+ai.totalTime/ai.totalNum);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
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
        this.addMouseListener(new mouseClickListener(fishList));

        //这里添加鱼
        for (int i = 0; i < 100; i++) {
            fishList.add(new Fish1());
        }
        for (int i = 0; i < 100; i++) {
            fishList.add(new Fish3());
        }
        for (int i = 0; i < 100; i++) {
            fishList.add(new Fish4());
        }
//        fishList.add(new Fish2());
        for (int i = 0; i < 100; i++) {
            fishList.add(new Fish5());
        }
//        fishList.add(new Fish3());
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
                    for (int i = 0; i < fishList.size(); i++) {
                        FishTpl thisFish = (FishTpl) fishList.get(i);
                        if (thisFish.isIsAlive()) {
                            thisFish.getNext(fishList, mouseX, mouseY);
                        }
//                        System.out.println("X="+mouseX+" "+"Y="+mouseY);
                    }
                    for (int i = 0; i < fishList.size(); i++) {
                        FishTpl thisFish = (FishTpl) fishList.get(i);
                        if (thisFish.isIsAlive()) {
                            thisFish.move();
                        }
                    }
                    Methods.removeEatenFish(fishList);
                    repaint();
                    try {
                        Thread.sleep(R.SLEEPTIME);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                }
            }
        }.start();
    }

    public static void main(String[] args) {

        new FishEating();
    }
}
