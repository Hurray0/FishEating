/**
 * @Author hurray
 * @Part
 * @Note
 * @Encoding UTF-8
 * @Date 2014-12-05 08:22:14
 * @Copyright Hurray@BUPT
 * @MainPage http://www.ihurray.com
 *
 */
package control;

import java.util.Random;
import model.*;
import model.R;
import view.FishGraph;

/**
 * @Author hurray
 * @Class SimpleFish
 */
public class Fish extends FishGraph {

    
    private int time; //鱼从生成到现在经历的时间单位
    private boolean isAlive; //是否还活着

    public Fish() throws Exception {
        this.isAlive = true;
        this.time = 0;
        Random random = new Random();
        this.setXX(Math.abs(random.nextDouble() * R.screenX));
        System.out.println(this.getXX());
        this.setYY(Math.abs(random.nextDouble() * R.screenY));
        System.out.println(this.getYY());
        this.setRadius(R.initSize + R.initSizeAdjust * random.nextDouble() % 1);
    }

    

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isIsAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public void move(double vectorX, double vectorY, double percentage) {
        if (this.isIsAlive()) {
            //实际要求vectorX和vectorY都不大于1，不小于-1。但此处仍做了错误处理。即二者的比例才起作用
            percentage = percentage > 1 ? 1 : percentage;
            percentage = percentage < 0 ? 0 : percentage;
            double speed = R.maxSpeed * percentage;
            if (vectorX != 0 && vectorY != 0) {
                if (Math.abs(vectorX) >= Math.abs(vectorY)) {
                    vectorY /= vectorX;
                    vectorX /= vectorX;
                } else {
                    vectorX /= vectorY;
                    vectorY /= vectorY;
                }
            } else if (vectorX != 0 && vectorY == 0) {
                vectorX /= vectorX;
            } else if (vectorX == 0 && vectorY != 0) {
                vectorY /= vectorY;
            }

            double newX = this.getX() + vectorX * speed;
            newX = newX > R.screenX ? R.screenX : newX;
            newX = newX < 0 ? 0 : newX;
            double newY = this.getY() + vectorY * speed;
            newY = newY > R.screenY ? R.screenY : newY;
            newY = newY < 0 ? 0 : newY;
            this.setXX(newX);
            this.setYY(newY);
            this.setTime(this.getTime() + 1);
        }
    }

    public void moveToPoint(double x, double y) {
        this.move(x - this.getX(), y - this.getY(), 1);
    }

    public void moveToPoint(double x, double y, double percentage) {
        this.move(x - this.getX(), y - this.getY(), percentage);
    }
}
