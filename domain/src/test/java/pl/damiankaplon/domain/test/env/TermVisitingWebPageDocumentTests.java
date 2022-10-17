package pl.damiankaplon.domain.test.env;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.damiankaplon.domain.Document;
import pl.damiankaplon.domain.DocumentFactory;
import pl.damiankaplon.domain.ISMException;
import pl.damiankaplon.domain.Term;

class TermVisitingWebPageDocumentTests {
    @Test
    void afterVisitingWebPageCountsTermOccurrences() throws ISMException {
        //GIVEN
        Term term = new Term("disease");
        Document document = DocumentFactory.createDocument("unique1", "https://en.wikipedia.org/wiki/Disease");
        final var termOccurrences = document.countOccurrences(term);
        //WHEN
        term.visit(document);
        final var testedOccurrences = term.getOccurrences(document.getName());
        //THEN
        Assertions.assertEquals(termOccurrences, testedOccurrences);
    }
}
