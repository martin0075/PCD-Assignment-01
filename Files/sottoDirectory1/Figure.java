import com.googlecode.lanterna.TextColor;

import java.util.Random;

public class Figure {
    private char[][] pezzo;
    //private int x, y;
    private TextColor color;

    public Figure(){
        //this.pezzo=Pezzo();
        /*this.x=x;
        this.y=y;*/
    }

    public void getPezzo(int num){

        switch (num){
            case 1:
                pezzo=new char[][]{
                        {'\0', '1', '\0'},
                        {'1', '1', '1'}
                };
                color=new TextColor.RGB(80, 46, 125);
                break;
            case 2:
                pezzo=new char[][]{
                        {'1'},
                        {'1'},
                        {'1'},
                        {'1'}
                };
                color=new TextColor.RGB(61, 15, 245);
                break;
            case 3:
                pezzo=new char[][]{
                        {'1', '1','\0'},
                        {'\0', '1','1'}
                };
                color=new TextColor.RGB(15, 183, 245);
                break;
            case 4:
                pezzo=new char[][]{
                        {'\0', '1','1'},
                        {'1', '1', '\0'}
                };
                color=new TextColor.RGB(114, 203, 59);
                break;
            case 5:
                pezzo=new char[][]{
                        {'1','\0'},
                        {'1','\0'},
                        {'1', '1'}
                };
                color=new TextColor.RGB(255, 213, 0);
                break;
            case 6:
                pezzo=new char[][]{
                        {'\0', '1'},
                        {'\0', '1'},
                        {'1', '1'}
                };
                color=new TextColor.RGB(255, 151, 28);
                break;
            case 7:
                pezzo=new char[][]{
                        {'1', '1'},
                        {'1', '1'}
                };
                color=new TextColor.RGB(206, 60, 174);
                break;
        }
    }

    public char[][] ritornoPezzo() {
        return pezzo;
    }

    public void setPezzo(char[][] tmp)
    {
        this.pezzo=tmp;
    }

    public char[][] ruota(char[][] pezzo)
    {
        char [][]tmp=new char[pezzo[0].length][pezzo.length];
        for(int i=0;i< pezzo.length;i++)
        {
            for(int k=0;k<pezzo[0].length;k++)
            {
                tmp[k][pezzo.length-i-1]=pezzo[i][k];
            }
        }
        return tmp;

    }

    public TextColor getColor(){
        return color;
    }
}
