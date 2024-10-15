package com.dillontodd.jsonparser.parser;

import com.dillontodd.jsonparser.lexer.Token;
import com.dillontodd.jsonparser.lexer.TokenType;

import java.util.List;

public class Parser {

    private final List<Token> tokens;
    private int index;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.index = 0;
    }

    public ASTNode parse() throws Exception {
        if (this.tokens.isEmpty()) {
            throw new Exception("json file is empty");
        }

        Token currentToken = this.tokens.get(this.index);
        if (currentToken.getType() == TokenType.OPEN_BRACE) {
            return parseObject();
        } else {
            throw new Exception("json file must be start with '{'");
        }
    }

    private Token advance() {
        if (this.index >= this.tokens.size() - 1) {
            return null;
        }
        return this.tokens.get(++this.index);
    }

    private ASTNode parseObject() throws Exception {
        ASTNode node = new ASTNode(ASTNodeType.OBJECT, null);
        Token token = advance();
        if (token == null) {
            throw new Exception("no closing brace");
        }

        while (token.getType() != TokenType.CLOSE_BRACE) {
            if (token.getType() == TokenType.STRING) {
                String key = token.getValue();

                token = advance();
                if (token == null) {
                    throw new Exception("invalid json. invalid key/value pair");
                }

                if (token.getType() != TokenType.COLON) {
                    throw new Exception("invalid json. expected colon in key/value pair");
                }

                token = advance();
                if (token == null) {
                    throw new Exception("invalid json. invalid key/value pair");
                }

                ASTNode value = parseValue();
                JsonKeyValuePair keyValuePair = new JsonKeyValuePair(key, value);
                node.setNodeValue(keyValuePair);

            } else {
                throw new Exception("invalid json. Expected key/value pair");
            }

            token = advance();
            if (token == null) {
                throw new Exception("json object must end with '}'");
            }

            if (token.getType() == TokenType.COMMA) {
                token = advance();
                if (token == null) {
                    throw new Exception("invalid json");
                }
            }
        }

        return node;
    }

    private ASTNode parseValue() throws Exception {
        Token token = tokens.get(this.index);
        return switch (token.getType()) {
            case STRING -> new ASTNode(ASTNodeType.STRING, token.getValue());
            case TRUE -> new ASTNode(ASTNodeType.BOOLEAN, true);
            case FALSE -> new ASTNode(ASTNodeType.BOOLEAN, false);
            case INTEGER -> new ASTNode(ASTNodeType.NUMBER, Integer.valueOf(token.getValue()));
            case DOUBLE -> new ASTNode(ASTNodeType.NUMBER, Double.valueOf(token.getValue()));
            case NULL -> new ASTNode(ASTNodeType.NULL, null);
            default -> throw new Exception("Unexpected token type: " + token.getValue());
        };
    }
}
