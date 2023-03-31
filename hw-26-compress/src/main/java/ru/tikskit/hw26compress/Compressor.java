package ru.tikskit.hw26compress;

public interface Compressor {
    byte[] compress(byte[] data);
    byte[] decompress(byte[] data);
}
