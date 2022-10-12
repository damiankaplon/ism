package pl.damiankaplon.domain;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

@RequiredArgsConstructor
final class WebPageDocument implements Document {

    public final Name name;
    private final String pageUrl;

    @Override
    public int countOccurrences(Term term) throws ISMException {
        try {
            final URL url = new URL(this.pageUrl);
            Scanner sc = new Scanner(url.openStream());
            StringBuilder sb = new StringBuilder();
            while (sc.hasNext()) {
                sb.append(sc.next());
            }
            String page = sb.toString();
            page = page.replaceAll("<[^>]*>", "");
            return page.toLowerCase().split(term.value.toLowerCase()).length - 1;
        }
        catch (IOException e) {
            throw new ISMException("Could not load web page content: " + this.pageUrl, e);
        }
    }

        @Override
    public Name getName() {
        return this.name;
    }
}
