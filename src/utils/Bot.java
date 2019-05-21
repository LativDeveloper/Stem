package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bot {
    private Robot robot;

    public Bot() {
        try {
            robot = new Robot();
            robot.setAutoDelay(40);
            robot.setAutoWaitForIdle(true);
            robot.delay(2000);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void type(int keyCode) {
        robot.delay(40);
        robot.keyPress(keyCode);
        robot.keyRelease(keyCode);
    }

    /*
    * [a-z] - [65-90]
    * */
    public void type(String text) {
//        System.out.println("Bot.type: " + text);
        byte[] bytes = text.getBytes();
        for (byte code : bytes) {
//            System.out.print(code + " ");
            code -= 32;
            type(code);
        }
    }

    public void leftClick() {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(200);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(200);
    }

    public void rightClick() {
        robot.mousePress(InputEvent.BUTTON3_MASK);
        robot.delay(200);
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
        robot.delay(200);
    }

    public void mouseMove(int x, int y) {
        robot.mouseMove(x, y);
        robot.delay(500);
    }

    public void screenCapture() {
        Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage image = robot.createScreenCapture(rectangle);
        File file = new File("screen.png");
        try {
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
