package pl.damiankaplon.ism.system;

import pl.damiankaplon.domain.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class System {

    public static void main(String[] args) throws ISMException {
        final var consoleArgsParser = new ConsoleArgsParser(args);
        final var docLinks = consoleArgsParser.parseDocumentsLinks();
        final var documents = generateDocumentsFromLinks(docLinks.toArray(new String[0]));
        final var terms = consoleArgsParser.parseTermsValues()
                .stream()
                .map(Term::new)
                .toList();

        for (final Term term : terms) {
            java.lang.System.out.print(term.value + "->[");
            for (Document doc : documents) {
                term.visit(doc);
                java.lang.System.out.print(doc.getName().name() + ":" + term.getOccurrences(doc.getName()) + ", ");
            }
            java.lang.System.out.print("]\n");
        }

        printEuklidesDistanceMatrix(documents, terms);
    }

    private static void printEuklidesDistanceMatrix(Set<Document> documents, List<Term> terms) throws ISMException {
        final var euklidesDistanceMatrix = DistanceMatrixCalculator.EuklidesMatrixCalculator();
        final var distancesMatrix = euklidesDistanceMatrix.calcDistancesBetweenDocs(documents, new ArrayList<>(terms));
        for (var distances : distancesMatrix) {
            java.lang.System.out.print(distances.doc().getName().name() + "->");
            for (var key : distances.distancesTo().keySet()) {
                java.lang.System.out.print(key.getName().name() + ": ");
                java.lang.System.out.print(distances.distancesTo().get(key) + ", ");
            }
            java.lang.System.out.print("\n");
        }
    }

    static Set<Document> generateDocumentsFromLinks(String[] links) throws ISMException {
        final var docs = new LinkedHashSet<Document>();
        for (int i = 0; i < links.length; i++) {
            docs.add(DocumentFactory.createDocument("D" + i, links[i]));
        }
        return docs;
    }
//TODO Euklides, manhatan, czebyszewskiego a, b, c, cosinus
}
