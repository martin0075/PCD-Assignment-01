package pcd.lab03.sem.ex;

import java.util.concurrent.Semaphore;

/**
 * Unsynchronized version
 * 
 * @TODO make it sync 
 * @author aricci
 *
 */
public class TestPingPong {
	public static void main(String[] args) {
		Semaphore pingDone=new Semaphore(1,true);
		Semaphore pongDone=new Semaphore(0,false);
		new Pinger(pingDone, pongDone).start();
		new Ponger(pingDone, pongDone).start();
	}

}
