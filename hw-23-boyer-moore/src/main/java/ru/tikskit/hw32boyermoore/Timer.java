package ru.tikskit.hw32boyermoore;

public class Timer {
    private long start;

    public void start() {
        start = System.currentTimeMillis();
    }

    public long stop() {
        return System.currentTimeMillis() - start;
    }

}
