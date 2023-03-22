package ru.tikskit.hw26compress;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainClass {
    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            System.out.println("java ru.tikskit.hw26compress.MainClass src tar mode");
            System.out.println("\tsrc source file name");
            System.out.println("\ttar target file name");
            System.out.println("\tmode c - for compressing, d - for decompressing");
            System.out.println("example:");
            System.out.println("\tjava ru.tikskit.hw26compress.MainClass file.in tar file.out");
        } else {
            String sourceFileName = args[0];
            String targetFileName = args[1];
            String mode = args[2];

            if (mode.equalsIgnoreCase("c")) {
                compressFile(sourceFileName, targetFileName);
            } else if (mode.equalsIgnoreCase("d")) {
                decompressFile(sourceFileName, targetFileName);
            }
        }

/*        String str = "AAADSDASKKLJJJDDDDSQWLWWWWWAA";
        StringBuilder sb = new StringBuilder();

        byte[] srcData = str.getBytes(StandardCharsets.UTF_8);
        Compressor c = new EnhancedRLE();
        byte[] compressed = c.compress(srcData);
        byte[] uncompressedData = c.decompress(compressed);
        String res = new String(uncompressedData);

        if (!str.equals(res)) {
            throw new RuntimeException("Data corrupted");
        }
        System.out.printf("%s bytes were compressed to %s%n", srcData.length, compressed.length);*/
    }

    private static void decompressFile(String src, String tar) throws IOException {
        try (BufferedInputStream in =
                     new BufferedInputStream(new FileInputStream(src), 1024);
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(tar), 1024)) {
            byte[] bytes = in.readAllBytes();
            Compressor c = new EnhancedRLE();
            byte[] decomressed = c.decompress(bytes);
            out.write(decomressed);
        }
    }

    private static void compressFile(String src, String tar) throws IOException {
        try (BufferedInputStream in =
                     new BufferedInputStream(new FileInputStream(src), 1024);
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(tar), 1024)) {
            byte[] bytes = in.readAllBytes();
            Compressor c = new EnhancedRLE();
            byte[] compressed = c.compress(bytes);
            out.write(compressed);
        }
    }
}
