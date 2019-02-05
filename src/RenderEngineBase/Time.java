package RenderEngineBase;

public class Time {
    public double deltaTime = 0;
    public long frameNumber = 0;
    public long cycleTime;
    public long nextCycleTime;
    //Record average fps over 60 frames
    private double oldDeltaTimeSum = 0;
    private double deltaTimeSum = 0;
    private int deltaTimeNumRecords = 0;
    private int averagedOver = 60;


    public void recordDT(){
        frameNumber++;

        deltaTimeNumRecords++;
        if(deltaTimeNumRecords >= averagedOver){
            oldDeltaTimeSum = deltaTimeSum;
            deltaTimeSum = 0;
            deltaTimeNumRecords = 0;
        }
        else{
            deltaTimeSum+=deltaTime;
        }


    }
    public double getDTAverage(){
        if(oldDeltaTimeSum == 0){
            if(deltaTime == 0){
                return 1;
            }
            else{
                return deltaTime;
            }

        }
        return oldDeltaTimeSum/averagedOver;
    }


}