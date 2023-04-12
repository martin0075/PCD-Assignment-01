import java.util.Random;

public class Receiver implements Runnable{
    private MailBox mailbox;
    

    public Receiver(MailBox mb){
        this.mailbox=mb;
        
    }
    
    
    public void run(){
        Random random=new Random();
        while(true){
            try{
                Thread.sleep(random.nextInt(3000));
                mailbox.getMessage();
            }catch(InterruptedException e){
                return;
            }
           
        }
    }
}
