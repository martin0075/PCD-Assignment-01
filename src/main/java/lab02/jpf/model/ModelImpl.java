package lab02.jpf.model;

import lab02.jpf.utils.ComputedFile;
import lab02.jpf.utils.ComputedFileImpl;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;

public class ModelImpl implements Model{
    private ComputedFile results;

    public void setup(int limit, int maxL, int numIntervals) {
        this.results = new ComputedFileImpl(limit, maxL, numIntervals);
    }

    @Override
    public ComputedFile getResult() {
        return results;
    }

}
