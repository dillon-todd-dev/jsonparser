package com.dillontodd.jsonparser.parser;

import com.dillontodd.jsonparser.lexer.Token;
import com.dillontodd.jsonparser.lexer.TokenType;

import java.util.List;

public class Parser {

    private final List<Token> tokens;
    private int currentPosition;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.currentPosition = 0;
    }

    public String parse() {
        if (this.tokens.isEmpty()) {
            return "invalid json";
        }

        Token currentToken = this.tokens.get(this.currentPosition);
        if (currentToken.getType() == TokenType.OPEN_BRACE) {
            return parseObject();
        } else {
            return "invalid json";
        }
    }

    private Token advance() {
        if (this.currentPosition >= this.tokens.size() - 1) {
            return null;
        }
        return this.tokens.get(++this.currentPosition);
    }

    private String parseObject() {
        Token token = advance();
        if (token == null) {
            return "invalid json";
        }

        while (token.getType() != TokenType.CLOSE_BRACE) {
            token = advance();
            if (token == null) {
                return "invalid json";
            }
        }

        return "valid json";
    }
}
