package pl.damiankaplon.domain;

import lombok.RequiredArgsConstructor;
import java.util.ArrayList;

@RequiredArgsConstructor
abstract class DistanceCalculator {

    record DocsPair(Document doc1, Document doc2){}
    final ArrayList<Term> terms;
    abstract double distanceBetween(final DocsPair docsPair) throws ISMException;

}
