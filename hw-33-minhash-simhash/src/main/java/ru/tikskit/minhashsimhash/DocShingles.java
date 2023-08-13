package ru.tikskit.minhashsimhash;

import java.util.ArrayList;
import java.util.List;

public class DocShingles {
    private final String id;
    private final List<Shingle> shingles = new ArrayList<>();

    public DocShingles(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public List<Shingle> getShingles() {
        return shingles;
    }
}
