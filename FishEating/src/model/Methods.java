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

import static java.lang.Math.abs;
import static java.lang.Math.abs;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import model.*;

/**
 * @Author hurray
 * @Class Methods
 */
public class Methods {

    public static int num = 0;

    public static class ComparatorFishByRadius implements Comparator {

        public int compare(Object arg0, Object arg1) {
            FishTpl fish1 = (FishTpl) arg0;
            FishTpl fish2 = (FishTpl) arg0;

            return (int) ((fish1.getRadius() - fish2.getRadius())
                    / abs(fish1.getRadius() - fish2.getRadius()));

        }

    }

    public static double getNearistWall(FishTpl thisFish) {
        double tmp;
        tmp = thisFish.getX() < thisFish.getY() ? thisFish.getX() : thisFish.getY();
        tmp = tmp < (R.screenX - thisFish.getX()) ? tmp : (R.screenX - thisFish.getX());
        tmp = tmp < (R.screenY - thisFish.getY()) ? tmp : (R.screenY - thisFish.getY());

        return tmp;
    }

    public static FishTpl getNearistFish(FishTpl thisFish, List fishList) {
        FishTpl thatFish, minFish = null;
        double tmpMin = -1;
        for (int i = 0; i < fishList.size(); i++) {
            thatFish = (FishTpl) fishList.get(i);
            if (!thatFish.isIsAlive() || thatFish == thisFish) {
                continue;
            }
            if (tmpMin == -1 || tmpMin > Methods.getDistance(thisFish, thatFish)) {
                minFish = thatFish;
                tmpMin = Methods.getDistance(thisFish, thatFish);
            }
        }
        return minFish;
    }

    public static FishTpl getNearistSmallFish(FishTpl thisFish, List fishList) {
        FishTpl thatFish, minFish = null;
        double tmpMin = -1;
        for (int i = 0; i < fishList.size(); i++) {
            thatFish = (FishTpl) fishList.get(i);
            if (!thatFish.isIsAlive() || thatFish == thisFish) {
                continue;
            }
            if ((tmpMin == -1 || tmpMin > Methods.getDistance(thisFish, thatFish))
                    && thisFish.getRadius() > thatFish.getRadius()) {
                minFish = thatFish;
                tmpMin = Methods.getDistance(thisFish, thatFish);
            }
        }
        return minFish;
    }

    public static FishTpl getNearistBigFish(FishTpl thisFish, List fishList) {
        FishTpl thatFish, maxFish = null;
        double tmpMax = -1;
        for (int i = 0; i < fishList.size(); i++) {
            thatFish = (FishTpl) fishList.get(i);
            if (!thatFish.isIsAlive() || thatFish == thisFish) {
                continue;
            }
            if ((tmpMax == -1 || tmpMax < Methods.getDistance(thisFish, thatFish))
                    && thisFish.getRadius() <= thatFish.getRadius()) {
                maxFish = thatFish;
                tmpMax = Methods.getDistance(thisFish, thatFish);
            }
        }
        return maxFish;
    }

    public static double getDistance(FishTpl fish1, FishTpl fish2) {
        if (fish1 == null || fish2 == null) {
            return 999999999;
        }
        return (sqrt(Math.pow(fish1.getX() - fish2.getX(), 2)
                + Math.pow(fish1.getY() - fish2.getY(), 2))
                - fish1.getRadius() - fish2.getRadius());
    }

    public static double getDistance(FishTpl fish1, double x, double y) {
        if (fish1 == null) {
            return 999999999;
        }
        return (sqrt(Math.pow(fish1.getX() - x, 2)
                + Math.pow(fish1.getY() - y, 2)));
    }

    public static boolean canEat(FishTpl fish1, FishTpl fish2) {
        if (getDistance(fish1, fish2) <= 0 && fish1.getRadius() > fish2.getRadius()) {
            return true;
        } else {
//            System.out.println("吃不了");
            return false;
        }
    }

    public static void nextMoveTo(FishTpl thisFish, double vectorX, double vectorY, double percentage) {
        if (thisFish.isIsAlive()) {
            //实际要求vectorX和vectorY都不大于1，不小于-1。但此处仍做了错误处理。即二者的比例才起作用
            percentage = percentage > 1 ? 1 : percentage;
            percentage = percentage < -1 ? -1 : percentage;
            double speed = R.maxSpeed * percentage;
            if (vectorX != 0 && vectorY != 0) {
                if (Math.abs(vectorX) >= Math.abs(vectorY)) {
                    vectorY /= abs(vectorX);
                    vectorX /= abs(vectorX);
                } else {
                    vectorX /= abs(vectorY);
                    vectorY /= abs(vectorY);
                }
            } else if (vectorX != 0 && vectorY == 0) {
                vectorX /= abs(vectorX);
            } else if (vectorX == 0 && vectorY != 0) {
                vectorY /= abs(vectorY);
            } else {
                thisFish.setNextX(thisFish.getX());
                thisFish.setNextY(thisFish.getY());
            }

            double newX = thisFish.getX() + vectorX * speed;
            newX = newX > R.screenX ? R.screenX : newX;
            newX = newX < 0 ? 0 : newX;
            double newY = thisFish.getY() + vectorY * speed;
            newY = newY > R.screenY ? R.screenY : newY;
            newY = newY < 0 ? 0 : newY;
            thisFish.setNextX(newX);
            thisFish.setNextY(newY);
            thisFish.setTime(thisFish.getTime() + 1);
        }
    }

    public static void nextMoveToPoint(FishTpl thisFish, double x, double y) {
        if (Methods.getDistance(thisFish, x, y) <= R.maxSpeed) {
            thisFish.setNextX(x);
            thisFish.setNextY(y);
        } else {
            nextMoveTo(thisFish, x - thisFish.getX(), y - thisFish.getY(), 1);
        }
//        thisFish.setNextX(x);
//        thisFish.setNextY(y);
    }

    public static void nextMoveToPoint(FishTpl thisFish, double x, double y, double percentage) {
//        System.out.println("nextMoveTo(thisFish, " + x + ", "
//                + y + ", " + (percentage)
//                + ")");
        nextMoveTo(thisFish, x - thisFish.getX(), y - thisFish.getY(), percentage);

    }

    public static void removeEatenFish(List fishList) {
        List removeList = new ArrayList();
        Iterator itr = fishList.iterator();
        while (itr.hasNext()) {
            FishTpl thisFish = (FishTpl) itr.next();
            Iterator itr2 = fishList.iterator();
            while (itr2.hasNext()) {
                FishTpl thatFish = (FishTpl) itr2.next();
                if (thisFish == thatFish) {
                    continue;
                } else {
                    if (canEat(thisFish, thatFish)) {
//                        if (!removeList.contains(thatFish)) {
//                            removeList.add(thatFish);
//                        }
//                        System.out.println("删鱼！！！！");
                        thatFish.goDead();
                    }

                }
            }
        }

//        //由硬删除改为软删除
//        itr = removeList.iterator();
//        while (itr.hasNext()) {
//            try {
//                ((FishTpl)itr.next()).setIsAlive(false);
////                fishList.remove(itr.next());
////                num++;
////                System.out.println("刪除一條魚No." + num);
//            } catch (Exception e) {
//            }
//        }
//        fishList.removeAll(removeList);
    }

}
