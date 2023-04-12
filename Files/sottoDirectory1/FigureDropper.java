public class FigureDropper extends Thread{
    private boolean dropFigure = false;
    private Integer delay = Integer.MAX_VALUE;

    public boolean getDropFigure() {
        if(this.dropFigure) {
            this.dropFigure = false;
            return true;
        }
        return this.dropFigure;
    }
    public void setDropFigure(boolean dropFigure) {
        this.dropFigure = dropFigure;
    }

    public Integer getDelay() {
        return delay;
    }
    public void setDelay(Integer delay) {
        this.delay = delay;
    }


    public void run() {
        while(true) {
            try {
                Thread.sleep(this.delay);
            } catch(InterruptedException e) {

                e.printStackTrace();
            }
            this.dropFigure = true;
        }
    }
}
