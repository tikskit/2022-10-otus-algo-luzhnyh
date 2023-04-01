package ru.tikskit.hw25kmp;

import java.util.regex.Pattern;

public class SearchPattern implements Search {
    @Override
    public int search(String text, String mask) {
        Pattern pattern = Pattern.compile(mask);

        return pattern.matcher(text).find() ? 1 : -1;
    }

    @Override
    public String getDescription() {
        return "Java Pattern";
    }
}
