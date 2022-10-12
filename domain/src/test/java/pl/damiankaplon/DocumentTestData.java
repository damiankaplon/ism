package pl.damiankaplon;

import pl.damiankaplon.domain.Document;
import pl.damiankaplon.domain.ISMException;
import pl.damiankaplon.domain.Term;

public class DocumentTestData {
    public static Document MOCK_DOC_1 = new Document() {
        @Override
        public int countOccurrences(Term term) throws                           ISMException {
            return 5;
        }

        @Override
        public Name getName() {
            return new Document.Name("unique name 1");
        }
    };

    public static Document MOCK_DOC_2 = new Document() {
        @Override
        public int countOccurrences(Term term) throws ISMException {
            return 123;
        }

        @Override
        public Name getName() {
            return new Document.Name("unique name 2");
        }
    };
}
