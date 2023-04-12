import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.sun.jdi.InvocationException;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KeyPressTester extends  Thread{
    private static final int POLL_INTERVAL = 100/120; // poll 120 times/sec

    private TerminalScreen screen;
    private List<KeyStroke> keyStrokes;

    public KeyPressTester(TerminalScreen screen) {
        this.screen = screen;
        this.keyStrokes = new ArrayList<>();
    }

    public List<KeyStroke> getKeyStrokes() {
        List<KeyStroke> tmp = new ArrayList<>();
        tmp.addAll(keyStrokes);
        this.keyStrokes.clear();
        return tmp;
    }
    

    public void run() {
        while(true) {

            // try to poll key stroke
            try {
                KeyStroke keyStroke = screen.pollInput();
                if(keyStroke != null) {
                    this.keyStrokes.add(keyStroke);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // try to sleep a bit
            try {
                Thread.sleep(POLL_INTERVAL);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }



        }
    }
}
