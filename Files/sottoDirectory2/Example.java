public class Example {
    public static void main(String[]args){
        MailBox mailbox=new MailBox();

        //Creiamo receiver
        Receiver receiver= new Receiver(mailbox);
        Thread receiverThread= new Thread(receiver);
        receiverThread.start();

        //Creiamo sender
        Sender sender=new Sender(mailbox);
        Thread senderThread= new Thread(sender);
        senderThread.start();
    }
}
