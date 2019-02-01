package RenderEngineBase;

import java.awt.*;
import java.awt.image.VolatileImage;
import java.util.*;

public class Renderer {
    private Application application;

    ArrayList<String> debugLineColor = new ArrayList<String>();

    int targetFPS = 60;
    double maxDelay=1.0/targetFPS;




    public Renderer(Application application){
        this.application = application;
    }
    public void render(Scene scene,Time time){

        Graphics g = this.application.getStrategy().getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, application.getWidth(), application.getHeight());
        g.setColor(Color.ORANGE);
        g.drawString(Double.toString(1/time.getDTAverage()),10,10);

        g.fillRect((int)scene.lineX, (int)scene.lineY, 100, 100); // arbitrary rendering logic
        g.dispose();
        this.application.getStrategy().show();
    }





}