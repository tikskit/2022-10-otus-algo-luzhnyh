package ru.tikskit.minhashsimhash;

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
    public static void main(String[] args) throws Exception {
        Charset windows1252 = Charset.forName("windows-1251");
        ShinglesCounter counter = new ShinglesCounter();
        FileShinglerSync fss = new FileShinglerSync("G:/Temp/file1.txt", s -> counter.inc(s.size()), windows1252);
        long start = System.currentTimeMillis();
        fss.read();
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(counter.getCount());
    }
}
