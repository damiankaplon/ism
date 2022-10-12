package pl.damiankaplon.domain;

import java.nio.file.Path;

public final class DocumentFactory {

    public static Document createDocument(String uniqueName, String source) throws ISMException {
        if (source.endsWith(".pdf"))
            return new PdfDocument(new Document.Name(uniqueName), Path.of(source));
        else if (source.startsWith("http://") || source.startsWith("https://"))
            return new WebPageDocument(new Document.Name(uniqueName), source);
        else
            throw new ISMException("Could not create document of certain type. Supported types are .pdf, .txt or existing WebPage");
    }

}
