package pl.damiankaplon.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

public interface DistanceMatrixCalculator {
    record Distances(Document doc, LinkedHashMap<Document, Double> distancesTo){}
    Set<Distances> calcDistancesBetweenDocs(Set<Document> docs, ArrayList<Term> terms) throws ISMException;

    static DistanceMatrixCalculator euklidesMatrixCalculator() {
        return new EuklidesDistanceMatrixCalculator();
    }
}
