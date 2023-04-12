import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class FigureDrawer {
    private TextColor grey = new TextColor.RGB(50, 50, 50);
    GameScreen gioco=new GameScreen();
    int trash_row_global=0; // mi indica quante righe spazzatura mi sono arrivate in tutto
    public FigureDrawer() throws IOException {
    }


    public boolean canDraw(Figure figure, TerminalSize panel, Screen screen, int xOffset, int yOffset, int random){
        TerminalSize gameSize=panel;
        //yOffset in caso di drop shape è yOffset+1
        //yOffset+=figure.getPezzo().length;
        //xOffset+=figure.getPezzo()[0].length;

        char[][]mat=new char[figure.ritornoPezzo().length][figure.ritornoPezzo()[0].length];
        for(int i=0;i< figure.ritornoPezzo().length;i++)
        {
            for(int j=0; j<figure.ritornoPezzo()[0].length;j++)
            {
                mat[i][j]=figure.ritornoPezzo()[i][j];
            }
        }
        int h=mat.length-1;

        for(int row = 0; row <= mat.length-1; row++){
            for(int col = 0; col < mat[row].length; col++) {


                    int xCoord = col * 2 + xOffset;// colonne
                    int yCoord =row+yOffset; // righe

                    if (xCoord < 0 || xCoord >= gameSize.getColumns()) {
                        return false;
                    }

                    if (xCoord < 0 || xCoord + 1 >= gameSize.getColumns()) {
                        return false;
                    }

                    if (yCoord > gameSize.getRows() ) { //+r
                        return false;
                    }
                    // check if there's already a block there
                    if(row< h) {
                        if(row==0&& mat.length>2)
                        {
                            if (mat[mat.length-1][col] == '\0') {
                                if(mat[mat.length-2][col] == '\0')
                                {
                                    if (StateScreen.employmentLocation(screen, xCoord, yOffset, panel)) {//
                                        return false;
                                    }
                                }
                                else {
                                    if (StateScreen.employmentLocation(screen, xCoord, yOffset + figure.ritornoPezzo().length - 2, panel)) {//
                                        return false;
                                    }
                                }
                            }
                        }
                        //else
                        else if (mat[row + 1][col] == '\0') {
                            if (StateScreen.employmentLocation(screen, xCoord, yOffset, panel)) {//
                                return false;
                            }
                        }
                            else {

                                if (StateScreen.employmentLocation(screen, xCoord, yOffset + figure.ritornoPezzo().length - 1, panel)) {//
                                    return false;
                                }
                            }

                    }
                    else {
                        if(row==h)
                        {
                            if(StateScreen.employmentLocation(screen, xCoord, yOffset, panel))
                            {
                                return false;
                            }
                        }
                        /*else
                        {
                            if (StateScreen.employmentLocation(screen, xCoord, yOffset + figure.ritornoPezzo().length, panel)) {//
                                return false;
                            }*/

                    }

            }

        }
        if(mat.length==1)
        {
            for(int row = 0; row < mat.length; row++){
                for(int col = 0; col < mat[row].length; col++) {


                    int xCoord = col * 2 + xOffset;// colonne
                    int yCoord =row+yOffset; // righe

                    if (xCoord < 0 || xCoord >= gameSize.getColumns()) {
                        return false;
                    }

                    if (xCoord < 0 || xCoord + 1 >= gameSize.getColumns()) {
                        return false;
                    }

                    if (yCoord > gameSize.getRows() ) { //+r
                        return false;
                    }
                    // check if there's already a block there
                    if (StateScreen.employmentLocation(screen, xCoord, yOffset, panel)) {//
                        return false;
                    }

                    else
                    {
                        if (StateScreen.employmentLocation(screen, xCoord, yOffset + figure.ritornoPezzo().length - 1, panel)) {//
                            return false;
                        }
                    }

                }

            }
        }
            return true;
    }

    public boolean canDropShape(Figure figure, TerminalSize panel, Screen screen, int xOffset, int yOffset, int random) {
        return canDraw(figure, panel, screen, xOffset, yOffset+1, random);
    }

    public boolean canGoRight(Figure figure, TerminalSize panel, Screen screen, int xOffset, int yOffset, int random) {
        return canDraw(figure, panel, screen, xOffset+2, yOffset, random);
    }

    public boolean canGoLeft(Figure figure, TerminalSize panel, Screen screen, int xOffset, int yOffset, int random) {
        return canDraw(figure, panel, screen, xOffset-2, yOffset, random);
    }



    /*
    * CONTROLLO CANROTATE OK, da sistemare il disegnare il pezzo ruotato che non va
    *
    * */
    public boolean canRotate(Figure figure, TerminalSize panel, Screen screen, int xOffset, int yOffset, int random) {

        Figure figuretmp=new Figure();
        char[][] tmp=figuretmp.ruota(figure.ritornoPezzo());
        if(canDraw(figure, panel, screen, xOffset, yOffset, random))
            figuretmp.setPezzo(tmp);
        return canDraw(figuretmp, panel, screen, xOffset, yOffset, random);
    }

    public void fillBackground( TerminalSize panel, Screen screen) throws IOException {
        TerminalSize gameSize = panel;
        for(int row = 0; row < gameSize.getRows(); row++) {
            for(int col = 0; col < gameSize.getColumns() / 2; col++) {

                TextCharacter block = null;
                if(((row % 2) ^ (col % 2)) == 0) {
                    block = new TextCharacter('0', grey, grey, SGR.FRAKTUR);
                } else {
                    block = new TextCharacter('0', grey, grey, SGR.FRAKTUR);
                }

                screen.setCharacter(col * 2, row, block);
                screen.setCharacter(col * 2 + 1, row, block);
            }
            screen.refresh();
        }
    }

    public void DrawShape(Figure figure, Screen screen, int xOffset, int yOffset) throws IOException{

        char[][]mat=figure.ritornoPezzo();
        for(int row = 0; row < mat.length; row++){
            for(int col = 0; col < mat[row].length; col++) {
                if(mat[row][col]=='1'){
                    TextCharacter block = new TextCharacter('1', figure.getColor(), figure.getColor(),SGR.FRAKTUR);
                    screen.setCharacter(col*2+xOffset, row+yOffset, block);
                    screen.setCharacter(col*2+1+xOffset, row+yOffset, block);
                }

            }

        }
        screen.refresh();
    }


    //METODO PER DISEGNARE PEZZO RUOTATO
    // CONTROLLARE COME DISEGNARE IL PEZZO RUOTATO

    public void UndrawShape(Figure figure, Screen screen, int xOffset, int yOffset) throws IOException{
        char[][]mat=figure.ritornoPezzo();
        for(int row = 0; row < mat.length; row++){
            for(int col = 0; col < mat[row].length; col++) {
                if(mat[row][col]=='1'){
                    TextCharacter block = null;
                    if(((row+yOffset % 2) ^ ((col+xOffset) % 2)) == 0) {//
                        block = new TextCharacter('0', grey, grey, SGR.FRAKTUR);
                    } else {
                        block = new TextCharacter('0', grey, grey, SGR.FRAKTUR);
                    }

                    screen.setCharacter(col*2+xOffset, row+yOffset, block);
                    screen.setCharacter(col*2+1+xOffset, row+yOffset, block);
                }

            }

        }
        screen.refresh();
    }


    public void removeRow(Screen screen, int row) {
        TerminalSize gameSize = screen.getTerminalSize();
        TextCharacter textChar;
        for(int currentRow = row; row > 0; row--) {
            for(int col = 0; col < gameSize.getColumns(); col++) {
                if(currentRow>1)
                {
                    textChar = screen.getFrontCharacter(col,currentRow - 1);
                }
                else {
                    textChar = screen.getFrontCharacter(col,0);
                }

                screen.setCharacter(col, row, textChar);
            }
        }
    }



    /*
    *   METODO PER INSERIMENTO DELLE RIGHE SPAZZATURA NEL NOSTRO CAMPO DA GIOCO
    *   PRENDO IN INPUT trash_row CHE SONO LE RIGHE SPAZZATURA CHE MI VERRANNO INVIATE DURANTE
    *   LE PARTITE
    *
    * */
    public void trashRow(Screen screen, TerminalSize terminalSize, Terminal terminal, int trash_row)
    {

        TextCharacter block_trash=new TextCharacter('2', grey, grey);


        if(controllo_fine_spazzatura(screen,terminalSize,trash_row))
        {
            gioco.EndGame();
        }
        else
        {
            for(int i= trash_row;i< terminalSize.getRows()-trash_row_global-trash_row;i++)
            {
                for(int k=0;k< terminalSize.getColumns();k++)
                {
                    for(int z=0;z<trash_row;z++)
                    {
                        screen.setCharacter(i-trash_row,k,screen.getBackCharacter(i,k));

                    }
                }
            }

            for(int m= terminalSize.getRows()-trash_row_global-trash_row;m<terminalSize.getRows()-trash_row_global;m++)
            {
                for(int k=0;k< terminalSize.getColumns();k++)
                {
                    screen.setCharacter(m,k,block_trash);
                }
            }
            trash_row_global+=trash_row;
        }
    }




    /*
    * METODO CHE MI SERVE PER CAPIRE SE CON L'AGGIUNTA DI RIGHE SPAZZATURA POSSO PERDERE LA PARTITA O NO
    *
    * */
    public boolean controllo_fine_spazzatura(Screen screen,TerminalSize terminalSize, int trash_row)
    {
        int i=0;
        for(int k=0; k< terminalSize.getColumns();k++)
        {
            if(screen.getBackCharacter(i+trash_row,k).equals('1'))
                return true;
            else
                return false;
        }
        return false;
    }


}
