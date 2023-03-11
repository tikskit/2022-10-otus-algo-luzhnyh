package ru.tikskit.hw32boyermoore;

public class MainClass {
    public static void main(String[] args) {
        Search s = new FullScanSearch();
        DataProvider dataProvider = new DataProviderImpl();
        Data[] data = dataProvider.getData();
        int[] iterations = {100, 1000};
        for (Data d : data) {
            for (int i : iterations) {
                long avgTime = doSearch(s, d.getText(), d.getMask(), i);
                System.out.printf("%s iterations of Full scan search over %s: avg %sms%n", i, d.getDescription(), avgTime);

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
