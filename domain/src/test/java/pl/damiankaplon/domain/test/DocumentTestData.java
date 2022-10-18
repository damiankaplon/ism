package pl.damiankaplon.domain.test;

import pl.damiankaplon.domain.Document;
import pl.damiankaplon.domain.ISMException;
import pl.damiankaplon.domain.Term;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class DocumentTestData {
    public static Document MOCK_DOC() {
        return new Document() {
            final Map<String, Integer> termValueOccurances = new LinkedHashMap<>();

            @Override
            public int countOccurrences(Term term) throws ISMException {
                if (termValueOccurances.get(term.value) != null) {
                    return termValueOccurances.get(term.value);
                } else {
                    Random random = new Random();
                    int rand = random.nextInt();
                    termValueOccurances.put(term.value, rand);
                    return rand;
                }
            }

            @Override
            public Name getName() {
                return new Document.Name("unique name 1");
            }

        };
    }

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
