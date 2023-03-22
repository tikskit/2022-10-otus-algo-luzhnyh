package ru.tikskit.hw26compress;

public interface Compressor {
    int[] compress(int[] data);
    int[] uncompress(int[] data);
}
