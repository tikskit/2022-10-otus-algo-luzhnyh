package ru.tikskit.minhashsimhash;

import java.util.HashSet;
import java.util.Set;

public class ShingleBuffer {
    private final StringBuilder buffer = new StringBuilder();
    private final int shingleSize;

    public ShingleBuffer(int shingleSize) {
        this.shingleSize = shingleSize;
    }

    public void append(String str) {
        buffer.append(str);
    }

    public Set<Shingle> getShingles() {
        String string = buffer.toString();
        String[] bufferWords = string.split("\\s+");
        Set<Shingle> res = new HashSet<>();
        if (bufferWords.length >= shingleSize) {
            String[] shingleWords = new String[shingleSize];
            for (int i = 0; i < bufferWords.length - shingleSize; i++) {
                System.arraycopy(bufferWords, i, shingleWords, 0, shingleSize);
                res.add(new Shingle(shingleWords));
            }
        }

        return res;
    }
}
