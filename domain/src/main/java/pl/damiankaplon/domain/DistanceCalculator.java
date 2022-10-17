package pl.damiankaplon.domain;

import lombok.RequiredArgsConstructor;
import java.util.ArrayList;

@RequiredArgsConstructor
abstract class DistanceCalculator {

    record DocsPair(Document doc1, Document doc2){}

    final DocsPair docsPair;
    final ArrayList<Term> terms;
    abstract double distance() throws ISMException;

    public static DistanceCalculator euklides(DocsPair docsPair, ArrayList<Term> terms) {
        return new EuklidesCalculator(docsPair, terms);
    }

}
