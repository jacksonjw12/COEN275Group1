package RenderEngineBase;

public class CubeScene extends Scene {

    public CubeScene(){
        super("Cube Scene");
       // super.addGameObject(new Model("cow.obj"));
        //super.getGameObjects().get(0).setScale(new Vector3(.2,.2,.2));

        for(int i = 0; i< 3; i++){
            for(int j = 0; j< 3; j++){
                for(int k = 0; k<3; k++){
                    Model m = new Model("cube.obj");
                    m.setPosition(new Vector3(i*2,j*2,k*2));
                    super.addGameObject(m);
                }
            }
        }
        //super.addGameObject(new Model("cube.obj"));
    }


    public void flyCamera(Time time, Input input){
        GameObject camera = super.getActiveCamera();
        int speed = 10;
        int inertia = 100;
        Vector3 accel = new Vector3(0,0,0);
        Vector3 rot = new Vector3(0,0,0);
        if(input.getKey("W")){
            accel.z += .1;
        }
        if(input.getKey("S")){
            accel.z -= .1;
        }
        if(input.getKey("A")){
            accel.x -= .1;
        }
        if(input.getKey("D")){
            accel.x += .1;
        }
        if(input.getKey("Q")){
            accel.y += .1;
        }
        if(input.getKey("E")){
            accel.y -= .1;
        }
        if(input.getKey("Up")){
            rot.x -= .1;
        }
        if(input.getKey("Down")){
            rot.x += .1;
        }
        if(input.getKey("Left")){
            rot.y -= .1;
        }
        if(input.getKey("Right")){
            rot.y += .1;
        }
        rot.scale(.25 );
        //accel.normalize();
        //System.out.println(accel);
//        accel.scale(inertia*time.deltaTime);

        accel.scale(inertia*time.deltaTime);
        Vector3 velocity = camera.getVelocity();
        velocity.add(accel);
//        velocity.set(accel);
        velocity.enforceAbsoluteValue(3);
        //System.out.println(time.deltaTime);

        Vector3 vel = camera.getVelocity().getCopy();
        vel.scale(time.deltaTime);

        vel.rotateMagnitude(camera.getRotation());
        vel = camera.getRotation().getRotatedPoint(vel);

        camera.addPosition(vel);
        camera.getVelocity().scale(.8);

        camera.addRotation(rot);

    }

    public void update(Time time,Input input,Application application){

        flyCamera(time,input);
        //super.getGameObjects().get(0).setRotation(new Vector3(.1*System.currentTimeMillis()/200,.1*System.currentTimeMillis()/200,.1*System.currentTimeMillis()/200));
        for(int i = 0; i< super.getGameObjects().size(); i++){
            super.getGameObjects().get(i).setRotation(new Vector3(.1*System.currentTimeMillis()/200,.1*System.currentTimeMillis()/200,.1*System.currentTimeMillis()/200));

        }
    }
}
