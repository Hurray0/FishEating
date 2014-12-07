/**
 * @Author hurray
 * @Part
 * @Note AI3:检测最近的鱼，若比自己大，则远离；若比自己小则贴近；
 * @Encoding UTF-8
 * @Date 2014-12-06 04:48:44
 * @Copyright Hurray@BUPT
 * @MainPage http://www.ihurray.com
 *
 */
package control;

import java.awt.Color;
import java.util.Collections;
import java.util.List;
import model.FishTpl;
import model.Methods;

/**
 * @Author hurray
 * @Class FIsh3
 */
public class Fish3 extends FishTpl {

    public Fish3() {
        this.setColor(Color.yellow);
        this.setType(3);
//        System.out.println(this.isIsAlive());
    }

    public void getNext(List fishList) {
        FishTpl nearistFish = Methods.getNearistFish(this, fishList);
        if (nearistFish == null) {
            return;
        }
        boolean isSmallThanMe = nearistFish.getRadius() < this.getRadius() ? true : false;

        if (isSmallThanMe) {
//            System.out.println("贴近！！！！" + nearistFish.getX() + " " + nearistFish.getY());
            Methods.nextMoveToPoint(this, nearistFish.getX(), nearistFish.getY(), 1);
        } else {
//            System.out.println("远离！！" + nearistFish.getX() + " " + nearistFish.getY());
            Methods.nextMoveToPoint(this, nearistFish.getX(), nearistFish.getY(), -1);

        }
    }
}
