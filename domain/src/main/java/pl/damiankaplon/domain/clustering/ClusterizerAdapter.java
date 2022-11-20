package pl.damiankaplon.domain.clustering;

import lombok.RequiredArgsConstructor;
import pl.damiankaplon.domain.Document;
import pl.damiankaplon.domain.ISMException;
import pl.damiankaplon.domain.Term;

import java.util.*;

@RequiredArgsConstructor
public class ClusterizerAdapter {
    public record Centroid(Map<String,Double> coordinates) {}
    public record Record(String desc, Map<String, Double> features) {}

    private final Set<Document> documents;
    private final Set<Term> terms;

    public static Map<ClusterizerAdapter.Centroid, List<Record>> clusterize(Set<Document> documents, Set<Term> terms) throws ISMException {
        var records = transformToRecords(documents, terms);
        return KMeansClusterizer.fit(records, 10, 50);
    }

    private static List<Record> transformToRecords(Set<Document> documents, Set<Term> terms) throws ISMException {
        var records = new ArrayList<Record>();
        for (var doc : documents){
            var features = new HashMap<String, Double>();
            for (var term : terms){
                var value = term.getOccurrences(doc.getName());
                features.put(term.getValue(), (double) value);
            }
            var record = new Record(doc.getName().name(), features);
           records.add(record);
        }
        return records;
    }
    
    
}
