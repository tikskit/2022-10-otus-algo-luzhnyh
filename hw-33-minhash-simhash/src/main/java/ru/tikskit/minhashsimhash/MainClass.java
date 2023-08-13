package ru.tikskit.minhashsimhash;

import java.io.IOException;
import java.nio.charset.Charset;

public class MainClass {
    private static class ShinglesCounter {
        private long count = 0;

        public void inc(int value) {
            count += value;
        }

        public long getCount() {
            return count;
        }
    }

    private static DocShingles createDocShingles(String fileName, Charset charset) throws IOException {
        DocShingles doc = new DocShingles(fileName);
        FileShinglerSync fss = new FileShinglerSync(fileName, doc.getShingles()::addAll, charset);
        fss.read();
        return doc;
    }
    public static void main(String[] args) throws Exception {

        Charset windows1252 = Charset.forName("windows-1251");
        ShinglesCounter counter = new ShinglesCounter();

        DocShingles doc1 = createDocShingles("G:/Temp/file1.txt", windows1252);
        DocShingles doc2 = createDocShingles("G:/Temp/file2.txt", windows1252);


    }
}
