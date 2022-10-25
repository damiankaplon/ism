package pl.damiankaplon.domain;

import java.util.ArrayList;

final class EuklidesZoomCalculator extends DistanceCalculator {

    private final int power;
    private final int sqrt;

    public EuklidesZoomCalculator(ArrayList<Term> terms, int zoom, int sqrt) {
        super(terms);
        this.power = zoom;
        this.sqrt = sqrt;
    }

    @Override
    double distanceBetween(DocsPair docsPair) throws ISMException {
        var sum = 0d;
        for (Term term : super.terms) {
            var diff =
                    term.getOccurrences(docsPair.doc1().getName()) - term.getOccurrences(docsPair.doc2().getName());
            sum += Math.pow(diff, this.power);
        }
        return Math.pow(sum, ((float)1/this.sqrt));
    }
}
