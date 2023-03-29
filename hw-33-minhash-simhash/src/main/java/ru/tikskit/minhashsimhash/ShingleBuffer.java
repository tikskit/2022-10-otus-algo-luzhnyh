package ru.tikskit.minhashsimhash;

import java.util.ArrayList;
import java.util.List;

public class ShingleBuffer {
    private final StringBuilder buffer = new StringBuilder();
    private final int shingleSize;

    public ShingleBuffer(int shingleSize) {
        this.shingleSize = shingleSize;
    }

    public void append(String str) {
        buffer.append(str);
    }

    public List<Shingle> getShingles() {
        String string = buffer.toString();
        String[] bufferWords = string.split("[\\s\\r\\n\\t]+");
        List<Shingle> res = new ArrayList<>();
        if (bufferWords.length >= shingleSize) {
            String[] shingleWords = new String[shingleSize];
            for (int i = 0; i <= bufferWords.length - shingleSize; i++) {
                System.arraycopy(bufferWords, i, shingleWords, 0, shingleSize);
                Shingle shingle = new Shingle(shingleWords);
                res.add(shingle);
            }
        }

        return res;
    }
}
