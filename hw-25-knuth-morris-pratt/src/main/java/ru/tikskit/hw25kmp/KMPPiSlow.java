package ru.tikskit.hw25kmp;

public class KMPPiSlow implements Search {

    public int[] createPiSlow(String mask) {
        int[] pi = new int[mask.length() + 1];
        for (int q = 0; q <= mask.length(); q++) {
            String line = mask.substring(0, q);
            for (int len = 0; len < q; len++) {
                if (line.substring(0, len).equals(line.substring(line.length() - len))) {
                    pi[q] = len;
                }
            }
        }
        return pi;
    }
    @Override
    public int search(String text, String mask) {
        String str = mask + '@' + text;
        int val = mask.length();
        int[] pi = createPiSlow(str);
        for (int p = 0; p < pi.length; p++) {
            if (pi[p] == val) {
                return pi[p];
            }
        }
        return -1;

    }

    @Override
    public String getDescription() {
        return "Knuth-Morris-Pratt with Pi slow";
    }
}
