/**
 * @Author hurray
 * @Part 
 * @Note 
 * @Encoding UTF-8 
 * @Date 2014-12-05 06:11:25
 * @Copyright Hurray@BUPT
 * @MainPage http://www.ihurray.com
 * 
 */

package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @Author hurray
 * @Class FishGraph
 */
public class FishGraph extends JPanel{
    private double radius; //鱼身体大小半径
    private double x; //鱼所在横坐标
    private double y; //鱼所在纵坐标
    private Color color;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(color);
        g.fillOval((int)x, (int)y, (int)radius, (int)radius);
    }

    public FishGraph() throws InterruptedException {
        color = new Color(
	          (new Double(Math.random() * 128)).intValue() + 128,   
	          (new Double(Math.random() * 128)).intValue() + 128,   
	          (new Double(Math.random() * 128)).intValue() + 128);
        Timer timer = new Timer(100, new timeListen());
        timer.start();
    }

//    public void addLength() {
//        this.length += 10;
//    }

    public class timeListen implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            // TODO 自动生成的方法存根
            repaint();
        }

    }
    
    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getXX() {
        return x;
    }

    public void setXX(double x) {
        this.x = x;
    }

    public double getYY() {
        return y;
    }

    public void setYY(double y) {
        this.y = y;
    }
}
