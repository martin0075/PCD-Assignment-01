package pcd.lab03.sem.ex;

import java.util.concurrent.Semaphore;

public class Pinger extends Thread {

	private Semaphore pongDone;
	private Semaphore pingDone;

	public Pinger(Semaphore pongDone, Semaphore pingDone) {
		this.pongDone = pongDone;
		this.pingDone=pingDone;
	}	
	
	public void run() {
		while (true) {
			try {
				pongDone.acquire();
				System.out.println("ping!");
				Thread.sleep(1000);
			} catch (Exception ex) {
				ex.printStackTrace();
			}finally {
				pongDone.release();
			}
		}
	}
}