import java.util.NoSuchElementException;

public class IntList {
    private int[] data;
    private int size;

    public IntList() {
        data = new int[1];
        size = 0;
    }

    public IntList(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    public void append(int value) {
        if (size >= data.length) {
            this.extract();
        }
        data[size++] = value;
    }

    public void pop() {
        if (size == 0) {
            throw new NoSuchElementException("pop from empty list");
        }
        size--;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " is out of bound " + size);
        }
        return data[index];
    }

    public void put(int index, int value) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " is out of bound " + size);
        }
        data[index] = value;
    }

    public int size() {
        return size;
    }

    public int length() {
        return size;
    }

    public int capacity() {
        return data.length;
    }

    public void extract() {
        this.setLength(this.length() * 2);
    }

    public void setLength(int length) {
        int[] newData = new int[length];
        System.arraycopy(data, 0, newData, 0, Math.min(data.length, length));
        data = newData;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size - 1; i++) {
            builder.append(data[i]);
            builder.append(' ');
        }
        builder.append(data[size - 1]);
        return builder.toString();
    }
}
