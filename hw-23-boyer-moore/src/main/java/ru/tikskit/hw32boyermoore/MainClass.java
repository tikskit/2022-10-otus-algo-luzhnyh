package ru.tikskit.hw32boyermoore;

import java.util.ArrayList;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
/*        Search s = new SearchBMH();
        String text = "ABC@ABBDABCABABCD";
        String mask = "ABCD";
        int search = s.search(text, mask);*/
        List<Search> algs = new ArrayList<>();
        algs.add(new SearchFullScan());
        algs.add(new SearchBMH());
        DataProvider dataProvider = new DataProviderImpl();
        Data[] data = dataProvider.getData();
        int[] iterations = {100, 1000};
        for (Search s : algs) {
            for (Data d : data) {
                for (int i : iterations) {
                    long avgTime = doSearch(s, d.getText(), d.getMask(), i);
                    System.out.printf("%s iterations of %s over %s: avg %sms%n", i, s.getDescription(), d.getDescription(), avgTime);
                }
            }
        }
    }

    private static long doSearch(Search s, String text, String mask, int iterCount) {
        Timer timer = new Timer();
        timer.start();
        for (int i = iterCount; i > 0; i--) {
            s.search(text, mask);
        }
        long timems = timer.stop();
        return timems / iterCount;
    }
}
