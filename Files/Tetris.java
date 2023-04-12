import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Tetris {
    public static void main(String[]args) throws IOException, InterruptedException {
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(70,30)).createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();

        Panel panel=new Panel().setPreferredSize(new TerminalSize(40,20)).setPosition(new TerminalPosition(20,5));


        panel.addComponent(new Label("Username"));
        TextBox txtUsername=new TextBox().setPreferredSize(new TerminalSize(15,1));
        panel.addComponent(txtUsername);

        GameScreen GameScreen=new GameScreen();

        panel.addComponent(new Label(" "));

        Button button=new Button("Start", new Runnable() {
            @Override
            public void run() {
                try {

                    GameScreen.Start(txtUsername.getText());

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).setPreferredSize(new TerminalSize(18,10));
        panel.addComponent(button);

        BasicWindow window = new BasicWindow();
        window.setComponent(panel);
        window.setPosition(new TerminalPosition(20,5));

        // Create gui and start gui
        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLACK));
        gui.addWindowAndWait(window);

    }
}