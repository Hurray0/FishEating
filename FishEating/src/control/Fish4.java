/**
 * @Author hurray
 * @Part
 * @Note AI4:在AI3的基础上加上墙的判断（与墙的距离和与鱼的距离同时判断）
 * @Encoding UTF-8
 * @Date 2014-12-07 01:10:08
 * @Copyright Hurray@BUPT
 * @MainPage http://www.ihurray.com
 *
 */
package control;

import java.awt.Color;
import java.util.List;
import model.FishTpl;
import model.Methods;
import model.R;

/**
 * @Author hurray
 * @Class Fish4
 */
public class Fish4 extends FishTpl {

    public Fish4() {
        this.setColor(Color.GREEN);
        this.setType(4);
//        System.out.println(this.isIsAlive());
    }

    public void getNext(List fishList) {
        FishTpl nearistFish = Methods.getNearistFish(this, fishList);
        double distanceToWall = Methods.getNearistWall(this);
        if (Methods.getDistance(nearistFish, this) <= distanceToWall) {
            boolean isSmallThanMe = nearistFish.getRadius() < this.getRadius() ? true : false;
            if (nearistFish == null) {
                System.out.println("已经没有更小的鱼了！");
                return;
            }
            if (isSmallThanMe) {
                Methods.nextMoveToPoint(this, nearistFish.getX(), nearistFish.getY(), 1);
//            System.out.println("贴近！！！！");

            } else {
                Methods.nextMoveToPoint(this, nearistFish.getX(), nearistFish.getY(), -1);
//            System.out.println("远离！！");

            }
        } else {
            Methods.nextMoveToPoint(this, R.screenX/2, R.screenY/2, 1);
        }
    }
}
