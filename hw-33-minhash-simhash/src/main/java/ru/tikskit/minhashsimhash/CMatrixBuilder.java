package ru.tikskit.minhashsimhash;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CMatrixBuilder {
    public CMatrix build(Collection<DocShingles> docShingles) {
        Set<Shingle> allSets = docShingles.parallelStream()
                .map(DocShingles::getShingles)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }
}
