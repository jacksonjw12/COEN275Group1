package RenderEngineBase;

import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferStrategy;

public class Application {
    private JFrame frame;
    private Canvas canvas;
    private Game game;
    private Input input;
    private boolean notFullscreen = true;
    private boolean cursorVisible = true;
    private BufferStrategy strategy;
    public static final int TARGET_FPS = 60;
    public static final int FRAME_DELAY = 1000/TARGET_FPS;

    public Application(Game game) {

        this.game = game;

        frame = new JFrame();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);





        canvas = new Canvas();
        frame.getContentPane().add(canvas);
        input = new Input(canvas);
        frame.getContentPane().requestFocus();




        BufferCapabilities bc = new BufferCapabilities(new ImageCapabilities(true),new ImageCapabilities(true),null);
        try{
            this.canvas.createBufferStrategy(2,bc);
        }
        catch(java.awt.AWTException e){
            System.out.println("bad buffer capabilities");
            this.canvas.createBufferStrategy(2);
        }
        strategy = this.canvas.getBufferStrategy();



        frame.setVisible(true);








    }

    public BufferStrategy getStrategy(){
        return this.strategy;
    }
    public Input getInput(){
        return this.input;
    }

//    public static void hideCursor() {
//        Toolkit toolkit = Toolkit.getDefaultToolkit();
//        frame.setCursor(toolkit.createCustomCursor(toolkit.getImage(""), new Point(frame.getX(), frame.getY()), "img"));
//        canvas.setRecenterMouse(true);
//        cursorVisible = false;
//    }
//
//    public static void showCursor() {
//        frame.setCursor(Cursor.getDefaultCursor());
//        canvas.setRecenterMouse(false);
//        cursorVisible = true;
//    }

    public boolean isCursorVisible() {
        return cursorVisible;
    }

    public void quit() {
        // Stop showing window
        frame.setVisible(false);

        // Destroy the window
        frame.dispose();

        // End the application process
        System.exit(0);
    }

    public void goFullscreen() {
        if (notFullscreen) {
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setUndecorated(true);
            notFullscreen = false;
        }
    }

    public void setResolution(int width, int height) {
        frame.setSize(width, height);
    }

    public int getWidth() {
        return frame.getWidth();
    }

    public int getHeight() {
        return frame.getHeight();
    }
}
