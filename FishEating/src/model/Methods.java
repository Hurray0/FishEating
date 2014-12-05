/**
 * @Author hurray
 * @Part
 * @Note
 * @Encoding UTF-8
 * @Date 2014-12-05 08:27:59
 * @Copyright Hurray@BUPT
 * @MainPage http://www.ihurray.com
 *
 */
package model;

import control.Fish;
import static java.lang.Math.sqrt;
import model.*;

/**
 * @Author hurray
 * @Class Methods
 */
public class Methods {

    public static double getDistance(Fish fish1, Fish fish2) {
        return (sqrt(Math.pow(fish1.getX() - fish2.getX(), 2)
                + Math.pow(fish1.getY() - fish2.getY(), 2))
                - fish1.getRadius() - fish2.getRadius());
    }

    public static boolean canEat(Fish fish1, Fish fish2) {
        if (getDistance(fish1, fish2) <= 0) {
            return true;
        } else {
            return false;
        }
    }
}
