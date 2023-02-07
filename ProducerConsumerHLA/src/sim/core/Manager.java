package sim.core;

import java.util.LinkedList;

public class Manager {
    private double startSimTime = 0.0;
    private double stopSimTime = Double.MAX_VALUE;
    private double currentSimTime = startSimTime;
    private double timeStep = 1.0;
    private static Manager simMngr; //Singleton
    private boolean simulationStarted = false;
    // Lista workerów, którzy są składowymi kroku symulacji
    private LinkedList<SimStep> simStepWorkers = new LinkedList<>();

    public static Manager getInstance(double startSimTime, double timeStep){
        if (simMngr == null) {
            simMngr = new Manager(startSimTime, timeStep);
        }
        return simMngr;
    }

    public double getTimeStep() {
        return timeStep;
    }

    private Manager(double startSimTime, double timeStep){
        if (startSimTime>0.0)
            this.startSimTime = startSimTime;
        this.timeStep = timeStep;
    }

    public void registerSimStep(SimStep step){
        if (step != null)
            simStepWorkers.add(step);
    }

    public final double simTime(){
        return  currentSimTime;
    }

    public final void stopSimulation(){
        simulationStarted = false;
    }

    public final void startSimulation() throws Exception {
        if(simulationStarted == true)
            throw new Exception("Symulacja trwa, poczekaj na zakończenie symulacji");
        simulationStarted = true;
        currentSimTime = startSimTime;
        runStepByStep();
    }

    public void setEndSimTime(double stopSimTime){
        this.stopSimTime = stopSimTime;
    }

    private void runStepByStep(){
        while(simulationStarted && currentSimTime < stopSimTime){//tu = wczesniej dawalem
            for (SimStep worker: simStepWorkers){
                worker.stateChange();
            }
            currentSimTime = Double.sum(currentSimTime,timeStep);

        }
        stopSimulation();
    }







}
