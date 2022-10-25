package pl.damiankaplon.domain;

import java.util.ArrayList;

final class CzebyszewCalculator extends DistanceCalculator {
    CzebyszewCalculator(ArrayList<Term> terms) {
        super(terms);
    }

    @Override
    double distanceBetween(DistanceCalculator.DocsPair docsPair) throws ISMException {
        var max = 0d;
        for (Term term : super.terms) {
            var diff =
                    term.getOccurrences(docsPair.doc1().getName()) - term.getOccurrences(docsPair.doc2().getName());
            max = diff > max ? diff : max;
        }
        return max;
    }
}
