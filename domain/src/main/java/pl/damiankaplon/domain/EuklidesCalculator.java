package pl.damiankaplon.domain;

import java.util.ArrayList;

class EuklidesCalculator extends DistanceCalculator {

    public EuklidesCalculator(DocsPair docsPair, ArrayList<Term> terms) {
        super(docsPair, terms);
    }

    @Override
    double distance() throws ISMException {
        var sum = 0d;
        for (Term term : super.terms) {
            var diff = term.getOccurrences(docsPair.doc1().getName()) - term.getOccurrences(docsPair.doc2().getName());
            sum += Math.pow(diff, 2);
        }
        return Math.sqrt(sum);
    }
}
