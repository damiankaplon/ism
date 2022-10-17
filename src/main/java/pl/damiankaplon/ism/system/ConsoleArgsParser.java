package pl.damiankaplon.ism.system;

import lombok.RequiredArgsConstructor;
import pl.damiankaplon.domain.ISMException;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@RequiredArgsConstructor
class ConsoleArgsParser {

    private final String[] args;

    public Set<String> parseDocumentsLinks() throws ISMException {
        final int documentsStart = this.findDocumentsStartIndex();
        final var documentLinks = new LinkedHashSet<String>();
        for (int i = documentsStart; i < args.length; i++) {
            if (!args[i].equals("-t"))
                documentLinks.add(args[i]);
            else
                break;
        }
        return documentLinks;
    }

    public Set<String> parseTermsValues() throws ISMException {
        final int termsStart = this.findTermsStartIndex();
        final var termsValues = new HashSet<String>();
        for (int i = termsStart; i < args.length; i++) {
            if (!args[i].equals("-d"))
                termsValues.add(args[i]);
            else
                break;
        }
        return termsValues;
    }

    private int findDocumentsStartIndex() throws ISMException {
        for(int i = 0; i < args.length; i++) {
            if (args[i].equals("-d"))
                return i + 1;
        }
        throw new ISMException("Did not specified documents");
    }

    private int findTermsStartIndex() throws ISMException {
        for(int i = 0; i < args.length; i++) {
            if (args[i].equals("-t"))
                return i + 1;
        }
        throw new ISMException("Did not specified terms");
    }
}
