package pl.damiankaplon.domain;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

final class PdfDocument implements Document {

    public final Name name;
    private final List<String> pages = new LinkedList<>();

    PdfDocument(Name name, Path pdfPath) {
        this.name = name;
        try {
            PdfReader reader = new PdfReader(pdfPath.toAbsolutePath().toString());
            for (int pageNo = 1; pageNo <= reader.getNumberOfPages(); pageNo++) {
                String textFromPage = PdfTextExtractor.getTextFromPage(reader, pageNo).toLowerCase();
                this.pages.add(textFromPage);
            }
        }catch (IOException e) {
            throw new RuntimeException(new ISMException("Could not read PDF page", e));
        }
    }

    @Override
    public int countOccurrences(Term term) {
        int occurances = 0;
            for (var page : this.pages) {
                var splits = page.split(term.value);
                if(splits.length > 0)
                    occurances += splits.length - 1;
            }
        return occurances;
    }

    @Override
    public Name getName() {
        return this.name;
    }
}
