package com.chatapp.app.connect;


public class ChatServerEventQueue {
    private final static ChatServerEventQueue queue;
    private final Runnable[] cycleQueue;
    private final int cycleQueueLength = 16;
    private int head = 0;
    private int tail = 0;
    private ChatServerEventQueue() {
        this.cycleQueue = new Runnable[cycleQueueLength];
    }
    static {
        queue = new ChatServerEventQueue();
        Thread eventThread = new Thread(() -> {
            while (true) {
                try {
                    queue.poll().run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        eventThread.setDaemon(true);
        eventThread.start();
    }
    public synchronized void add(Runnable job) {
        if ((tail + 1) % cycleQueueLength == head) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        cycleQueue[tail] = job;
        tail = (tail + 1) % cycleQueueLength;
        notifyAll();
    }
    public synchronized Runnable poll() {
        if (head == tail) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Runnable result = cycleQueue[head];
        cycleQueue[head] = null;
        head = (head + 1) % cycleQueueLength;
        notifyAll();
        return result;
    }

    public static synchronized void invoke(Runnable job) {
        queue.add(job);
    }
}
