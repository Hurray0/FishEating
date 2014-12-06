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

    public Fish3()
    {
        this.setColor(Color.yellow);
    }
    
    public void getNext(List fishList) {
        FishTpl smallFish = Methods.getNearistSmallFish(this, fishList);
        FishTpl bigFish = Methods.getNearistBigFish(this, fishList);
        if(smallFish == null)
        {
            Methods.nextMoveToPoint(this, bigFish.getX(), bigFish.getY(), -1);
            return ;
        }
        if(Methods.getDistance(this, bigFish) < Methods.getDistance(this, smallFish))
        {
            Methods.nextMoveToPoint(this, bigFish.getX(), bigFish.getY(), -1);
//            System.out.println("远离！！");
        }
        else
        {
            Methods.nextMoveToPoint(this, smallFish.getX(), smallFish.getY(), 1);
//            System.out.println("贴近！！！！");
        }
    }
}
