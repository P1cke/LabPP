package Laba5;

class SharedData implements DataContainer {
    private final int[] data;

    public SharedData(int size) {
        this.data = new int[size];
    }

    @Override
    public synchronized void write(int value, int position) {
        data[position] = value;
        System.out.println("Write: " + value + " to position " + position);
    }

    @Override
    public synchronized int read(int position) {
        return data[position];
    }
}
