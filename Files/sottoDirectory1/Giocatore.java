public class Giocatore {
    private String nome;
    public int punteggio;
    public Giocatore(String nome, int punteggio)
    {
        this.nome=nome;
        this.punteggio=punteggio;
    }

    public void setNome(String nome)
    {
        this.nome=nome;
    }
    public String getNome()
    {
        return nome;
    }
    public void setPunteggio(int punteggio)
    {
        this.punteggio=punteggio;
    }
    public int getPunteggio()
    {
        return punteggio;
    }


}
