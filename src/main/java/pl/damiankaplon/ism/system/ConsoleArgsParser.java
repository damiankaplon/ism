package pl.damiankaplon.ism.system;

import lombok.RequiredArgsConstructor;
import pl.damiankaplon.domain.ISMException;

import java.util.*;

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
            if (!args[i].equals("-d") && !args[i].equals("-p"))
                termsValues.add(args[i]);
            else
                break;
        }
        return termsValues;
    }

    public List<String> pareProposeVectorValues() throws ISMException {
        final int termsStart = this.findProposeStartIndex();
        final var proposeVector = new ArrayList<String>();
        for (int i = termsStart; i < args.length; i++) {
            if (!args[i].equals("-d") && !args[i].equals("-t") && !args[i].equals("-p"))
                proposeVector.add(args[i]);
            else
                break;
        }
        return proposeVector;
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

    private int findProposeStartIndex() throws ISMException {
        for(int i = 0; i < args.length; i++) {
            if (args[i].equals("-p"))
                return i + 1;
        }
        throw new ISMException("Did not specified propose vector");
    }
}
