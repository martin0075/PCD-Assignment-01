public class MailBox {
    private int message=-1;
    //variabile booleana che determina lo stato della casella di posta
    private boolean isEmpty=true;

    public synchronized void putMessage(int msg) throws InterruptedException{
        while(!isEmpty){
            wait();
        }
        message=msg;
        System.out.println("Sent message: "+msg);
        isEmpty=false;
        notify();
    }

    public synchronized int getMessage() throws InterruptedException{
        while(isEmpty){
            wait();
        }
        System.out.println("\t\t\tGot message: "+message);
        isEmpty=true;
        notify();;
        return message;
    }
}
