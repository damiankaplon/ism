package pl.damiankaplon.ism.system;

import com.google.common.collect.Lists;
import pl.damiankaplon.domain.*;
import pl.damiankaplon.domain.clustering.ClusterizerAdapter;

import java.util.*;

public class System {

    public static void main(String[] args) throws ISMException {
        final var consoleArgsParser = new ConsoleArgsParser(args);
        final var docLinks = consoleArgsParser.parseDocumentsLinks();
        final var documents = generateDocumentsFromLinks(docLinks.toArray(new String[0]));
        final var terms = Lists.newArrayList(
                consoleArgsParser.parseTermsValues()
                .stream()
                .map(Term::new)
                .toList()
        );
        final var proposeVector = consoleArgsParser.pareProposeVectorValues();
        documents.add(new MockDoc(proposeVector));
        for (final Term term : terms) {
            java.lang.System.out.printf("%-12s ->[", term.value);
            for (Document doc : documents) {
                term.visit(doc);
                java.lang.System.out.printf("%-3s: %-5d, ", doc.getName().name(), term.getOccurrences(doc.getName()));
            }
            java.lang.System.out.print("]\n");
        }
        printEuklidesDistanceMatrix(documents, terms);
        printEuklidesZoomDistanceMatrix(documents, terms, 2, 3);
        printManhatanDistanceMatrix(documents, terms);
        printCzebyszewDistanceMatrix(documents, terms);
        java.lang.System.out.print("\n CLUSTERS \n");
        var clusters = ClusterizerAdapter.clusterize(documents, new HashSet<>(terms));
        printClusters(clusters);
        printClustersBestMatch(clusters);
        printBestMatch(documents, terms);
    }

    private static void printClustersBestMatch(Map<ClusterizerAdapter.Centroid, List<ClusterizerAdapter.Record>> clusters) {
        clusters.values().stream()
                .filter((List<ClusterizerAdapter.Record> ls) -> ls.stream().anyMatch(record -> record.desc().equalsIgnoreCase("user vector")))
                .findFirst()
                .ifPresent(
                        list -> clusters.keySet().stream()
                        .filter(k -> clusters.get(k).equals(list))
                        .findFirst()
                        .ifPresent(
                                key -> clusters.get(key).stream()
                                .filter(record -> !record.desc().equalsIgnoreCase("user vector")).findFirst().ifPresent(rec -> java.lang.System.out.println("Best match from cluster is: " + rec.desc()))
                        )
                );
    }

    private static void printClusters(Map<ClusterizerAdapter.Centroid, List<ClusterizerAdapter.Record>> clusters) {
        for (var centroid : clusters.keySet()){
            for(var cord : centroid.coordinates().keySet()){
                java.lang.System.out.print(cord + "(");
                java.lang.System.out.print(centroid.coordinates().get(cord));
                java.lang.System.out.print("), ");
            }
            java.lang.System.out.print("\n");
            for (var record : clusters.get(centroid)) {
                java.lang.System.out.print(record.desc() + " -> [");
                for(var featureName : record.features().keySet()) {
                    java.lang.System.out.print(featureName + ":(");
                    java.lang.System.out.print(record.features().get(featureName));
                    java.lang.System.out.print("), ");
                }
                java.lang.System.out.print("]\n");
            }
            java.lang.System.out.print("\n");
        }
    }

    private static void printBestMatch(LinkedHashSet<Document> documents, ArrayList<Term> terms) throws ISMException {
        java.lang.System.out.print("\n");
        final var euklidesDistanceMatrix = DistanceMatrixCalculator.euklides(documents, terms);
        final var distancesMatrix = euklidesDistanceMatrix.calcDistancesBetweenDocs();
        for (final var distances : distancesMatrix) {
            DistanceMatrixCalculator.Distances userVectorDistances;
            if (distances.doc().getName().name().equals("User vector")) {
                userVectorDistances = distances;
                Document closestDoc = null;
                for (final var doc : userVectorDistances.distancesTo().keySet()) {
                    double distance = userVectorDistances.distancesTo().get(doc);
                    if ((closestDoc == null || distance < userVectorDistances.distancesTo().get(closestDoc))
                    && !doc.getName().name().equals("User vector"))
                        closestDoc = doc;
                }
                java.lang.System.out.println("Best Document similarity match is: " + closestDoc.getName().name() + "\n Based on the distance to other documents counted throughout various distance algorithms");
            }
        }

    }

    private static void printEuklidesDistanceMatrix(LinkedHashSet<Document> documents, ArrayList<Term> terms) throws ISMException {
        java.lang.System.out.print("\n Euklides: \n");
        final var euklidesDistanceMatrix = DistanceMatrixCalculator.euklides(documents, terms);
        final var distancesMatrix = euklidesDistanceMatrix.calcDistancesBetweenDocs();
        for (var distances : distancesMatrix) {
            java.lang.System.out.print(distances.doc().getName().name() + " -> [");
            for (var key : distances.distancesTo().keySet()) {
                java.lang.System.out.printf("%-3s: %-8.2f, ", key.getName().name(), distances.distancesTo().get(key));
            }
            java.lang.System.out.print("]\n");
        }
    }

    private static void
    printEuklidesZoomDistanceMatrix(LinkedHashSet<Document> documents, ArrayList<Term> terms, int pow, int sqrt)
            throws ISMException {
        java.lang.System.out.printf("\n Euklides zoom pow %d, sqrt %d : \n", pow, sqrt);
        final var euklidesDistanceMatrix =
                DistanceMatrixCalculator.euklidesZoom(documents, terms, pow, sqrt);
        final var distancesMatrix = euklidesDistanceMatrix.calcDistancesBetweenDocs();
        for (var distances : distancesMatrix) {
            java.lang.System.out.print(distances.doc().getName().name() + " -> [");
            for (var key : distances.distancesTo().keySet()) {
                java.lang.System.out.printf("%-3s: %-8.2f, ", key.getName().name(), distances.distancesTo().get(key));
            }
            java.lang.System.out.print("]\n");
        }
    }

    private static void printManhatanDistanceMatrix(LinkedHashSet<Document> documents, ArrayList<Term> terms) throws ISMException {
        java.lang.System.out.print("\n Manhatan \n");
        final var euklidesDistanceMatrix = DistanceMatrixCalculator.manhatan(documents, terms);
        final var distancesMatrix = euklidesDistanceMatrix.calcDistancesBetweenDocs();
        for (var distances : distancesMatrix) {
            java.lang.System.out.print(distances.doc().getName().name() + " -> [");
            for (var key : distances.distancesTo().keySet()) {
                java.lang.System.out.printf("%-3s: %-8.2f, ", key.getName().name(), distances.distancesTo().get(key));
            }
            java.lang.System.out.print("]\n");
        }
    }

    private static void printCzebyszewDistanceMatrix(LinkedHashSet<Document> documents, ArrayList<Term> terms) throws ISMException {
        java.lang.System.out.print("\n Czebyszew \n");
        final var euklidesDistanceMatrix = DistanceMatrixCalculator.czebyszew(documents, terms);
        final var distancesMatrix = euklidesDistanceMatrix.calcDistancesBetweenDocs();
        for (var distances : distancesMatrix) {
            java.lang.System.out.print(distances.doc().getName().name() + " -> [");
            for (var key : distances.distancesTo().keySet()) {
                java.lang.System.out.printf("%-3s: %-8.2f, ", key.getName().name(), distances.distancesTo().get(key));
            }
            java.lang.System.out.print("]\n");
        }
    }

    static LinkedHashSet<Document> generateDocumentsFromLinks(String[] links) throws ISMException {
        final var docs = new LinkedHashSet<Document>();
        for (int i = 0; i < links.length; i++) {
            docs.add(DocumentFactory.createDocument("D" + i, links[i]));
        }
        return docs;
    }
//TODO Euklides, manhatan, czebyszewskiego a, b, c, cosinus
}
