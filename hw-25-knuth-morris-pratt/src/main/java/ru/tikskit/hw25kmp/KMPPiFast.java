package ru.tikskit.hw25kmp;

public class KMPPiFast implements Search {

    public int[] createPiFast(String mask) {
        int[] pi = new int[mask.length() + 1];
        pi[1] = 0;

        for (int q = 1; q < mask.length(); q++) {
            int len = pi[q];
            while (len > 0 && mask.charAt(len) != mask.charAt(q)) {
                len = pi[len];
            }
            if (mask.charAt(len) == mask.charAt(q)) {
                len++;
            }
            pi[q + 1] = len;
        }
        return pi;
    }
    @Override
    public int search(String text, String mask) {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Knuth-Morris-Pratt with Pi fast";
    }
}
