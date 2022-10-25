package pl.damiankaplon.domain;

interface DocumentVisitor {
    void visit(Document document) throws ISMException;
}
