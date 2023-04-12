import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import org.w3c.dom.Text;

public class StateScreen {
    private static int punteggio;

    private static int n_riga=0;
    private static TextColor bgColor1 = new TextColor.RGB(0, 0, 0);
    private static TextColor bgColor2 = new TextColor.RGB(50, 50, 50);
    private static int n_row=0;
    /*
    * EMPLOYMENT LOCATION DA RIVEDERE E PROBABILMENTE DA SOSTITUIRE
    * Probabilmente bisogna andare a rivedere il metodo andando ad utilizzare i caratteri invece dei colori
    * in modo da verificare anche la presenza di righe spazzatura che andremo a segnare con il carattere 2
    * e che non devono essere toccate in quanto fisse sul campo da gioco
    *
    * Idea: andiamo a controllare i caratteri e non i colori, andando a controllare che la posizione successiva
    * sia 1 o 0, se 1 si deve fermare altrimenti deve continuare fino a che non raggiunge il fondo oppure non
    * trova un altro pezzo che presenta il valore 1 a cui si deve fermare
    *
    * */
    public static boolean employmentLocation(Screen screen, int x, int y,TerminalSize terminalSize){
        if(x< terminalSize.getColumns()||y< terminalSize.getRows()) {

            //RIGUARDA LE COLONNE
            if (screen.getBackCharacter(x, y) != null) {//|| screen.getBackCharacter(x+1, y)!=null
                char carattere = screen.getBackCharacter(x, y).getCharacter();
                //char carattere2 = screen.getBackCharacter(x + 1, y).getCharacter();
                if ( carattere != '0')//carattere2 != '0' ||
                    return true;
            }
            // check front character
            TextCharacter frontChar = screen.getFrontCharacter(x, y);
            if (frontChar != null) {//|| screen.getFrontCharacter(x+1, y)!=null
                char carattere=frontChar.getCharacter();
                //char carattere2=screen.getFrontCharacter(x+1,y).getCharacter();
                if(carattere!='0')//|| carattere2!='0'
                    return true;
            }




            // RIGUARDA LE RIGHE

            if ( screen.getBackCharacter(x, y) != null ) {//&&screen.getBackCharacter(x, y-1)!=null
                char carattere = screen.getBackCharacter(x, y).getCharacter();
                if (carattere != '0')//||carattere2 != '0'
                    return true;
            }
            frontChar = screen.getFrontCharacter(x, y);
            if (frontChar != null) {//&&screen.getFrontCharacter(x, y-1)!=null
                char carattere=frontChar.getCharacter();
                if(carattere!='0')//||carattere2!='0'
                    return true;
            }
        }
        return false;

    }


    /*
    *  METODO CHE MI CONTROLLA EFFETTIVAMENTE CHE LE RIGHE SIANO PIENE O NO
    *  ANDANDO ANCHE A CONSIDERARE LE RIGHE SPAZZATURA CHE VERRANNO INSERITE CON IL NUMERO 2 E DI COLORE GRIGIO
    * */



    public static int[] isRowFull(TerminalSize terminalSize, Screen screen,int punteggio_player)
    {
        punteggio=punteggio_player;
        int[] informazioni=new int[2];
        int contatore=0;
        int trash_row_send=0;
        int tmp=0;
        double moltiplicatore=0;
        int posizione_riga=0;// serve per capire in quale posizione si trova la riga da eliminare
        for(int row=terminalSize.getRows()-1;row>0;row--)
        {
            for(int col=0; col< terminalSize.getColumns();col=col+2)
            {

                char carattere_test=screen.getBackCharacter(col,row).getCharacter();
                char carattere_test2=screen.getFrontCharacter(col,row).getCharacter();
                if(screen.getBackCharacter(col,row).getCharacter()=='1')
                {
                    contatore++;
                }
            }
            if(contatore== terminalSize.getColumns())
            {
                tmp++;
                if(posizione_riga==0)
                    posizione_riga=row;

                n_riga++;
            }
        }
        /*
        * CI SERVE PER CAPIRE QUANTE RIGHE SPAZZATURA MANDARE INVOCANDO POI IL METODO DA CREARE
        * set_n_row_to_send
        * */
        switch (tmp)
        {
            case 1:
                trash_row_send=0;
                moltiplicatore=1*24;
                break;
            case 2:
                trash_row_send=1;
                moltiplicatore=1.2*24;
                break;
            case 3:
                trash_row_send=2;
                moltiplicatore=1.5*24;
                break;
            case 4:
                trash_row_send=4;
                moltiplicatore=2*24;
                break;
        }
        set_n_row_to_delete(tmp);
        punteggio=calcolo_punteggio(moltiplicatore,punteggio);
        informazioni[0]=posizione_riga;
        informazioni[1]=n_riga;
        return informazioni;
    }
    public static int calcolo_punteggio(double moltiplicatore,int punteggio)
    {
        punteggio+=moltiplicatore;
        return punteggio;
    }
    public static int punteggio_player()
    {
        return punteggio;
    }
    public static void set_n_row_to_delete(int tmp)
    {
        n_row=0;
        n_row=tmp;
    }
    public static int get_n_row_to_delete()
    {
        return n_row;
    }




}
