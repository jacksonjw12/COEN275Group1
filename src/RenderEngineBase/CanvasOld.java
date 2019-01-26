package RenderEngineBase;

import javax.swing.*;
import java.awt.*;

public class CanvasOld extends JPanel {
//    private MouseInput mouseListener;
    private RenderEngine engine;
    //private boolean recenterMouse = false;

    public CanvasOld(RenderEngine engine) {
        this.engine = engine;

        // Add input listeners
//        mouseListener = new MouseInput(this);
//        new KeyInput(this);
//        new MouseWheelInput(this);
//        new MouseClickInput(this);

        // Set up the frame
        setFocusable(true);

        // Initiate rendering
        repaint();
    }

    public void paintComponent(Graphics graphics) {
        engine.getRenderer().render(graphics);
    }

    public void recenterMouse() {
//        if (recenterMouse) {
//            mouseListener.recenterMouse();
//        }
    }

    public void setRecenterMouse(boolean recenter) {
        //recenterMouse = recenter;
    }
}