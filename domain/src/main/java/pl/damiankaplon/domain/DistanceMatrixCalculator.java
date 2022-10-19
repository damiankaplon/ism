package pl.damiankaplon.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class DistanceMatrixCalculator {

    private final DistanceCalculator distanceCalculator;
    private final LinkedHashSet<Document> documents;
    public record Distances(Document doc, LinkedHashMap<Document, Double> distancesTo){}

    public Set<Distances> calcDistancesBetweenDocs() throws ISMException {
        final var distancesMatrix = new LinkedHashSet<Distances>();
        for (Document doc1 : this.documents) {
            final var distancesToOthers = new LinkedHashMap<Document, Double>();
            for (Document doc2 : this.documents) {
             final var distance = distanceCalculator.distanceBetween(new DistanceCalculator.DocsPair(doc1, doc2));
             distancesToOthers.put(doc2, distance);
            }
            distancesMatrix.add(new Distances(doc1, distancesToOthers));
        }
        return distancesMatrix;
    }

    public static DistanceMatrixCalculator euklides(LinkedHashSet<Document> documents, ArrayList<Term> terms) {
        final var euklidesCalculator = new EuklidesCalculator(terms);
        return new DistanceMatrixCalculator(euklidesCalculator, documents);
    }
}
