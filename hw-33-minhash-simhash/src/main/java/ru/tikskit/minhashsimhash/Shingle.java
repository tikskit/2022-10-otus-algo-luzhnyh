package ru.tikskit.minhashsimhash;

import java.util.Arrays;

/*
Шингл исходного текста
 */
public record Shingle (String[] words){
    public Shingle(String[] words) {
        this.words = Arrays.copyOf(words, words.length);
    }

    @Override
    public String toString() {
        return "Shingle{" +
                "words=" + Arrays.toString(words) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shingle shingle = (Shingle) o;
        return Arrays.equals(words, shingle.words);
    }
}
