package RenderEngineBase;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class RenderEngine {
    private JFrame frame;
    private Canvas canvas;
    //private Scene scene;
    private Renderer renderer;
    private static final int TARGET_FPS = 60;
    private static final int FRAME_DELAY = 1000/TARGET_FPS;
    public RenderEngine() {
        // Create window frame
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);

        canvas = new Canvas();
        frame.getContentPane().add(canvas);


        new Application(frame, canvas, this);
        new KeyInput(canvas);

        // Store the scene
        //loadScene(scene);

        // Create the renderer
        renderer = new Renderer(this);

        frame.setVisible(true);

        Thread gameThread = new Thread(new GameLoop(canvas));
        gameThread.setPriority(Thread.MIN_PRIORITY);
        gameThread.start(); // start Game processing.


    }

    public Canvas getCanvas() {
        return canvas;
    }

    public JFrame getFrame() {
        return frame;
    }

//    public Scene getScene() {
//        return scene;
//    }

    public Renderer getRenderer() {
        return renderer;
    }

//    public void loadScene(Scene scene) {
//        this.scene = scene;
//        scene.Load(this);
//    }


    private static class GameLoop implements Runnable {

        boolean isRunning;
        double lineX;
        double lineY;
        double xVel = 0;
        double yVel = 0;
        double size = 100;
        java.awt.Canvas gui;


        public GameLoop(Canvas canvas) {
            gui = canvas;
            isRunning = true;
            lineX = Application.getWidth()/2 - size/2;
            lineY = Application.getHeight()/2 - size/2;
        }

        public void run() {
            Time.cycleTime = System.currentTimeMillis();
            Time.nextCycleTime = Time.cycleTime;
            BufferCapabilities bc = new BufferCapabilities(new ImageCapabilities(true),new ImageCapabilities(true),null);
            try{
                gui.createBufferStrategy(2,bc);
            }
            catch(java.awt.AWTException e){
                System.out.println("bad buffer capabilities");
                gui.createBufferStrategy(2);
            }
            BufferStrategy strategy = gui.getBufferStrategy();
            // Game Loop
            while (isRunning) {

                updateGameState();

                updateGUI(strategy);

                syncFramerate();
            }
        }

        private void updateGameState() {
            double acceleration = 20;
            //lineX+=Time.deltaTime*15;
            if(Input.getKey("Up")){
                yVel-=Time.deltaTime*acceleration;
            }
            if(Input.getKey("Down")){
                yVel+=Time.deltaTime*acceleration;
            }
            if(Input.getKey("Left")){
                xVel-=Time.deltaTime*acceleration;
            }
            if(Input.getKey("Right")){
                xVel+=Time.deltaTime*acceleration;
            }
            lineX += Time.deltaTime * xVel*10;
            lineY += Time.deltaTime * yVel*10;
            xVel = xVel;
            yVel = yVel;
            if(lineX < 0){
                lineX = 0;
                xVel*=-1;
            }
            else if(lineX+ size > Application.getWidth()){
                lineX = Application.getWidth()-size;
                xVel*=-1;
            }
            if(lineY < 0){
                lineY = 0;
                yVel *=-1;
            }
            else if(lineY+ size > Application.getHeight()){
                lineY = Application.getHeight()-size;
                yVel *=-1;
            }
        }

        private void updateGUI(BufferStrategy strategy) {
            Graphics g = strategy.getDrawGraphics();

            g.setColor(Color.BLACK);
            g.fillRect(0, 0, gui.getWidth(), gui.getHeight());
            g.setColor(Color.ORANGE);
            g.drawString(Double.toString(1/Time.getDTAverage()),10,10);

            g.fillRect((int)lineX, (int)lineY, 100, 100); // arbitrary rendering logic
            g.dispose();
            strategy.show();
        }

        private void syncFramerate() {
            Time.nextCycleTime = Time.cycleTime + FRAME_DELAY;
            long difference = Time.nextCycleTime - System.currentTimeMillis();

            try {
                Thread.sleep(Math.max(0, difference));
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }

            Time.deltaTime = (FRAME_DELAY + (double)Math.max(-difference,0))/1000;
            Time.recordDT();

            Time.cycleTime = System.currentTimeMillis();

        }

    }

}
