package ru.tikskit.quickmergeexternal.utils;

public class Timer {
    private long start;

    public void start() {
        start = System.currentTimeMillis();
    }

    public long stop() {
        return System.currentTimeMillis() - start;
    }

}
