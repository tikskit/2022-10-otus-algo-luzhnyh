package ru.tikskit.minhashsimhash;

import java.nio.charset.Charset;

public class MainClass {
    public static void main(String[] args) throws Exception {
        Charset windows1252 = Charset.forName("windows-1252");
        FileShinglerSync fss = new FileShinglerSync("G:/Temp/mail.txt", s -> s.forEach(System.out::println), windows1252);
        fss.read();
    }
}
