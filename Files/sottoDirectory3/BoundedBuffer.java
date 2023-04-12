package org.example;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer<Item> implements IBoundedBuffer<Item>{

    private LinkedList<Item> buffer;
    private Lock mutex;
    private Condition notEmpty, notFull;

    public BoundedBuffer() {
        this.buffer = new LinkedList<Item>();
        this.mutex=new ReentrantLock();
        this.notEmpty= mutex.newCondition();
        this.notFull= mutex.newCondition();
    }

    private boolean isEmpty(){return buffer.size()==0;}

    @Override
    public void put(Item item) throws InterruptedException {
        buffer.addLast(item);
    }

    @Override
    public Item get() throws InterruptedException {
        try {
            mutex.lock();
            if (isEmpty()) {
                notEmpty.await();
            }
            Item item = buffer.removeFirst();
            notFull.signal();
            return item;
        } finally {
            mutex.unlock();
        }
    }
}
