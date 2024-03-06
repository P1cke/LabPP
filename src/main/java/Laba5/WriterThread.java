package Laba5;

import java.util.Random;

class WriterThread extends Thread {
    private final DataContainer container;
    private final Random random;

    public WriterThread(DataContainer container) {
        this.container = container;
        this.random = new Random();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) { // Arbitrary number of iterations
            int value = random.nextInt(100) + 1; // Random value between 1 and 100
            container.write(value, i);
            try {
                Thread.sleep(100); // Sleep for 100 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ReaderThread extends Thread {
    private final DataContainer container;

    public ReaderThread(DataContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) { // Arbitrary number of iterations
            int value = container.read(i);
            System.out.println("Read: " + value + " from position " + i);
            try {
                Thread.sleep(100); // Sleep for 100 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}