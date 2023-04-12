package lab02.jpf.view;

import lab02.jpf.controller.Controller;

public interface View {
    void setController(Controller controller);

    void resultsUpdated() throws InterruptedException;

    void endComputation();
}
