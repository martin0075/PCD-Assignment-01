import java.io.IOException;
import java.util.Random;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class GameScreen {
    private Terminal terminal;
    private Screen screen;
    private Panel panel;
    private Window window;
    private FigureDrawer drawer;
    private Figure figure;
    private int x0,y0;

    public GameScreen() throws IOException{
        terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(70,30)).createTerminal();
        screen = new TerminalScreen(terminal);
        panel=new Panel();
        window = new BasicWindow();

        drawer=new FigureDrawer();
    }

    public void Start() throws IOException {
        screen.startScreen();
        TerminalSize terminalSize=new TerminalSize(19,24);
        panel.setPreferredSize(terminalSize);

        drawer.fillBackground(panel, screen);

        x0=terminalSize.getColumns()/4;
        y0=0;


        boolean gameOver=false;
        while(!gameOver){
            figure=new Figure();
            drawer.DrawShape(figure,screen,x0,y0);
        }





        window.setComponent(panel);
        // Create gui and start gui
        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
        gui.addWindowAndWait(window);
    }

    public int TipoPezzo (){
        Random random=new Random();
        int num= random.nextInt(7);
        return num;
    }













}
