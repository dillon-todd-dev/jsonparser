package com.dillontodd.jsonparser.lexer;

import com.dillontodd.jsonparser.exceptions.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private String json;
    private int index;

    public Lexer(String json) {
        this.json = json;
        this.index = 0;
    }

    public List<Token> generateTokens() throws JSONException {
        List<Token> tokens = new ArrayList<>();
        while (index < json.length()) {
            char character = json.charAt(index);

            if (character == '{') {
                tokens.add(new Token(TokenType.OPEN_BRACE, String.valueOf(character)));
                index++;
            } else if (character == '}') {
                tokens.add(new Token(TokenType.CLOSE_BRACE, String.valueOf(character)));
                index++;
            } else if (character == ':') {
                tokens.add(new Token(TokenType.COLON, String.valueOf(character)));
                index++;
            } else if (character == ',') {
                tokens.add(new Token(TokenType.COMMA, String.valueOf(character)));
                index++;
            } else if (character == '"') {
                tokens.add(generateStringToken());
                index++;
            } else if (character == 't') {
                if (json.startsWith("true", index)) {
                    tokens.add(new Token(TokenType.TRUE, "true"));
                    index += 4;
                } else {
                    throw new JSONException("Invalid character: " + character);
                }
            } else if (character == 'f') {
                if (json.startsWith("false", index)) {
                    tokens.add(new Token(TokenType.FALSE, "false"));
                    index += 5;
                } else {
                    throw new JSONException("Invalid character: " + character);
                }
            } else if (character == 'n') {
                if (json.startsWith("null", index)) {
                    tokens.add(new Token(TokenType.NULL, "null"));
                    index += 4;
                } else {
                    throw new JSONException("Invalid character: " + character);
                }
            } else if (character == ' ') {
                tokens.add(new Token(TokenType.WHITESPACE, String.valueOf(character)));
                index++;
            } else if (character == '\n') {
                tokens.add(new Token(TokenType.NEW_LINE, String.valueOf(character)));
                index++;
            } else {
                throw new JSONException("Invalid character: " + character);
            }
        }
        return tokens;
    }

    private Token generateStringToken() {
        StringBuilder stringBuilder = new StringBuilder();
        index++;
        while (this.json.charAt(index) != '"') {
            stringBuilder.append(json.charAt(index));
            index++;
        }
        return new Token(TokenType.STRING, stringBuilder.toString());
    }
}
