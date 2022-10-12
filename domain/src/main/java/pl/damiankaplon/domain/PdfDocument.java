package pl.damiankaplon.domain;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Path;

@RequiredArgsConstructor
final class PdfDocument implements Document {

    public final Name name;
    private final Path pdfPath;

    @Override
    public int countOccurrences(Term term) throws ISMException {
        int occurances = 0;
        try {
            PdfReader reader = new PdfReader(pdfPath.toAbsolutePath().toString());
            for (int pageNo = 1; pageNo <= reader.getNumberOfPages(); pageNo++) {
                String textFromPage = PdfTextExtractor.getTextFromPage(reader, pageNo).toLowerCase();
                var splits = textFromPage.split(term.value);
                if(splits.length > 0)
                    occurances += splits.length - 1;
            }
            reader.close();
        }
        catch (IOException exception) {
            throw new ISMException("Could not read PDF page", exception);
        }
        return occurances;
    }

    @Override
    public Name getName() {
        return this.name;
    }
}
