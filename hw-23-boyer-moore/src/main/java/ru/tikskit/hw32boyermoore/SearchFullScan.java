package ru.tikskit.hw32boyermoore;

/**
 * Реализация поиска методом перебора
 */
public class SearchFullScan implements Search {
    @Override
    public int search(String text, String mask) {
        for (int t = 0; t <= text.length() - mask.length(); t++) {
            int m = 0;
            while (m < mask.length() && mask.charAt(m) == text.charAt(t + m)) {
                m++;
            }
            if (m == mask.length()) {
                return t;
            }
        }

        return -1;
    }

    @Override
    public String getDescription() {
        return "Full scan search";
    }
}
