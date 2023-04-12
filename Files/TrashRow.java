package Client.Model;

public class TrashRow {

    private int[] informazioni;

    private int trash_row_send;

    public TrashRow(int[] informazioni, int trash_row_send) {
        this.informazioni = informazioni;
        this.trash_row_send = trash_row_send;
    }

    public int[] getInformazioni() {
        return informazioni;
    }

    public void setInformazioni(int[] informazioni) {
        this.informazioni = informazioni;
    }

    public int getTrash_row_send() {
        return trash_row_send;
    }

    public void setTrash_row_send(int trash_row_send) {
        this.trash_row_send = trash_row_send;
    }
}
