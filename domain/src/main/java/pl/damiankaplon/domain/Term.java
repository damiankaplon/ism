package pl.damiankaplon.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public final class Term implements DocumentVisitor {

    @Getter public final String value;
    private final Map<Document.Name, Integer> documentOccurances = new HashMap<>();

    @Override
    public void visit(Document document) throws ISMException {
        documentOccurances.put(document.getName(), document.countOccurrences(this));
    }

    public int getOccurrences(Document.Name name) throws ISMException {
        if (documentOccurances.containsKey(name))
            return this.documentOccurances.get(name);
        else
            throw new ISMException("The document have not been read yet");
    }

}
