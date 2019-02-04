package RenderEngineBase;


public class GameLoop implements Runnable{
    private Game game;
    private Application application;
    public Time time;
    private Renderer renderer;
    public boolean isRunning = false;
    public GameLoop(Game game, Application application){
        this.time = new Time();
        this.game = game;
        this.application = application;
        this.renderer = new Renderer(this.application);
    }
    public void run() {
        isRunning = true;
        time.cycleTime = System.currentTimeMillis();
        time.nextCycleTime = time.cycleTime;

        // Game Loop
        while (isRunning) {
            //updateGameState();
            game.getCurrentScene().update(time,application.getInput(),application);
            renderer.render(game.getCurrentScene(),time);
            //updateGUI(strategy);

            syncFramerate();
        }

    }
    private void syncFramerate() {
        time.nextCycleTime = time.cycleTime + application.FRAME_DELAY;
        long difference = time.nextCycleTime - System.currentTimeMillis();

        try {
            Thread.sleep(Math.max(0, difference));
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }

        time.deltaTime = (application.FRAME_DELAY + (double)Math.max(-difference,0))/1000;
        time.recordDT();

        time.cycleTime = System.currentTimeMillis();

    }

}
