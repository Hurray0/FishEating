/**
 * @Author hurray
 * @Part
 * @Note
 * @Encoding UTF-8
 * @Date 2014-12-05 11:33:20
 * @Copyright Hurray@BUPT
 * @MainPage http://www.ihurray.com
 *
 */
package view;

import model.FishTpl;
import java.awt.Color;
import java.awt.Graphics;
import java.util.*;
import javax.swing.JPanel;

/**
 * @Author hurray
 * @Class DrawAllFish
 */
public class DrawAllFish extends JPanel {

    private List fishList;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Iterator itr = fishList.iterator();
        while (itr.hasNext()) {
            FishTpl thisFish = (FishTpl) itr.next();

            g.setColor(thisFish.getColor());
            g.fillOval((int) (thisFish.getX() - thisFish.getRadius()),
                    (int) (thisFish.getY()- thisFish.getRadius()),
                    (int) thisFish.getRadius()*2, (int) thisFish.getRadius()*2);
            if (thisFish.getColor() == Color.BLUE) {
                System.out.println("【】" + (int) (thisFish.getX() - thisFish.getRadius()) + " "
                        + (int) (thisFish.getY() - thisFish.getRadius()) + " "
                        + (int) thisFish.getRadius());
            }
//            g.fillOval(-100,-100,200,200);
            g.setColor(java.awt.SystemColor.controlShadow); // 设置边框颜色
            g.drawOval((int) (thisFish.getX() - thisFish.getRadius()),
                    (int) (thisFish.getY()- thisFish.getRadius()),
                    (int) thisFish.getRadius()*2, (int) thisFish.getRadius()*2);
        }

    }

    public DrawAllFish(List fishList) {
        this.fishList = fishList;
    }
}
