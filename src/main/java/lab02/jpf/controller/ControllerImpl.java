package lab02.jpf.controller;

import lab02.jpf.model.MasterThread;
import lab02.jpf.model.Model;
import lab02.jpf.utils.ComputedFile;
import lab02.jpf.utils.Pair;
import lab02.jpf.view.View;

import java.io.File;

public class ControllerImpl implements Controller{
    private final Model model;
    private final View view;
    private MasterThread masterThread;

    public ControllerImpl(Model model, View view){
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    @Override
    public void start(int numberOfWorkers, String path, int topN, int maxL, int numIntervals) {
        this.model.setup(topN, maxL, numIntervals);
        masterThread = new MasterThread(path, numberOfWorkers, this);
        masterThread.start();
    }

    @Override
    public void processEvent(Runnable runnable){
        new Thread(runnable).start();
    }

    @Override
    public ComputedFile getResult() {
        return this.model.getResult();
    }

    public void addResult(Pair<File, Integer> result) {
        this.model.getResult().add(result);
    }

    @Override
    public void stop() {
        this.masterThread.interrupt();
    }

    @Override
    public void updateResult() throws InterruptedException {
        this.view.resultsUpdated();
    }

    @Override
    public void endComputation() {
        this.view.endComputation();
    }
}
