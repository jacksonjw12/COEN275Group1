package RenderEngineBase;

import java.awt.*;
import java.awt.image.VolatileImage;
import java.util.*;

public class Renderer {
    private RenderEngine engine;
    //private Camera camera;

   // ArrayList<Vector3> debugLineStart = new ArrayList<Vector3>();
    //ArrayList<Vector3> debugLineEnd = new ArrayList<Vector3>();
    ArrayList<String> debugLineColor = new ArrayList<String>();
    //ArrayList<>
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsConfiguration gc = ge.getDefaultScreenDevice().getDefaultConfiguration();
    VolatileImage vImage = gc.createCompatibleVolatileImage(Application.getWidth(), Application.getHeight());
    Graphics2D context = vImage.createGraphics();
    int targetFPS = 60;
    double maxDelay=1.0/targetFPS;



    public Renderer(RenderEngine engine) {
        this.engine = engine;
    }

    public void render(Graphics graphics) {
        Time.frameNumber++;
        //System.out.println(Time.frameNumber);
        // Set the time at the very start of the frame
        long lastLoopTime = System.nanoTime();

        // Initialize frame and context





        //VolatileImage frame = engine.getFrame().createVolatileImage(Application.getWidth(), Application.getHeight());

        // Update - Run game logic
        //engine.getScene().callUpdate();

        // LateUpdate - Run additional game logic
        //engine.getScene().callLateUpdate();

        // Reset input states
        //Input.resetScrollRotation();
        //Input.resetMouseMovement();
        //engine.getCanvas().recenterMouse();
        //Input.resetKeysDown();

        // Draw sky background
//        if (engine.getScene().sky != null) {
//            engine.getScene().sky.drawBackground(context, camera);
//        }

        // Render the camera view to the context
        drawCameraView();

        // Render GUI elements
        //drawGUIElements();

        // Paint frame to canvas
        graphics.drawImage(vImage, 0, 0, engine.getFrame());
        //frame.flush();
        // Repaint the window which then calls the next frame
        //System.out.println("delay1: " + (System.nanoTime() - lastLoopTime) / 1000000000.0);
        //System.out.println(( maxDelay));
        // Slow down if frames are rendering too fast
        //System.out.println(((System.nanoTime() - lastLoopTime) / 1000000000.0 < maxDelay));
        engine.getFrame().repaint();

        if ((System.nanoTime() - lastLoopTime) / 1000000000.0 < maxDelay) {//30 fps
            //System.out.println("delay: " + (int)Math.max(100*(maxDelay - (System.nanoTime() - lastLoopTime) / 1000000), 1));
            try {
                Thread.sleep((long)Math.max(1000*(maxDelay - (System.nanoTime() - lastLoopTime) / 1000000), 1));//Thread.sleep(Math.max( ((int)targetFPS-1) - (System.nanoTime() - lastLoopTime) / 100000, 1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Set deltaTime as the length of time in seconds that this frame took
        Time.deltaTime = (System.nanoTime() - lastLoopTime) / 1000000000.0;
        Time.recordDT();
    }
    public double xPos = 0;
    public void drawCameraView() {

        context.setColor(Color.white);
        context.fillRect(0,0,Application.getWidth(),Application.getHeight());
        context.setColor(Color.black);

        context.drawString(Double.toString(1000 /Time.getDTAverage()),10,10);

        int xSpeed = 20;
        xPos += xSpeed*Time.deltaTime*10;
        context.fillRect((int)xPos %Application.getWidth(),100,100,100);


    }

//    public void addDebugLine(Vector3 start, Vector3 end, String color) {
//        debugLineStart.add(start);
//        debugLineEnd.add(end);
//        debugLineColor.add(color);
//    }
//
//    public void clearDebugLines() {
//        debugLineStart.clear();
//        debugLineEnd.clear();
//        debugLineColor.clear();
//    }


}