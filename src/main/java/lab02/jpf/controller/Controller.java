package lab02.jpf.controller;

import lab02.jpf.utils.ComputedFile;
import lab02.jpf.utils.Pair;

import java.io.File;

public interface Controller {
    void start(int numberOfWorkers, String path, int topN, int maxL, int numIntervals);
    void processEvent(Runnable runnable);
    ComputedFile getResult();
    void addResult(Pair<File, Integer> result);

    void stop();

    void updateResult() throws InterruptedException;
    void endComputation();
}
