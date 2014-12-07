/**
 * @Author hurray
 * @Part
 * @Note AI-1：布朗运动
 * @Encoding UTF-8
 * @Date 2014-12-05 09:07:31
 * @Copyright Hurray@BUPT
 * @MainPage http://www.ihurray.com
 *
 */
package control;

import java.awt.Color;
import model.FishTpl;
import java.util.List;
import java.util.Random;
import model.*;

/**
 * @Author hurray
 * @Class Fish1
 */
public class Fish1 extends FishTpl {

    public Fish1(){
        this.setColor(Color.red);
        this.setType(1);
    }
    
    public void getNext(List fishList) {
        Random random = new Random();
        Methods.nextMoveTo(this, random.nextDouble() * 2 - 1, random.nextDouble() * 2 - 1, random.nextDouble());
    }
}
