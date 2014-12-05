/**
 * @Author hurray
 * @Part FishEating
 * @Note 程序主入口
 * @Encoding UTF-8
 * @Date 2014-12-05 08:20:43
 * @Copyright Hurray@BUPT
 * @MainPage http://www.ihurray.com
 *
 */
package view;

import javax.swing.*;
import java.awt.*;
import model.R;
import control.*;

/**
 * @Author hurray
 * @Class FishEating
 */
public class FishEating extends JFrame {

    public FishEating() throws Exception {
        //GUI
//        setLayout(new BorderLayout());
        setTitle("FishEating");
        setSize(R.screenX, R.screenY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        try {
            Fish test = new Fish();
            this.add(test);
            while (true) {
                Thread.sleep(500);
            }
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) throws Exception {
        new FishEating();
    }
}
