package ru.tikskit.hw32boyermoore;

public class SearchSuffix implements Search {
    private int[] createShift(String mask) {
        /*
        Таблица смещений. Индекс таблицы соответствует длине маски. А значение элемента содержит величину смещения
         */
        int[] shift = new int[mask.length()];
        // Инициализируем таблицу смещений значениием, равным по умолчанию 1
        for (int i = 0; i < shift.length; i++) {
            shift[i] = 1;
        }
        // Теперь определим для каждого суффикса величину смежения
        for (int len = 1; len <= mask.length(); len++) {
            String suffix = mask.substring(mask.length() - len);
            // Начинаем искать, есть ли левее в маске еще совпадения этого же суффикса
            int i = mask.length() - len - 1;
            while (i > 0 && !mask.substring(i, i + len).equals(suffix)) {
                i--;
            }

            if (i >= 0) {
                shift[len] = mask.length() - len - i;
            }
        }

        return shift;
    }
    @Override
    public int search(String text, String mask) {
        if (mask.length() > text.length()) {
            return 0;
        }

        int[] shift = createShift(mask);
        int t = 0;
        while (t <= text.length() - mask.length()) {
            int m = mask.length() - 1;
            int count = 0;
            while(m >= 0 && text.charAt(t + m) == mask.charAt(m)) {
                m--;
                count++;
            }
            if (m < 0) {
                return t;
            }
            t += shift[count];
        }
        return 0;
    }

    @Override
    public String getDescription() {
        return "Suffix search";
    }
}
