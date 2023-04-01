package ru.tikskit.hw25kmp;

/**
 * Алгоритм поиска Бойер-Мура-Хорспула
 */
public class SearchBMH implements Search {
    private int[] createShift(String mask) {
        int[] shift = new int[128];
        for (int i = 0; i < shift.length; i++) {
            shift[i] = mask.length();
        }
        for (int m = 0; m < mask.length() - 1; m++) {
            shift[mask.charAt(m)] = mask.length() - m - 1;
        }

        return shift;
    }

    @Override
    public int search(String text, String mask) {
        int[] shift = createShift(mask);
        int t = 0;
        while (t <= text.length() - mask.length()) {
            int m = mask.length() - 1;
            while(m >= 0 && text.charAt(t + m) == mask.charAt(m)) {
                m--;
            }
            if (m < 0) {
                return t;
            }
            t += shift[text.charAt(t + mask.length() - 1)];
        }
        return -1;
    }

    @Override
    public String getDescription() {
        return "Boyer-Moore-Horspool search";
    }
}
