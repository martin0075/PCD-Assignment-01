import java.io.IOException;
import java.util.*;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class GameScreen {
    /*
    * player 1 siamo sempre noi, gli altri player verranno identificati con opponent 1, opponent 2, opponent 3
    * e così via
    *
    * */
    private Giocatore player1;
    private Terminal terminal;
    private Screen screen;
    private Panel panel;
    private Window window;
    private FigureDrawer drawer;
    private Figure figure;
    private int x0,y0;
    private TerminalSize terminalSize;
    private int brickDropDelay = 1000;
    private int fpsDelay=1000/60;
    private int num=0;
    private Random rnd;
    private int x0_nonruotato;
    private int y0_nonruotato;
    private Figure figura_pre;

    private boolean ruota=false;


    public GameScreen() throws IOException{
        /*terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(70,30)).createTerminal();
        screen = new TerminalScreen(terminal);
        panel=new Panel();
        window = new BasicWindow();
        figure=new Figure();
        drawer=new FigureDrawer();
        rnd=new Random();*/

    }

    public void Start(String username) throws IOException, InterruptedException {

        terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(70,30)).createTerminal();
        screen = new TerminalScreen(terminal);
        panel=new Panel();
        window = new BasicWindow();
        figure=new Figure();
        drawer=new FigureDrawer();
        rnd=new Random();
        player1=new Giocatore(username,0);

        screen.startScreen();
        terminalSize=new TerminalSize(24,24);

        drawer.fillBackground(terminalSize, screen);

        x0=terminalSize.getColumns()/2;
        y0=0;


        boolean gameOver=false;
        num=7;
        //num=rnd.nextInt(8);
        figure.getPezzo(num);
        drawer.fillBackground(terminalSize,screen);
        drawer.DrawShape(figure,screen,x0,y0);

        FigureDropper FigureDropper = new FigureDropper();
        FigureDropper.setDelay(brickDropDelay);
        FigureDropper.start();

        KeyPressTester keyInput = new KeyPressTester((TerminalScreen) screen);
        keyInput.start();





        while(!gameOver){
            Thread.sleep(fpsDelay); // delay

            boolean nextShape = false;
            boolean shapeMoved = false;
            ruota=false;
            List<KeyStroke> keyStrokes = keyInput.getKeyStrokes();

            for(KeyStroke key : keyStrokes) {
                drawer.UndrawShape(figure, screen, x0, y0);
                keyPressed(key);
                if (!ruota) {
                    if (!drawer.canDropShape(figure, terminalSize, screen, x0, y0, num)) {
                        nextShape = true;
                    }
                }
                drawer.DrawShape(figure,screen,x0,y0);
                shapeMoved = true;
            }

            if(FigureDropper.getDropFigure()) {
                drawer.UndrawShape(figure, screen, x0, y0);
                if(drawer.canDropShape(figure, terminalSize, screen, x0, y0, num)){
                    y0++;
                } else {
                    nextShape = true;

                }
                drawer.DrawShape(figure, screen, x0, y0);
                shapeMoved = true;
            }

            // if ready for next shape
            if(nextShape) {
                //TextCharacter test=screen.getBackCharacter(0,2)
                processCompletedRows();
                x0 = terminalSize.getColumns() / 2;//2
                y0 = 0;
                num=7;//rnd.nextInt(8);
                figure.getPezzo(num);

            }

            /*if(shapeMoved) {

            }*/

            /*figure=new Figure();
            drawer.DrawShape(figure,screen,x0,y0);
            gameOver=true;*/
        }





        //window.setComponent(panel);
        //Create gui and start gui
        //MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
        //gui.addWindowAndWait(window);
    }

    public void keyPressed(KeyStroke e) throws IOException {
        figura_pre=new Figure();

        if(e!=null)
        {
            switch (e.getKeyType()) {
                case ArrowUp://tasto freccia in alto mi permette di ruotare il pezzo verso sinistra
                    //figura_pre=figure;
                    //drawer.UndrawShape(figure, screen, x0, y0);

                    figura_pre.setPezzo(figure.ritornoPezzo());
                    if(drawer.canRotate(figure,terminalSize,screen,x0,y0,num)){
                            figure.setPezzo(figure.ruota(figure.ritornoPezzo()));
                        try {
                            drawer.UndrawShape(figura_pre,screen,x0,y0);
                            drawer.DrawShape(figure, screen, x0, y0);

                        }catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        ruota=true;
                    }
                    break;
                case ArrowDown:
                    if(drawer.canDropShape(figure,terminalSize,screen,x0,y0, num)){
                        y0++;
                    }
                    break;
                case ArrowLeft:
                    if(drawer.canGoLeft(figure,terminalSize,screen,x0,y0, num)){
                            x0=x0-2;
                    }
                    break;
                case ArrowRight:
                    if(drawer.canGoRight(figure, terminalSize,screen,x0,y0, num)) {
                        x0=x0+2;
                    }
                    break;
            }
        }

    }


    // Controlla che la riga sia piena invocando il metodo che va effettivamente a controllare se piena o no
    public void processCompletedRows()
    {
        int[] posizione=StateScreen.isRowFull(terminalSize,screen,player1.punteggio);
        //int n_row_to_delete=StateScreen.get_n_row_to_delete();
        for(int row=posizione[0];row>terminalSize.getRows()-posizione[1];row--)
        {
            drawer.removeRow(screen, row);
        }
        player1.setPunteggio(StateScreen.punteggio_player()); //mi setta il punteggio del player

    }

    public void EndGame()
    {
    System.out.println("Hai perso la partita con un punteggio di...");
    /*
    *  DA COMPLETARE CON LA CONCLUSIONE DEL GIOCO RIPORTANDO ALLA SCHERMATA PRINCIPALE DEL GIOCO
    *
    * */
}










}
