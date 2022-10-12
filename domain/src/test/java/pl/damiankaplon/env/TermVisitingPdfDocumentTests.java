package pl.damiankaplon.env;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.damiankaplon.domain.Document;
import pl.damiankaplon.domain.DocumentFactory;
import pl.damiankaplon.domain.ISMException;
import pl.damiankaplon.domain.Term;

class TermVisitingPdfDocumentTests {

    @Test
    void afterVisitingDocumentReturnsProperOccurrencesValue() throws ISMException {
        //GIVEN
        Term term = new Term("disease");
        Document document = DocumentFactory.createDocument("unique1", "/test.pdf");
        final var termOccurrences = document.countOccurrences(term);
        //WHEN
        term.visit(document);
        final var testedOccurrences = term.getOccurrences(document.getName());
        //THEN
        Assertions.assertEquals(termOccurrences, testedOccurrences);
    }

}
