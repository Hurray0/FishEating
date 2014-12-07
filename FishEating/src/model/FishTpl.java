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
package model;

import java.awt.Color;
import java.util.List;
import java.util.Random;
import model.*;
import model.R;
import view.*;

/**
 * @Author hurray
 * @Class SimpleFish
 */
public class FishTpl {

    private double radius; //鱼身体大小半径
    private double x; //鱼所在横坐标
    private double y; //鱼所在纵坐标
    private double nextX; //鱼下一步的横坐标，用作缓存
    private double nextY; //鱼下一步的纵坐标，用作缓存
    private int time; //鱼从生成到现在经历的时间单位
    private boolean isAlive; //是否还活着
    private Color color; //鱼的颜色
    private int type; //AI的序号
//    public static int totalTime = 0; //总时间
//    public static int num = 0;//总个数
//    public static int deadNum = 0;//死亡个数

    public FishTpl() {
        this.isAlive = true;
        this.time = 0;
//        this.num ++;
        Random random = new Random();
        this.setX(Math.abs(random.nextDouble() * R.screenX));
//        System.out.println(this.getX());
        this.setY(Math.abs(random.nextDouble() * R.screenY));
//        System.out.println(this.getY());
        this.setRadius(R.initSize + R.initSizeAdjust * random.nextDouble());
//        System.out.println(this.getRadius());

        color = new Color(
                (new Double(Math.random() * 128)).intValue() + 128,
                (new Double(Math.random() * 128)).intValue() + 128,
                (new Double(Math.random() * 128)).intValue() + 128);
    }
    
    //需要鼠标跟随 时的AI，否则同getNext(fishList)
    public void getNext(List fishList, int mouseX, int mouseY) {
        getNext(fishList);
    }
    
    //AI需要重写的方法。表示下一步要走的情况
    public void getNext(List fishList) {
        this.setNextX(this.getX() + 10);
        this.setNextY(this.getY());
    }
    
    public void move() {
        this.setX(this.getNextX());
        this.setY(this.getNextY());
        this.addTime();
//        this.totalTime ++;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    
    public void addTime() {
        this.time ++;
    }

    public boolean isIsAlive() {
        return isAlive;
    }

    public void goDead() {
        this.setIsAlive(false);
    }
    
    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getNextX() {
        return nextX;
    }

    public void setNextX(double nextX) {
        this.nextX = nextX;
    }

    public double getNextY() {
        return nextY;
    }

    public void setNextY(double nextY) {
        this.nextY = nextY;
    }
    
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public Color getColor(){
        return color;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
