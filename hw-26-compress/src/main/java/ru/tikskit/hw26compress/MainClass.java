package ru.tikskit.hw26compress;

import java.nio.charset.StandardCharsets;

public class MainClass {
    public static void main(String[] args) {
        String str = "AAADSDASKKLJJJDDDDSQWLWWWWW";
        byte[] srcData = str.getBytes(StandardCharsets.UTF_8);
        Compressor c = new SimpleRLE();
        byte[] compressed = c.compress(srcData);
        byte[] uncompressedData = c.uncompress(compressed);
        String res = new String(uncompressedData);

        if (!str.equals(res)) {
            throw new RuntimeException("Data corrupted");
        }
        System.out.printf("%s bytes were compressed to %s%n", srcData.length, compressed.length);
    }
}
