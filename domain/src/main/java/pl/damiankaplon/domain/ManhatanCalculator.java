package pl.damiankaplon.domain;

import java.util.ArrayList;

final class ManhatanCalculator extends DistanceCalculator {

    ManhatanCalculator(ArrayList<Term> terms) {
        super(terms);
    }
    @Override
    double distanceBetween(final DocsPair docsPair) throws ISMException {
        var sum = 0d;
        for (Term term : super.terms) {
            var diff = Math.abs(
                    term.getOccurrences(docsPair.doc1().getName()) - term.getOccurrences(docsPair.doc2().getName())
            );
            sum += diff;
        }
        return sum;
    }
}
