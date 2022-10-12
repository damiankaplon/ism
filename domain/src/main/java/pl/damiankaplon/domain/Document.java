package pl.damiankaplon.domain;

public interface Document {

    int countOccurrences(Term term) throws ISMException;
    Name getName();

    record Name(String name){}

}

