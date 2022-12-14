package pl.damiankaplon.domain;

import java.util.ArrayList;

final class EuklidesCalculator extends DistanceCalculator {

    public EuklidesCalculator(ArrayList<Term> terms) {
        super(terms);
    }

    @Override
    double distanceBetween(DocsPair docsPair) throws ISMException {
        var sum = 0d;
        for (Term term : super.terms) {
            var diff =
                    term.getOccurrences(docsPair.doc1().getName()) - term.getOccurrences(docsPair.doc2().getName());
            sum += Math.pow(diff, 2);
        }
        return Math.sqrt(sum);
    }
}
