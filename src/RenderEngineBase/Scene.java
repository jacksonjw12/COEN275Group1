package RenderEngineBase;

public class Scene {
    public double lineX;//for test
    public double lineY;//for test
    double xVel = 0;
    double yVel = 0;
    double size = 100;
    public Scene(){

    }
    public void update(Time time,Input input,Application application){

        double acceleration = 20;
        //lineX+=Time.deltaTime*15;
        if(input.getKey("Up")){
            yVel-=time.deltaTime*acceleration;
        }
        if(input.getKey("Down")){
            yVel+=time.deltaTime*acceleration;
        }
        if(input.getKey("Left")){
            xVel-=time.deltaTime*acceleration;
        }
        if(input.getKey("Right")){
            xVel+=time.deltaTime*acceleration;
        }
        lineX += time.deltaTime * xVel*10;
        lineY += time.deltaTime * yVel*10;
        xVel = xVel;
        yVel = yVel;
        if(lineX < 0){
            lineX = 0;
            xVel*=-1;
        }
        else if(lineX+ size > application.getWidth()){
            lineX = application.getWidth()-size;
            xVel*=-1;
        }
        if(lineY < 0){
            lineY = 0;
            yVel *=-1;
        }
        else if(lineY+ size > application.getHeight()){
            lineY = application.getHeight()-size;
            yVel *=-1;
        }

    }


}
