package pl.damiankaplon.ism.system;

import lombok.RequiredArgsConstructor;
import pl.damiankaplon.domain.Document;
import pl.damiankaplon.domain.ISMException;
import pl.damiankaplon.domain.Term;

import java.util.List;

@RequiredArgsConstructor
public class MockDoc implements Document {

    private final List<String> vector;

    @Override
    public int countOccurrences(Term term) throws ISMException {
        return switch (term.getValue()) {
            case "diseas" -> Integer.parseInt(vector.get(0));
            case "flu" -> Integer.parseInt(vector.get(1));
            case "fever" -> Integer.parseInt(vector.get(2));
            case "cancer" -> Integer.parseInt(vector.get(3));
            case "inflammation" -> Integer.parseInt(vector.get(4));
            case "pneumonia" -> Integer.parseInt(vector.get(5));
            case "cough" -> Integer.parseInt(vector.get(6));
            case "depression" -> Integer.parseInt(vector.get(7));
            case "dermatitis" -> Integer.parseInt(vector.get(8));
            case "acne" -> Integer.parseInt(vector.get(9));
            case "diarrhea" -> Integer.parseInt(vector.get(10));
            default -> throw new ISMException("To big vector");
        };
    }

    @Override
    public Name getName() {
        return new Name("User vector");
    }
}
