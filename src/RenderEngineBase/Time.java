package RenderEngineBase;

public class Time {
    public static double deltaTime = 0;
    public static long frameNumber = 0;
    public static long cycleTime;
    public static long nextCycleTime;
    //Record average fps over 60 frames
    private static double oldDeltaTimeSum = 0;
    private static double deltaTimeSum = 0;
    private static int deltaTimeNumRecords = 0;
    private static int avereragedOver = 60;

    public static void recordDT(){

        deltaTimeNumRecords++;
        if(deltaTimeNumRecords >= avereragedOver){
            oldDeltaTimeSum = deltaTimeSum;
            deltaTimeSum = 0;
            deltaTimeNumRecords = 0;
        }
        else{
            deltaTimeSum+=deltaTime;
        }


    }
    public static double getDTAverage(){
        if(oldDeltaTimeSum == 0){
            return deltaTimeSum/deltaTimeNumRecords;
        }
        return oldDeltaTimeSum/avereragedOver;
    }


}