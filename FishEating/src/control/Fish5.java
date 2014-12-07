/**
 * @Author hurray
 * @Part
 * @Note AI5:如果最近的是小鱼，且大鱼不在附近，则追小鱼；否则同AI4
 * @Encoding UTF-8
 * @Date 2014-12-07 01:44:08
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
 * @Class Fish5
 */
public class Fish5 extends FishTpl {

    public Fish5() {
        this.setColor(Color.BLUE);
        this.setType(5);
//        System.out.println(this.isIsAlive());
    }

    public void getNext(List fishList) {
        FishTpl nearistFish = Methods.getNearistFish(this, fishList);
        FishTpl nearistBigFish = Methods.getNearistBigFish(this, fishList);
        double distanceToWall = Methods.getNearistWall(this);

        boolean nearistIsSmall = nearistFish.getRadius() < this.getRadius() ? true : false;
        boolean bigFishAround = Methods.getDistance(nearistBigFish, this) < R.safeDistance;
        if (nearistIsSmall && bigFishAround) {
            Methods.nextMoveToPoint(this, nearistFish.getX(), nearistFish.getY(), 1);
        } else {
            if (Methods.getDistance(nearistFish, this) <= distanceToWall) {
                Methods.nextMoveToPoint(this, nearistFish.getX(), nearistFish.getY(), -1);
            } else {
                //这里策略不是很好，可以修正(会被逼死在墙角)
                Methods.nextMoveToPoint(this, R.screenX / 2, R.screenY / 2, 1);
            }
        }

    }
}
