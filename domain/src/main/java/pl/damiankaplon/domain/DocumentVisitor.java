package pl.damiankaplon.domain;

public interface DocumentVisitor {
    void visit(Document document) throws ISMException;
}
