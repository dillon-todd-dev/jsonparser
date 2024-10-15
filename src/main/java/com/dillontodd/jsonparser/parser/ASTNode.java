package com.dillontodd.jsonparser.parser;

public class ASTNode {

    private ASTNodeType nodeType;
    private Object nodeValue;

    public ASTNode(ASTNodeType nodeType, Object nodeValue) {
        this.nodeType = nodeType;
        this.nodeValue = nodeValue;
    }

    public ASTNodeType getNodeType() {
        return this.nodeType;
    }

    public Object getNodeValue() {
        return this.nodeValue;
    }

    public void setNodeType(ASTNodeType nodeType) {
        this.nodeType = nodeType;
    }

    public void setNodeValue(Object nodeValue) {
        this.nodeValue = nodeValue;
    }
}
