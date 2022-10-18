package pl.damiankaplon.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public final class Term implements DocumentVisitor {

    @Getter public final String value;
    private final Map<Document.Name, Integer> documentOccurrences = new HashMap<>();

    @Override
    public void visit(Document document) throws ISMException {
        documentOccurrences.put(document.getName(), document.countOccurrences(this));
    }

    public int getOccurrences(Document.Name name) throws ISMException {
        if (documentOccurrences.containsKey(name))
            return this.documentOccurrences.get(name);
        else
            throw new ISMException("The document have not been read yet");
    }

}
