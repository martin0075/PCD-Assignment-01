package lab02.jpf.model;

import lab02.jpf.utils.ComputedFile;

public interface Model {
    ComputedFile getResult();
    void setup(int limit, int maxL, int numIntervals);

}
