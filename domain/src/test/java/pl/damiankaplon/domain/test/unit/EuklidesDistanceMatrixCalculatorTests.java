package pl.damiankaplon.domain.test.unit;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.damiankaplon.domain.DistanceMatrixCalculator;
import pl.damiankaplon.domain.Document;
import pl.damiankaplon.domain.ISMException;
import pl.damiankaplon.domain.Term;
import pl.damiankaplon.domain.test.DocumentTestData;

import java.util.*;

class EuklidesDistanceMatrixCalculatorTests {

    @Test
    void givenProperTermsAndDocsReturnsProperMatrixWithZeroOnDiagonal() throws ISMException {
        //GIVEN
        Document[] docs = {DocumentTestData.MOCK_DOC(), DocumentTestData.MOCK_DOC(), DocumentTestData.MOCK_DOC()};
        final var docsSet = new LinkedHashSet<Document>();
        Collections.addAll(docsSet, docs);
        final ArrayList<Term> testTerms = Lists.newArrayList();
        testTerms.add(new Term("testTerm1"));
        testTerms.add(new Term("testTerm2"));
        testTerms.add(new Term("testTerm3"));
        //WHEN
        for (final var term : testTerms) {
            for (final var doc : docsSet) {
                term.visit(doc);
            }
        }
        final var distancesMatrix =
                DistanceMatrixCalculator.euklidesMatrixCalculator().calcDistancesBetweenDocs(docsSet, testTerms);
        //THEN
        for (final var distances : distancesMatrix) {
            final var doc = distances.doc();
            Assertions.assertEquals(0, distances.distancesTo().get(doc).intValue());
        }
    }

}
