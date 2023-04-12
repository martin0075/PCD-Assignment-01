package shared;

public enum GameCommand {
    start(1),
    pause(2),
    restart(3),
    quit(4),
    trashRow(5),
    none(6),
    updateScore(7),
    gameOver(8);

    private int numVal;

    GameCommand(int numVal) {
        this.numVal = numVal;
    }
}
