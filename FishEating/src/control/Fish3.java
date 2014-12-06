/**
 * @Author hurray
 * @Part
 * @Note
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
//        System.out.println(this.isIsAlive());
    }

    public void getNext(List fishList) {
        FishTpl nearistFish = Methods.getNearistFish(this, fishList);
        boolean isSmallThanMe = nearistFish.getRadius() < this.getRadius() ? true : false;
        if(nearistFish == null)
            return;
        if (isSmallThanMe) {
            Methods.nextMoveToPoint(this, nearistFish.getX(), nearistFish.getY(), 1);
//            System.out.println("贴近！！！！");

        } else {
            Methods.nextMoveToPoint(this, nearistFish.getX(), nearistFish.getY(), -1);
//            System.out.println("远离！！");

        }
    }
}
