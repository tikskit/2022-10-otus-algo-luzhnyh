package ru.tikskit.minhashsimhash;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Set;
import java.util.function.Consumer;

public class MainClass {
    public static void main(String[] args) throws Exception {
        Path filePath = Path.of("G:\\Temp\\mail.txt");
        Charset windows1252 = Charset.forName("windows-1252");
        try(FileShingler fs = new FileShingler(s -> s.forEach(System.out::println), filePath, windows1252)) {
            fs.processFile();
        }

    }
}
