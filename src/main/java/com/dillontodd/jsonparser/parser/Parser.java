package com.dillontodd.jsonparser.parser;

import com.dillontodd.jsonparser.exceptions.JSONException;
import com.dillontodd.jsonparser.lexer.Token;
import com.dillontodd.jsonparser.lexer.TokenType;

import java.util.List;

public class Parser {

    private final List<Token> tokens;
    private int index;
    private int lineNumber;
    private int linePosition;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.index = 0;
        this.lineNumber = 0;
        this.linePosition = 0;
    }

    public boolean parse() throws JSONException {
        if (tokens.isEmpty()) {
            throw new JSONException("json file is empty");
        }

        Token token = skipWhitespaceAndNewLines();
        if (token.getType() == TokenType.OPEN_BRACE) {
            linePosition++;
            boolean validJson = parseObject();
            return validJson && index == tokens.size() - 1;
        } else {
            throw new JSONException("json file must be start with '{' at line " + lineNumber + ":" + linePosition);
        }
    }

    private Token nextToken() throws JSONException {
        if (index + 1 >= tokens.size()) {
            throw new JSONException("Unexpected end of file at line " + lineNumber + ":" + linePosition);
        }
        return tokens.get(++index);
    }

    private Token currentToken() {
        return tokens.get(index);
    }

    private Token skipWhitespaceAndNewLines() throws JSONException {
        Token token = currentToken();
        while (token.getType() == TokenType.NEW_LINE || token.getType() == TokenType.WHITESPACE) {
            if (token.getType() == TokenType.NEW_LINE) {
                lineNumber++;
            } else {
                linePosition++;
            }
            token = nextToken();
        }
        return token;
    }

    private boolean parseObject() throws JSONException {
        Token token = skipWhitespaceAndNewLines();
        if (token.getType() != TokenType.OPEN_BRACE) {
            return false;
        }
        nextToken();
        token = skipWhitespaceAndNewLines();

        while (token.getType() != TokenType.CLOSE_BRACE) {
            if (token.getType() != TokenType.STRING) {
                return false;
            }
            token = nextToken();

            if (token.getType() != TokenType.COLON) {
                return false;
            }
            nextToken();

            if (!parseValue()) {
                return false;
            }
            token = nextToken();

            if (token.getType() == TokenType.COMMA) {
                nextToken();
                token = skipWhitespaceAndNewLines();
                if (token.getType() != TokenType.STRING) {
                    throw new JSONException("Invalid character: ',' at line " + lineNumber + ":" + linePosition);
                }
            }

            token = skipWhitespaceAndNewLines();
        }

        try {
            nextToken();
            skipWhitespaceAndNewLines();
        } catch (JSONException ignored) {
            // ignore end of file index out of bounds
        }
        return true;
    }

    private boolean parseValue() throws JSONException {
        Token token = skipWhitespaceAndNewLines();
        return token.getType() == TokenType.STRING;
    }
}
