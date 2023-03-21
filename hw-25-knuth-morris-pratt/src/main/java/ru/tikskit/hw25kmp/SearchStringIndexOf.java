package ru.tikskit.hw25kmp;

public class SearchStringIndexOf implements Search {
    @Override
    public int search(String text, String mask) {
        return text.indexOf(mask);
    }

    @Override
    public String getDescription() {
        return "Java String.indexOf";
    }
}
