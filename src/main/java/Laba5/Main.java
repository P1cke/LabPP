package Laba5;

public class Main {
    public static void main(String[] args) {
        DataContainer container = new SharedData(10); // Creating shared data container
        Thread writerThread = new WriterThread(container);
        Thread readerThread = new ReaderThread(container);

        writerThread.setPriority(Thread.MAX_PRIORITY);
        readerThread.setPriority(Thread.MIN_PRIORITY);

        writerThread.start();
        readerThread.start();
    }
}
