package pl.damiankaplon.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

class EuklidesDistanceMatrixCalculator implements DistanceMatrixCalculator {

    @Override
    public Set<Distances> calcDistancesBetweenDocs(Set<Document> docs, ArrayList<Term> terms) throws ISMException {
        final var distancesMatrix = new LinkedHashSet<Distances>();
        for (Document doc1 : docs) {
            final var distancesToOthers = new LinkedHashMap<Document, Double>();
            for (Document doc2 : docs) {
             final var distanceCalculator = new EuklidesCalculator(new DistanceCalculator.DocsPair(doc1, doc2), terms);
             final var distance = distanceCalculator.distance();
             distancesToOthers.put(doc2, distance);
            }
            distancesMatrix.add(new Distances(doc1, distancesToOthers));
        }
        return distancesMatrix;
    }

}
