package RenderEngineBase;

public class Game {
    private Scene[] scenes;
    private Application application;
    private Scene currentScene;
    private Thread gameThread;
    public Game(){

        this.application = new Application( this);
        gameThread = new Thread(new GameLoop(this,this.application));
        gameThread.setPriority(Thread.MIN_PRIORITY);
        // start Game.

        this.scenes =new Scene[1];
        this.scenes[0] = new Scene();

    }
    public void start(){
        this.currentScene = this.scenes[0];
        gameThread.start();

    }
    public Scene getCurrentScene(){
        return this.currentScene;
    }
}
