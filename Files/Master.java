package org.example;


public class Master extends Thread{

    private IBoundedBuffer<Integer> buffer;

    public Master(IBoundedBuffer<Integer> buffer) {
        this.buffer = buffer;
    }

    public void run(){
        while (true){
            //Implementare metodo che prende i file dalla cartelli e li passa nel buffer
            Integer item=0;
            try {
                buffer.put(item);
                log("produced "+item);
            } catch(InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }

    private void log(String st){
        synchronized(System.out){
            System.out.println("["+this.getName()+"] "+st);
        }
    }
}
