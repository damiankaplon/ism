package pl.damiankaplon.domain.test.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.damiankaplon.domain.test.DocumentTestData;
import pl.damiankaplon.domain.Document;
import pl.damiankaplon.domain.ISMException;
import pl.damiankaplon.domain.Term;

class TermVisitingDocumentTests {

    @Test
    void afterVisitingDocumentReturnsProperOccurrencesValue() throws ISMException {
        //GIVEN
        Term term = new Term("disease");
        Document document = DocumentTestData.MOCK_DOC();
        int termOccurrences = document.countOccurrences(term);
        //WHEN
        term.visit(document);
        final var testedOccurrences = term.getOccurrences(document.getName());
        //THEN
        Assertions.assertEquals(termOccurrences, testedOccurrences);
    }

    @Test
    void afterVisitingMultipleUniqueDocumentsReturnsProperOccurrencesValue() throws ISMException {
        //GIVEN
        Term term = new Term("disease");
        Document document1 = DocumentTestData.MOCK_DOC();
        Document document2 = DocumentTestData.MOCK_DOC_2;

        final int termOccurrences1 = document1.countOccurrences(term);
        final int termOccurrences2 = document2.countOccurrences(term);
        //WHEN
        term.visit(document1);
        term.visit(document2);
        final int testedOccurrences1 = term.getOccurrences(document1.getName());
        final int testedOccurrences2 = term.getOccurrences(document2.getName());
        //THEN
        Assertions.assertEquals(termOccurrences1, testedOccurrences1);
        Assertions.assertEquals(termOccurrences2, testedOccurrences2);
    }

}

