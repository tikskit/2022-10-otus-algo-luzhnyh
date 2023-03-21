package ru.tikskit.hw32boyermoore;

import java.util.ArrayList;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
/*        Search s = new SearchSuffix();
        String text = "XXXXXXXXXXXXBC-ABC-BC-C-ABCXXXXXXXXXXXXXXXX";
        String mask = "ABC-ABC-BC-C-ABC";
        int search = s.search(text, mask);
        System.out.println(search);*/
        List<Search> algs = new ArrayList<>();
        algs.add(new SearchFullScan());
        algs.add(new SearchBMH());
        algs.add(new SearchSuffix());
        algs.add(new SearchStringIndexOf());
        algs.add(new SearchPattern());
        DataProvider dataProvider = new DataProviderImpl();
        Data[] data = dataProvider.getData();
        int[] iterations = {10, 100};
        for (Search s : algs) {
            for (Data d : data) {
                for (int i : iterations) {
                    SearchResult res = doSearch(s, d.getText(), d.getMask(), i);
                    System.out.printf("%s iterations of %s over %s: avg %sms, %s hits%n", i, s.getDescription(), d.getDescription(), res.avgTime(), res.hitsCount());
                }
            }
        }
    }

    private static SearchResult doSearch(Search s, String text, String mask, int iterCount) {
        Timer timer = new Timer();
        int hits = 0;
        timer.start();
        for (int i = iterCount; i > 0; i--) {
            if (s.search(text, mask) >= 0) {
                hits++;
            }
        }
        long timems = timer.stop();
        long avgTime = timems / iterCount;
        return new SearchResult(avgTime, hits);
    }
}
