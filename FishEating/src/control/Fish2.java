/**
 * @Author hurray
 * @Part
 * @Note 鼠标跟随
 * @Encoding UTF-8
 * @Date 2014-12-06 01:17:45
 * @Copyright Hurray@BUPT
 * @MainPage http://www.ihurray.com
 *
 */
package control;

import java.awt.Color;
import java.util.List;
import java.util.Random;
import model.FishTpl;
import model.Methods;
import model.R;

/**
 * @Author hurray
 * @Class Fish2
 */
public class Fish2 extends FishTpl {

    public Fish2() {
        this.setColor(Color.ORANGE);
        this.setType(2);
        this.setRadius(R.initSize + R.initSizeAdjust);
    }

    public void getNext(List fishList, int mouseX, int mouseY) {

        Methods.nextMoveToPoint(this, mouseX, mouseY);
//        System.out.println(mouseX + " " + mouseY + " " + this.getX() + " " + this.getY());
    }
}
