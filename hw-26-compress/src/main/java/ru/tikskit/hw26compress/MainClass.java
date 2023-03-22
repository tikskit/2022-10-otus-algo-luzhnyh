package ru.tikskit.hw26compress;

import java.nio.charset.StandardCharsets;

public class MainClass {
    public static void main(String[] args) {
        String data = "AAADSDASKKLJJJDDDDSQWLWWWWW";
        Compressor c = new SimpleRLE();
        byte[] compressed = c.compress(data.getBytes(StandardCharsets.UTF_8));
        byte[] uncompressedData = c.uncompress(compressed);
        String res = new String(uncompressedData);

        System.out.println(res);
    }
}
